package com.rainng.coursesystem.model.vo.request;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: course-system
 * @description: 课程查询入参
 * @author: chenqiulu
 * @create: 2024-04-12 22:04
 **/
@Data
public class CourseSearchReqVO {
    @ApiModelProperty("课程名，模糊查询")
    private String courseName;

    @ApiModelProperty("分类id")
    private String typeId;

    @ApiModelProperty("分页参数")
    private PageInfo<CourseSearchReqVO> pageParam;
}
