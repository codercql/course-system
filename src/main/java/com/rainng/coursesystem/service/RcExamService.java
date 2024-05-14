package com.rainng.coursesystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageInfo;
import com.rainng.coursesystem.dao.mapper.ExamMapper;
import com.rainng.coursesystem.dao.mapper.RcCourseMapper;
import com.rainng.coursesystem.dao.mapper.RcSelectCourseMapper;
import com.rainng.coursesystem.model.entity.RcCourseEntity;
import com.rainng.coursesystem.model.entity.RcExamEntity;
import com.rainng.coursesystem.model.entity.RcSelectCourseEntity;
import com.rainng.coursesystem.model.vo.request.ExamSearchReqVO;
import com.rainng.coursesystem.model.vo.request.RcExamReqVO;
import com.rainng.coursesystem.model.vo.response.ExamDetailVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.util.RandomNumUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-03 17:00
 **/
@Service
public class RcExamService extends BaseService {
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private RcCourseMapper rcCourseMapper;
    @Autowired
    private RcSelectCourseMapper rcSelectCourseMapper;

    public ResultVO<PageInfo<ExamDetailVO>> getExamMainPage(ExamSearchReqVO vo) {
        List<ExamDetailVO> examMainPage = examMapper.getExamMainPage(vo);
        PageInfo<ExamDetailVO> pageInfo = new PageInfo<>(examMainPage);
        return result(pageInfo);
    }

    public ResultVO<String> addExam(RcExamReqVO vo) {
        RcExamEntity entity = new RcExamEntity();
        BeanUtils.copyProperties(vo, entity);
        int examId = RandomNumUtil.getRandomNum();
        entity.setExamId(examId);
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());

        RcCourseEntity rcCourseEntity = rcCourseMapper.selectById(vo.getCourseId());
        if (rcCourseEntity == null) {
            return failedResult("课程不存在");
        }
        rcCourseEntity.setCourseExamId(examId);
        rcCourseEntity.setUpdateTime(new Date());

        examMapper.insert(entity);
        rcCourseMapper.updateById(rcCourseEntity);
        return result("新增考试成功！");
    }

    public ResultVO<String> updateExam(RcExamReqVO vo) {
        RcExamEntity entity = new RcExamEntity();

        BeanUtils.copyProperties(vo,entity);
        entity.setUpdateTime(new Date());

        RcCourseEntity rcCourseEntity = rcCourseMapper.selectById(vo.getCourseId());
        if (rcCourseEntity == null) {
            return failedResult("课程不存在");
        }
        rcCourseEntity.setCourseExamId(vo.getExamId());
        rcCourseEntity.setUpdateTime(new Date());
        examMapper.updateById(entity);
        rcCourseMapper.updateById(rcCourseEntity);
        return result("更新考试成功！");
    }

    public ResultVO<String> deleteExam(String examId) {
        examMapper.deleteById(examId);
        return result("删除考试成功！");
    }

    public ResultVO<List<ExamDetailVO>> getExamListByStudentId(Integer studentId) {
        List<RcSelectCourseEntity> rcSelectCourseEntities = rcSelectCourseMapper.selectList(new LambdaQueryWrapper<RcSelectCourseEntity>().eq(RcSelectCourseEntity::getScStudentId, studentId));
        List<Integer> examIdList = rcSelectCourseEntities.stream().map(RcSelectCourseEntity::getExamId).distinct().collect(Collectors.toList());
        ExamSearchReqVO vo = new ExamSearchReqVO();
        vo.setExamIdList(examIdList);
        return result(examMapper.getExamMainPage(vo));
    }

    public ResultVO<List<ExamDetailVO>> getExamListByTeacherId(Integer teacherId) {
        List<RcCourseEntity> rcSelectCourseEntities = rcCourseMapper.selectList(new LambdaQueryWrapper<RcCourseEntity>().eq(RcCourseEntity::getCourseTeacherId, teacherId));
        List<Integer> examIdList = rcSelectCourseEntities.stream().map(RcCourseEntity::getCourseExamId).distinct().collect(Collectors.toList());
        ExamSearchReqVO vo = new ExamSearchReqVO();
        vo.setExamIdList(examIdList);
        return result(examMapper.getExamMainPage(vo));
    }
}
