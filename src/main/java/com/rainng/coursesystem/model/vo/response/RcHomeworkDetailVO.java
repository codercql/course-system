package com.rainng.coursesystem.model.vo.response;

import com.rainng.coursesystem.model.entity.RcHomeworkEntity;
import lombok.Data;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-14 20:43
 **/
@Data
public class RcHomeworkDetailVO extends RcHomeworkEntity {

    private String courseName;

    private String teacherName;
}
