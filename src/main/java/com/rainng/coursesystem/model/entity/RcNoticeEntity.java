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
@TableName("rc_notice")
public class RcNoticeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Integer noticeId;
	/**
	 * 通知标题
	 */
	private String title;
	/**
	 * 通知内容
	 */
	private String content;
	/**
	 * 讲师id
	 */
	private Integer teacherId;
	/**
	 * 管理员id
	 */
	private Integer adminId;
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
