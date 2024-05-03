package com.rainng.coursesystem.service;

import com.github.pagehelper.PageInfo;
import com.rainng.coursesystem.dao.mapper.ExamMapper;
import com.rainng.coursesystem.model.entity.RcExamEntity;
import com.rainng.coursesystem.model.vo.request.ExamSearchReqVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.util.RandomNumUtil;
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

    public ResultVO<PageInfo<RcExamEntity>> getExamMainPage(ExamSearchReqVO vo) {
        List<RcExamEntity> examMainPage = examMapper.getExamMainPage(vo);
        PageInfo<RcExamEntity> pageInfo = new PageInfo<>(examMainPage);
        return result(pageInfo);
    }

    public ResultVO<String> addExam(RcExamEntity entity){
        entity.setExamId(RandomNumUtil.getRandomNum());
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        examMapper.insert(entity);
        return result("新增考试成功！");
    }

    public ResultVO<String> updateExam( RcExamEntity entity){
        entity.setUpdateTime(new Date());
        examMapper.updateById(entity);
        return result("更新考试成功！");
    }

    public ResultVO<String> deleteExam(String examId){
        examMapper.deleteById(examId);
        return result("删除考试成功！");
    }
}
