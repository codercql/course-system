package com.rainng.coursesystem.controller;

import com.rainng.coursesystem.model.vo.request.CommentVO;
import com.rainng.coursesystem.model.vo.request.ReplyVO;
import com.rainng.coursesystem.model.vo.response.CommentReplyVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-04-10 00:12
 **/
@Api("评论模块")
@RequestMapping("/comment")
@RestController
public class CommentController {

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

    @ApiOperation("查询该课程所有评论")
    @GetMapping("/getComment")
    public ResultVO<CommentReplyVO> getComment(@RequestParam("courseId") Integer courseId){
        return commentService.getComment(courseId);
    }

}
