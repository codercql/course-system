package com.rainng.coursesystem.model.vo.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-04-11 22:48
 **/
@Data
public class CommentVO {
    @ApiModelProperty("评论")
    @NotEmpty(message = "评论不能为空")
    private String comment;

    @ApiModelProperty("课程ID")
    @NotNull(message = "课程ID不能为空")
    private Integer courseId;

    @ApiModelProperty("用户ID")
    @NotNull(message = "用户ID不能为空")
    private Integer commentUserId;

    @ApiModelProperty("学生student，老师teacher")
    @NotEmpty(message = "角色不能为空")
    private String commentPrivilege;
}
