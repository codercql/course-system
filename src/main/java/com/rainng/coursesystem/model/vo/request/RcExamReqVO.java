package com.rainng.coursesystem.model.vo.request;

import com.rainng.coursesystem.model.entity.RcExamEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @program: course-system
 * @description: 考试新增更新
 * @author: chenqiulu
 * @create: 2024-05-08 20:11
 **/
@Data
public class RcExamReqVO extends RcExamEntity {
    @ApiModelProperty("课程id")
    @NotEmpty(message = "课程id不能为空")
    private String courseId;
}
