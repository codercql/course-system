package com.rainng.coursesystem.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2024-05-03 21:47:15
 */
@Data
@TableName("rc_select_course")
public class RcSelectCourseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 选课Id
	 */
	@TableId
	private Integer scId;
	/**
	 * 学生id
	 */
	private Integer scStudentId;
	/**
	 * 课程Id
	 */
	private Integer scCourseId;
	/**
	 *
	 */
	private Integer examId;
	/**
	 * 作业Id
	 */
	private Integer homeworkId;
	/**
	 * 期末考试分数
	 */
	private Integer scExamScore;
	/**
	 * 学生考试状态
	 */
	private String examStatus;
	/**
	 * 作业分数，逗号分隔
	 */
	private String scHomeScores;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

}
