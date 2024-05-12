package com.rainng.coursesystem.model.vo.response;

import com.rainng.coursesystem.model.entity.RcCourseEntity;
import lombok.Data;

/**
 * @program: course-system
 * @description: 课程查询返回VO
 * @author: chenqiulu
 * @create: 2024-04-29 14:46
 **/
@Data
public class CourseSearchResVO extends RcCourseEntity {
    private String typeName;

    private String teacherName;
}
