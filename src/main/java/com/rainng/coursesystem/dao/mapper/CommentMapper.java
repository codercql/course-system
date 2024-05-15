package com.rainng.coursesystem.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainng.coursesystem.model.entity.CommentEntity;
import com.rainng.coursesystem.model.vo.UserIdNameVO;
import com.rainng.coursesystem.model.vo.response.CommentDetailVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-04-11 22:58
 **/
@Mapper
public interface CommentMapper extends BaseMapper<CommentEntity> {
    List<CommentDetailVO> selectByCourseId(@Param("courseId") Integer courseId);

    List<CommentDetailVO> selectListByReplyId(@Param("commentId")Integer commentId);

    List<UserIdNameVO> getStudentIdNameList();

    List<UserIdNameVO> getTeacherIdNameList();

    List<CommentDetailVO> getCommentByUserId(String userId);

    void deleteComment(String commentId);

}
