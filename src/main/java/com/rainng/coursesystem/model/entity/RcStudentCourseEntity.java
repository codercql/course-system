package com.rainng.coursesystem.model.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2024-05-05 16:42:31
 */
@Data
@TableName("rc_student_course")
public class RcStudentCourseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 选课Id
	 */
	@TableId
	private Integer scId;
	/**
	 * 学生Id
	 */
	private Integer scStudentId;
	/**
	 * 课程Id
	 */
	private Integer scCourseId;
	/**
	 * 日常表现分
	 */
	private Integer scDailyScore;
	/**
	 * 期末测试分
	 */
	private Integer scExamScore;
	/**
	 * 总成绩
	 */
	private Integer scScore;
	/**
	 * 学习进度
	 */
	private String progress;

}
