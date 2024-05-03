package com.rainng.coursesystem.util;

import lombok.Data;

/**
 * @program: course-system
 * @description: 公共返回
 * @author: chenqiulu
 * @create: 2024-04-29 14:49
 **/
@Data
public class ApiResponse<T> {
    private T data;
}
