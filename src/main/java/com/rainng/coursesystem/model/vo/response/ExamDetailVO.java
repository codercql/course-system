package com.rainng.coursesystem.model.vo.response;

import com.rainng.coursesystem.model.entity.RcExamEntity;
import lombok.Data;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-11 21:32
 **/
@Data
public class ExamDetailVO extends RcExamEntity {

    private String courseId;

    private String courseName;
}
