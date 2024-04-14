package com.rainng.coursesystem.model.vo.request;

import lombok.Data;

/**
 * @program: course-system
 * @description: 课程查询入参
 * @author: chenqiulu
 * @create: 2024-04-12 22:04
 **/
@Data
public class CourseSearchReqVO {
    private String courseName;

    private String courseTeacherId;
}
