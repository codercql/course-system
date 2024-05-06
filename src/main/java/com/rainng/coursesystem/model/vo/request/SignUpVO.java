package com.rainng.coursesystem.model.vo.request;

import com.rainng.coursesystem.model.constant.UserType;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-06 22:32
 **/
@Data
public class SignUpVO {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @ApiModelProperty("用户类型 STUDENT = 1;TEACHER = 2;ADMIN = 3")
    @NotNull(message = "必须选择用户类型")
    @Range(min = UserType.STUDENT, max = UserType.ADMIN, message = "无效的用户类型")
    private Integer userType;

    @ApiModelProperty("用户工号")
    private String number;
}
