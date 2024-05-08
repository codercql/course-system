package com.rainng.coursesystem.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
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
	@ApiModelProperty("选课Id")
	private Integer scId;
	/**
	 * 学生id
	 */
	@ApiModelProperty("学生id")
	private Integer scStudentId;
	/**
	 * 课程Id
	 */
	@ApiModelProperty("课程Id")
	private Integer scCourseId;
	/**
	 * 考试Id
	 */
	@ApiModelProperty("考试Id")
	private Integer examId;
	/**
	 * 作业Id
	 */
	@ApiModelProperty("作业Id")
	private Integer homeworkId;
	/**
	 * 期末考试分数
	 */
	@ApiModelProperty("期末考试分数")
	private Integer scExamScore;
	/**
	 * 学生考试状态 0，1
	 */
	@ApiModelProperty("学生考试状态 0，1")
	private String examStatus;
	/**
	 * 作业分数，逗号分隔
	 */
	@ApiModelProperty("作业分数，逗号分隔")
	private String scHomeScores;
	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 更新时间
	 */
	@ApiModelProperty("更新时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updateTime;

}
