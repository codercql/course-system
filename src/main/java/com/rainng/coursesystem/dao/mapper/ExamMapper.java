package com.rainng.coursesystem.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainng.coursesystem.model.entity.RcExamEntity;
import com.rainng.coursesystem.model.vo.request.ExamSearchReqVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-03 16:59
 **/
@Mapper
public interface ExamMapper extends BaseMapper<RcExamEntity> {
    List<RcExamEntity> getExamMainPage(ExamSearchReqVO vo);
}
