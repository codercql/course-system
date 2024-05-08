package com.rainng.coursesystem.service;

import com.github.pagehelper.PageInfo;
import com.rainng.coursesystem.dao.mapper.ExamMapper;
import com.rainng.coursesystem.dao.mapper.RcCourseMapper;
import com.rainng.coursesystem.model.entity.RcCourseEntity;
import com.rainng.coursesystem.model.entity.RcExamEntity;
import com.rainng.coursesystem.model.vo.request.ExamSearchReqVO;
import com.rainng.coursesystem.model.vo.request.RcExamReqVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.util.RandomNumUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-03 17:00
 **/
@Service
public class RcExamService extends BaseService{
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private RcCourseMapper rcCourseMapper;

    public ResultVO<PageInfo<RcExamEntity>> getExamMainPage(ExamSearchReqVO vo) {
        List<RcExamEntity> examMainPage = examMapper.getExamMainPage(vo);
        PageInfo<RcExamEntity> pageInfo = new PageInfo<>(examMainPage);
        return result(pageInfo);
    }

    public ResultVO<String> addExam(RcExamReqVO vo){
        RcExamEntity entity = new RcExamEntity();
        BeanUtils.copyProperties(vo,entity);
        int examId = RandomNumUtil.getRandomNum();
        entity.setExamId(examId);
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());

        RcCourseEntity rcCourseEntity = rcCourseMapper.selectById(vo.getCourseId());
        if(rcCourseEntity == null){
            return failedResult("课程不存在");
        }
        rcCourseEntity.setCourseExamId(examId);
        rcCourseEntity.setUpdateTime(new Date());

        examMapper.insert(entity);
        rcCourseMapper.updateById(rcCourseEntity);
        return result("新增考试成功！");
    }

    public ResultVO<String> updateExam(RcExamReqVO vo){
        RcExamEntity entity = new RcExamEntity();
        entity.setUpdateTime(new Date());

        RcCourseEntity rcCourseEntity = rcCourseMapper.selectById(vo.getCourseId());
        if(rcCourseEntity == null){
            return failedResult("课程不存在");
        }
        rcCourseEntity.setCourseExamId(entity.getExamId());
        rcCourseEntity.setUpdateTime(new Date());
        examMapper.updateById(entity);
        rcCourseMapper.updateById(rcCourseEntity);
        return result("更新考试成功！");
    }

    public ResultVO<String> deleteExam(String examId){
        examMapper.deleteById(examId);
        return result("删除考试成功！");
    }
}
