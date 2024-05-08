package com.rainng.coursesystem.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * @program: course-system
 * @description: 评论表
 * @author: chenqiulu
 * @create: 2024-04-09 23:29
 **/
@TableName("rc_comment")
@Data
public class CommentEntity {
    public static final String COMMENT_ID = "comment_id";
    public static final String COURSE_ID = "course_id";
    public static final String COMMENT = "comment";
    public static final String COMMENT_TM = "comment_tm";
    public static final String COMMENT_USER_ID = "comment_user_id";
    public static final String COMMENT_PRIVILEGE = "comment_privilege";
    public static final String REPLY_ID = "reply_id";
    public static final String REPLY_TM = "reply_tm";

    @ApiModelProperty("评论Id")
    @TableField(COMMENT_ID)
    private Integer commentId;

    @ApiModelProperty("课程Id")
    @TableField(COURSE_ID)
    private Integer courseId;

    @ApiModelProperty("评论")
    @TableField(COMMENT)
    private String comment;

    @ApiModelProperty("评论时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(COMMENT_TM)
    private Date commentTm;

    @ApiModelProperty("评论人id")
    @TableField(COMMENT_USER_ID)
    private Integer commentUserId;

    @ApiModelProperty("评论人角色 student/teacher")
    @TableField(COMMENT_PRIVILEGE)
    private String commentPrivilege;

    @ApiModelProperty("回复Id")
    @TableField(REPLY_ID)
    private Integer replyId;

    @ApiModelProperty("回复时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(REPLY_TM)
    private Date replyTm;
  }
