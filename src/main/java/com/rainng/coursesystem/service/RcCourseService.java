package com.rainng.coursesystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.github.pagehelper.PageInfo;
import com.rainng.coursesystem.dao.mapper.RcCourseMapper;
import com.rainng.coursesystem.dao.mapper.RcSelectCourseMapper;
import com.rainng.coursesystem.model.DownloadFileDto;
import com.rainng.coursesystem.model.entity.RcCourseEntity;
import com.rainng.coursesystem.model.entity.RcSelectCourseEntity;
import com.rainng.coursesystem.model.vo.request.CourseSearchReqVO;
import com.rainng.coursesystem.model.vo.response.CourseSearchResVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.util.RandomNumUtil;
import com.rainng.coursesystem.util.ZipFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-02 10:57
 **/
@Slf4j
@Service
public class RcCourseService extends BaseService{

    @Autowired
    private RcCourseMapper rcCourseMapper;
    @Autowired
    private RcSelectCourseMapper rcSelectCourseMapper;


    public ResultVO<String> update(RcCourseEntity entity) {
        if(entity.getCourseId() == null){
            return failedResult("课程Id不能为空");
        }
        if(entity.getCourseTeacherId() == null){
            return failedResult("授课教师Id不能为空");
        }
        entity.setUpdateTime(new Date());

        LambdaQueryWrapper<RcSelectCourseEntity> eq = new LambdaQueryWrapper<RcSelectCourseEntity>().eq(RcSelectCourseEntity::getScCourseId, entity.getCourseId());
        List<RcSelectCourseEntity> rcSelectCourseEntities = rcSelectCourseMapper.selectList(eq);
        rcSelectCourseEntities.forEach(sc->{
            sc.setUpdateTime(new Date());
            sc.setExamId(entity.getCourseExamId());
            sc.setHomeworkId(entity.getCourseHomeworkId());
            rcSelectCourseMapper.updateById(sc);
        });


        rcCourseMapper.updateById(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer id) {
        rcCourseMapper.deleteById(id);
        return result("删除成功");
    }

    public ResultVO<String> create(RcCourseEntity entity) {
        entity.setCourseId(RandomNumUtil.getRandomNum());
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        rcCourseMapper.insert(entity);
        return result("添加成功");
    }

    public ResultVO<String> getCoverByCourseIdList(List<Integer> courseIdList, HttpServletResponse response) {
        List<RcCourseEntity> entityList = rcCourseMapper.selectBatchIds(courseIdList);
        //封面文件压缩包返回
        List<DownloadFileDto> downloadFileDtoList = new ArrayList<>();
        // 这里的i的作用是保证文件名唯一，否则往ZIP中添加文件会报异常
        for (RcCourseEntity resVO : entityList) {
            if(resVO.getCover() != null){
                // 封面字节流
                byte[] bytes = resVO.getCover();
                String fileName = resVO.getCourseId() + ".jpg";
                DownloadFileDto dto = new DownloadFileDto();
                dto.setFileName(fileName);
                dto.setByteDataArr(bytes);
                downloadFileDtoList.add(dto);
            }
            if(resVO.getFile() != null){
                //文件字节流
                byte[] fileBytes = resVO.getFile();
                String fileName2 = resVO.getCourseId() + ".zip";
                DownloadFileDto dto2 = new DownloadFileDto();
                dto2.setFileName(fileName2);
                dto2.setByteDataArr(fileBytes);
                downloadFileDtoList.add(dto2);
            }
        }
        String fileName = "cover.zip";
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
        byte[] dataByteArr = new byte[0];
        if (CollectionUtils.isNotEmpty(downloadFileDtoList)) {
            try {
                dataByteArr = ZipFileUtil.zipFile(downloadFileDtoList);
                response.getOutputStream().write(dataByteArr);
                response.flushBuffer();
            } catch (Exception e) {
                log.error("压缩zip数据出现异常", e);
                return failedResult("压缩zip数据出现异常"+e.getMessage());
            }
        }
        return result("获取封面成功");
    }

    public ResultVO<String> getFileByCourseId(Integer courseId, HttpServletResponse response) {
        List<DownloadFileDto> downloadFileDtoList = new ArrayList<>();
        // 这里的i的作用是保证文件名唯一，否则往ZIP中添加文件会报异常
        RcCourseEntity resVO = rcCourseMapper.selectById(courseId);
        if(resVO.getFile() == null){
            return failedResult("文件不存在");
        }
        // 你的每一个文件字节流
        byte[] bytes = resVO.getFile();
        DownloadFileDto dto = new DownloadFileDto();
        dto.setFileName("file.zip");
        dto.setByteDataArr(bytes);
        downloadFileDtoList.add(dto);
        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename=" + "file.zip");
        byte[] dataByteArr = new byte[0];
        if (CollectionUtils.isNotEmpty(downloadFileDtoList)) {
            try {
                dataByteArr = ZipFileUtil.zipFile(downloadFileDtoList);
                response.getOutputStream().write(dataByteArr);
                response.flushBuffer();
            } catch (Exception e) {
                log.error("压缩zip数据出现异常", e);
                return failedResult("压缩zip数据出现异常"+e.getMessage());
            }
        }
        return result("获取文件成功");
    }

    public ResultVO<PageInfo<CourseSearchResVO>> getMainPage(CourseSearchReqVO vo) {
        List<CourseSearchResVO> mainPage = rcCourseMapper.getMainPage(vo);
        mainPage.stream().forEach(course->{
            course.setFile(null);
            course.setCover(null);
        });
        PageInfo<CourseSearchResVO> pageInfo = new PageInfo<>(mainPage);
        return result(pageInfo);
    }

    public ResultVO<List<RcCourseEntity>> getCourseListByStudentId(Integer studentId) {
        List<RcSelectCourseEntity> rcSelectCourseEntities = rcSelectCourseMapper.selectList(new LambdaQueryWrapper<RcSelectCourseEntity>().eq(RcSelectCourseEntity::getScStudentId, studentId));
        List<Integer> courseIdList = rcSelectCourseEntities.stream().map(RcSelectCourseEntity::getScCourseId).distinct().collect(Collectors.toList());
        List<RcCourseEntity> rcCourseEntities = rcCourseMapper.selectBatchIds(courseIdList);
        rcCourseEntities.stream().forEach(course->{
            course.setFile(null);
            course.setCover(null);
        });
        return result(rcCourseEntities);
    }
}
