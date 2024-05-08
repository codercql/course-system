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
 * @date 2024-04-29 16:39:20
 */
@Data
@TableName("rc_course_type")
public class RcCourseTypeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 课程分类id
	 */
	@TableId
	@ApiModelProperty("课程id")
	private Integer typeId;
	/**
	 * 父级分类id，顶层为空
	 */
	@ApiModelProperty("父级分类id，顶层为空")
	private Integer parentTypeId;
	/**
	 * 课程分类名称
	 */
	private String typeName;
	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	/**
	 * 更新时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date updatTime;

}
