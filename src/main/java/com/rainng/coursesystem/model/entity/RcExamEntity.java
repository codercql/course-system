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
 * @date 2024-05-03 16:46:32
 */
@Data
@TableName("rc_exam")
public class RcExamEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 试卷id
	 */
	@TableId
	private Integer examId;
	/**
	 * 考试名称
	 */
	private String name;
	/**
	 * 考试内容
	 */
	private String content;
	/**
	 * 考试开始时间
	 */
	private Date startTime;
	/**
	 * 考试截止时间
	 */
	private Date endTime;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

}
