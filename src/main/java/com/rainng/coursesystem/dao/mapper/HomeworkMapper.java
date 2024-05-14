package com.rainng.coursesystem.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainng.coursesystem.model.entity.RcHomeworkEntity;
import com.rainng.coursesystem.model.vo.response.RcHomeworkDetailVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-03 16:59
 **/
@Mapper
public interface HomeworkMapper extends BaseMapper<RcHomeworkEntity> {
    List<RcHomeworkDetailVO> selectHomeworkDetailByCourseId(Integer courseId);

    List<RcHomeworkDetailVO> selectHomeworkDetailByHomeworkIdList(List<Integer> homeworkIdList);
}
