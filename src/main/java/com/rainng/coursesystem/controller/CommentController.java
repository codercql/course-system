package com.rainng.coursesystem.controller;

import com.rainng.coursesystem.model.vo.request.CommentVO;
import com.rainng.coursesystem.model.vo.request.ReplyVO;
import com.rainng.coursesystem.model.vo.response.CommentDetailVO;
import com.rainng.coursesystem.model.vo.response.CommentReplyVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-04-10 00:12
 **/
@Api("评论模块")
@RequestMapping("/comment")
@RestController
public class CommentController<ResultVo> {

    @Autowired
    private CommentService commentService;
    //新增评论 更新-新增回复 查询评论

    @ApiOperation("新增评论")
    @PostMapping("/addComment")
    public ResultVO<String> addComment(@RequestBody CommentVO vo) {
         return commentService.addComment(vo);
    }

    @ApiOperation("新增回复")
    @PostMapping("/addReply")
    public ResultVO<String> addReply(@RequestBody ReplyVO vo) {
         return commentService.addReply(vo);
    }

    @ApiOperation("查询该课程所有评论(courseId为空则查询所有评论)")
    @GetMapping("/getComment")
    public ResultVO<List<CommentReplyVO>> getComment(@RequestParam(value = "courseId" ,required = false) Integer courseId){
        return commentService.getComment(courseId);
    }

    @ApiOperation("查询该用户所有评论、回复,入参传studentId/teacherId")
    @GetMapping("/getCommentByUserId")
    public ResultVO<List<CommentDetailVO>> getCommentByUserId(@RequestParam("userId") String userId){
        return commentService.getCommentByUserId(userId);
    }

    @ApiOperation("删除评论")
    @GetMapping("/delete")
    public ResultVO<String> deleteComment(@RequestParam("commentId") String commentId) {
        return commentService.deleteComment(commentId);
    }
}
