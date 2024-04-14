package com.rainng.coursesystem.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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

    @TableField(COMMENT_ID)
    private Integer commentId;

    @TableField(COURSE_ID)
    private Integer courseId;

    @TableField(COMMENT)
    private String comment;

    @TableField(COMMENT_TM)
    private Date commentTm;

    @TableField(COMMENT_USER_ID)
    private Integer commentUserId;

    @TableField(COMMENT_PRIVILEGE)
    private String commentPrivilege;

    @TableField(REPLY_ID)
    private Integer replyId;

    @TableField(REPLY_TM)
    private Date replyTm;
  }
