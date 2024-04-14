package com.rainng.coursesystem.model.vo.request;

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
    @NotEmpty(message = "评论不能为空")
    private String comment;

    @NotNull(message = "课程ID不能为空")
    private Integer courseId;

    @NotNull(message = "用户ID不能为空")
    private Integer commentUserId;

    @NotEmpty(message = "角色不能为空")
    private String commentPrivilege;
}
