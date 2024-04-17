package com.rainng.coursesystem.model.vo.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-04-12 19:24
 **/
@Data
@ApiModel
public class CommentReplyVO {

    @ApiModelProperty("评论详情")
    private CommentDetailVO comment;

    @ApiModelProperty("该评论所有回复详情")
    private List<CommentDetailVO> replyList;

}
