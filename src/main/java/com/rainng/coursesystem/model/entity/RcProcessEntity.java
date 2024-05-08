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
 * @date 2024-05-03 16:46:32
 */
@Data
@TableName("rc_process")
public class RcProcessEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 进度id
	 */
	@TableId
	private Integer processId;
	/**
	 * 选课关系id
	 */
	private Integer scId;
	/**
	 * 学习进度百分比
	 */
	private Integer precent;
	/**
	 * 已完成章节数量
	 */
	private Integer finishCharpterNum;
	/**
	 *
	 */
	private Integer leftCharpterNum;
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
