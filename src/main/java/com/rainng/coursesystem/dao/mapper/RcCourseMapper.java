package com.rainng.coursesystem.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainng.coursesystem.model.entity.RcCourseEntity;
import com.rainng.coursesystem.model.vo.request.CourseSearchReqVO;
import com.rainng.coursesystem.model.vo.response.CourseSearchResVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-02 10:52
 **/
@Mapper
public interface RcCourseMapper extends BaseMapper<RcCourseEntity> {
    List<CourseSearchResVO> getMainPage(CourseSearchReqVO vo);

    List<CourseSearchResVO> getCourseListByStudentId(String studentId);
}
