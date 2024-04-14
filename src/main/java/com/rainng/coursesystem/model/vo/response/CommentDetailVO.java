package com.rainng.coursesystem.model.vo.response;

import com.rainng.coursesystem.model.entity.CommentEntity;
import lombok.Data;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-04-12 19:24
 **/
@Data
public class CommentDetailVO extends CommentEntity {
    private String commentUserName;
}
