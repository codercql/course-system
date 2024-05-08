package com.rainng.coursesystem.model.vo.response;

import com.rainng.coursesystem.model.entity.RcSelectCourseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-08 22:53
 **/
@Data
public class SelectCourseDetailVO extends RcSelectCourseEntity {
    @ApiModelProperty("课程名称")
    private String courseName;

    @ApiModelProperty("老师id")
    private String courseTeacherId;

    @ApiModelProperty("老师名称")
    private String courseTeacherName;

    @ApiModelProperty("分类id")
    private String typeId;

    @ApiModelProperty("分类名称")
    private String typeName;

}
