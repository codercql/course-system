package com.rainng.coursesystem.model.vo.response;

import com.rainng.coursesystem.model.entity.RcCourseTypeEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-03 14:00
 **/
@Data
public class CourseTypeSearchResVO {
    @ApiModelProperty("父类课程类型")
    RcCourseTypeEntity typeEntity;
    @ApiModelProperty("子类课程类型")
    List<RcCourseTypeEntity> subTypeList;
}
