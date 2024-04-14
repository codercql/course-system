package com.rainng.coursesystem.model.vo;

import lombok.Data;

/**
 * @program: course-system
 * @description: id和用户名VO
 * @author: chenqiulu
 * @create: 2024-04-12 19:33
 **/
@Data
public class UserIdNameVO {
    private Integer userId;

    private String userName;

    private String privilege;
}
