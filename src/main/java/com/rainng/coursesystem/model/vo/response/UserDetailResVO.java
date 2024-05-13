package com.rainng.coursesystem.model.vo.response;

import com.rainng.coursesystem.model.entity.AdminEntity;
import com.rainng.coursesystem.model.entity.StudentEntity;
import com.rainng.coursesystem.model.entity.TeacherEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-13 22:03
 **/
@Data
public class UserDetailResVO {
    @ApiModelProperty("student = 1, teacher = 2, admin = 3")
    private String userType;

    private List<TeacherEntity> teacherList;

    private List<AdminEntity> adminList;

    private List<StudentEntity> studentList;

}
