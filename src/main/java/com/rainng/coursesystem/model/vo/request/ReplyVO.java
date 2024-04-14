package com.rainng.coursesystem.model.vo.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-04-11 23:04
 **/
@Data
public class ReplyVO extends CommentVO {
    @NotNull(message = "评论id不能为空")
    private Integer commentId;
}
