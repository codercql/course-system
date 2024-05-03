package com.rainng.coursesystem.service;

import com.github.pagehelper.PageInfo;
import com.rainng.coursesystem.dao.mapper.RcCourseMapper;
import com.rainng.coursesystem.model.entity.CourseEntity;
import com.rainng.coursesystem.model.entity.RcCourseEntity;
import com.rainng.coursesystem.model.vo.request.CourseSearchReqVO;
import com.rainng.coursesystem.model.vo.response.CourseSearchResVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.util.RandomNumUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

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

    public ResultVO<String> update(RcCourseEntity entity) {
        if(entity.getCourseId() == null){
            return failedResult("课程Id不能为空");
        }
        if(entity.getCourseTeacherId() == null){
            return failedResult("授课教师Id不能为空");
        }
        entity.setUpdateTime(new Date());

        rcCourseMapper.updateById(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer id) {
        rcCourseMapper.deleteById(id);
        return result("删除成功");
    }

    public ResultVO<String> create(RcCourseEntity entity) {
        if (rcCourseMapper.selectById(entity.getCourseId()) != null) {
            return failedResult("课程Id: " + entity.getCourseId() + "已存在!");
        }
        entity.setCourseId(RandomNumUtil.getRandomNum());
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        rcCourseMapper.insert(entity);
        return result("添加成功");
    }

    public ResultVO<String> getCoverByCourseId(Integer courseId) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return failedResult("requestAttributes == null");
        }
        HttpServletResponse response = requestAttributes.getResponse();
        if (response == null) {
            return failedResult("response == null");
        }
        RcCourseEntity entity = rcCourseMapper.selectById(courseId);
        try {
            response.getOutputStream().write(entity.getCover());
        } catch (IOException e) {
            log.error("文件流写入失败={}", e.getMessage());
            e.printStackTrace();
        }
        return result("获取封面成功");
    }

    public ResultVO<String> getFileByCourseId(Integer courseId) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return failedResult("requestAttributes == null");
        }
        HttpServletResponse response = requestAttributes.getResponse();
        if (response == null) {
            return failedResult("response == null");
        }
        RcCourseEntity entity = rcCourseMapper.selectById(courseId);
        try {
            response.getOutputStream().write(entity.getFile());
        } catch (IOException e) {
            log.error("文件流写入失败={}", e.getMessage());
            e.printStackTrace();
        }
        return result("获取文件成功");
    }

    public ResultVO<PageInfo<CourseSearchResVO>> getMainPage(CourseSearchReqVO vo) {
        List<CourseSearchResVO> mainPage = rcCourseMapper.getMainPage(vo);
        PageInfo<CourseSearchResVO> pageInfo = new PageInfo<>(mainPage);
        return result(pageInfo);
    }

}
