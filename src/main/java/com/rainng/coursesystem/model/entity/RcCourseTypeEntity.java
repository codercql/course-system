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
	private Integer typeId;
	/**
	 * 父级分类id，顶层为空
	 */
	private Integer parentTypeId;
	/**
	 * 课程分类名称
	 */
	private String typeName;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updatTime;

}
