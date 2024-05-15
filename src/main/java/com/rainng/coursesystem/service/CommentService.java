package com.rainng.coursesystem.service;

import com.rainng.coursesystem.dao.mapper.CommentMapper;
import com.rainng.coursesystem.enums.PrivilegeEnum;
import com.rainng.coursesystem.model.entity.CommentEntity;
import com.rainng.coursesystem.model.vo.UserIdNameVO;
import com.rainng.coursesystem.model.vo.request.CommentVO;
import com.rainng.coursesystem.model.vo.request.ReplyVO;
import com.rainng.coursesystem.model.vo.response.CommentDetailVO;
import com.rainng.coursesystem.model.vo.response.CommentReplyVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.util.RandomNumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @program: course-system
 * @description:
 * @create: 2024-04-10 00:17
 **/
@Service
public class CommentService extends BaseService {
    @Autowired
    private CommentMapper commentMapper;

    /***
     * @Description: 新增评论
     * @Param: [entity]
     * @return: com.rainng.coursesystem.model.vo.response.ResultVO
     * @Date: 2024/4/11
     */
    public ResultVO<String> addComment(CommentVO vo) {
        CommentEntity entity = new CommentEntity();
        entity.setCommentId(RandomNumUtil.getRandomNum());
        entity.setCourseId(vo.getCourseId());
        entity.setComment(vo.getComment());
        entity.setCommentTm(new Date());
        entity.setCommentUserId(vo.getCommentUserId());
        entity.setCommentPrivilege(vo.getCommentPrivilege());
        commentMapper.insert(entity);
        return result("评论成功");
    }

    public ResultVO<String> addReply(ReplyVO vo) {
        CommentEntity entity = new CommentEntity();
        entity.setCommentId(RandomNumUtil.getRandomNum());
        entity.setCourseId(vo.getCourseId());
        entity.setComment(vo.getComment());
        entity.setReplyId(vo.getCommentId());
        entity.setReplyTm(new Date());
        entity.setCommentUserId(vo.getCommentUserId());
        entity.setCommentPrivilege(vo.getCommentPrivilege());
        commentMapper.insert(entity);
        return result("回复成功");
    }

    /***
     * @Description: 查询评论
     * @Param: []
     * @return: com.rainng.coursesystem.model.vo.response.ResultVO
     * @Author: chenqiulu
     * @Date: 2024/4/11
     */
    public ResultVO<List<CommentReplyVO>> getComment(Integer courseId) {
        List<CommentReplyVO> commentReplyVOList = new ArrayList<>();
        //查询所有用户id和姓名
        List<UserIdNameVO> studentIdNameList = commentMapper.getStudentIdNameList();
        List<UserIdNameVO> teacherIdNameList = commentMapper.getTeacherIdNameList();

        Map<Integer, List<UserIdNameVO>> studentIdNameMap = studentIdNameList.stream().collect(Collectors.groupingBy(UserIdNameVO::getUserId));
        Map<Integer, List<UserIdNameVO>> teacherIdNameMap = teacherIdNameList.stream().collect(Collectors.groupingBy(UserIdNameVO::getUserId));


        //先根据课程id查询评论,课程id为空查询全部评论
        List<CommentDetailVO> commentList = commentMapper.selectByCourseId(courseId);
        if (CollectionUtils.isEmpty(commentList)) {
            return result("");
        }
        //查询该评论所有回复
        commentList.forEach(commentDetailVO -> {
            CommentReplyVO commentReplyVO = new CommentReplyVO();
            List<CommentDetailVO> replyList = commentMapper.selectListByReplyId(commentDetailVO.getCommentId());
            //查询用户名称
            String userName = getUserName(commentDetailVO.getCommentPrivilege(), commentDetailVO.getCommentUserId(), teacherIdNameMap, studentIdNameMap);
            commentDetailVO.setCommentUserName(userName);
            replyList.forEach(reply -> {
                String replyUserName = getUserName(reply.getCommentPrivilege(), reply.getCommentUserId(), teacherIdNameMap, studentIdNameMap);
                reply.setCommentUserName(replyUserName);
            });
            commentReplyVO.setComment(commentDetailVO);
            commentReplyVO.setReplyList(replyList);
            commentReplyVOList.add(commentReplyVO);
        });
        return result(commentReplyVOList);

    }

    public String getUserName(String privilege, Integer userId, Map<Integer, List<UserIdNameVO>> teacherMap,
                              Map<Integer, List<UserIdNameVO>> studentMap) {
        if (PrivilegeEnum.TEACHER.getCode().equals(privilege)
                && teacherMap.containsKey(userId)) {
            return teacherMap.get(userId).get(0).getUserName();
        } else if (PrivilegeEnum.STUDENT.getCode().equals(privilege) &&
                studentMap.containsKey(userId)) {
            return studentMap.get(userId).get(0).getUserName();
        } else {
            return "";
        }
    }

    public ResultVO<List<CommentDetailVO>> getCommentByUserId(String userId){
        List<CommentDetailVO> commentByUserId = commentMapper.getCommentByUserId(userId);

        //查询所有用户id和姓名
        List<UserIdNameVO> studentIdNameList = commentMapper.getStudentIdNameList();
        List<UserIdNameVO> teacherIdNameList = commentMapper.getTeacherIdNameList();

        Map<Integer, List<UserIdNameVO>> studentIdNameMap = studentIdNameList.stream().collect(Collectors.groupingBy(UserIdNameVO::getUserId));
        Map<Integer, List<UserIdNameVO>> teacherIdNameMap = teacherIdNameList.stream().collect(Collectors.groupingBy(UserIdNameVO::getUserId));

        commentByUserId.forEach(commentDetailVO -> {
            //查询用户名称
            String userName = getUserName(commentDetailVO.getCommentPrivilege(), commentDetailVO.getCommentUserId(), teacherIdNameMap, studentIdNameMap);
            commentDetailVO.setCommentUserName(userName);
        });
        return result(commentByUserId);
    }

    public ResultVO<String> deleteComment(String commentId) {
        commentMapper.deleteComment(commentId);
        return result("删除评论成功");
    }
}
