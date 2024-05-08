package com.rainng.coursesystem.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainng.coursesystem.model.entity.RcSelectCourseEntity;
import com.rainng.coursesystem.model.vo.response.SelectCourseDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-05 17:20
 **/
@Mapper
public interface RcSelectCourseMapper extends BaseMapper<RcSelectCourseEntity> {

    List<SelectCourseDetailVO> getCourseListByStudentId(String studentId);
}
