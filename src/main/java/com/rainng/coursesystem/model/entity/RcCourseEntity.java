package com.rainng.coursesystem.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-02 10:46
 **/
@Data
public class RcCourseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 课程Id
     */
    @TableId
    private Integer courseId;
    /**
     * 授课教师Id
     */
    private Integer courseTeacherId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程简介
     */
    private String introduction;
    /**
     * 课程封面
     */
    private byte[] cover;
    /**
     *
     */
    private Integer courseSelectedCount;
    /**
     * 考试时间
     */
    private Date courseExamDate;
    /**
     * 课程创建时间
     */
    private Date createTime;
    /**
     * 课程更新时间
     */
    private Date updateTime;
    /**
     * 0表示免费1表示付费
     */
    private Integer freeType;
    /**
     * 课程分类id
     */
    private Integer typeId;
    /**
     * 课程文件
     */
    private byte[] file;
    /**
     * 课程考试大纲
     */
    private String courseExamFrame;
    /**
     *
     */
    private Integer courseExamId;
    /**
     *
     */
    private Integer courseHomeworkId;
    /**
     *
     */
    private Date courseStartTime;
    /**
     *
     */
    private Date courseEndTime;
}