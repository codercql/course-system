package com.rainng.coursesystem.model.vo.response;

import com.rainng.coursesystem.model.entity.RcNoticeEntity;
import lombok.Data;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-12 22:15
 **/
@Data
public class NoticeSearchResVO extends RcNoticeEntity {
    private String teacherName;
    private String adminUsername;
}
