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
 * @date 2024-05-03 16:46:31
 */
@Data
@TableName("rc_homework")
public class RcHomeworkEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 作业id
	 */
	@TableId
	private Integer homeworkId;
	/**
	 * 选课关系id
	 */
	private Integer courseId;
	/**
	 * 作业名称
	 */
	private String name;
	/**
	 * 作业内容
	 */
	private String content;
	/**
	 * 作业开始时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startTime;
	/**
	 * 作业截止时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endTime;
	/**
	 * 作业状态0,1
	 */
	private String endStatus;
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
