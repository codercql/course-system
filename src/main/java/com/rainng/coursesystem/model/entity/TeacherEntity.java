package com.rainng.coursesystem.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@TableName("rc_teacher")
@Data
public class TeacherEntity {
//    public static final String ID = "teacher_id";
//    public static final String DEPARTMENT_ID = "teacher_department_id";
//    public static final String NUMBER = "teacher_number";
//    public static final String NAME = "teacher_name";
//    public static final String PASSWORD = "teacher_password";
//
//    @NotNull
//    @TableId(value = ID, type = IdType.AUTO)
//    private Integer id;
//
//    @NotNull(message = "必须选择所属系")
//    @TableField(DEPARTMENT_ID)
//    private Integer departmentId;
//
//    @Length(min = 12, max = 12, message = "工号长度必须为12位")
//    @TableField(NUMBER)
//    private String number;
//
//    @NotBlank(message = "教师姓名不能为空")
//    @TableField(NAME)
//    private String name;
//
//    @NotNull
//    @TableField(PASSWORD)
//    private String password;

    /**
     * 教师Id
     */
    @TableId
    private Integer teacherId;
    /**
     * 教师工号
     */
    private String teacherNumber;
    /**
     * 教师姓名
     */
    private String teacherName;
    /**
     * 密码
     */
    private String teacherPassword;
    /**
     * 专业领域
     */
    private String major;
    /**
     * 教学经验
     */
    private String experirence;
    /**
     * 职称
     */
    private String level;
    /**
     * 教师简介
     */
    private String introduction;
}
