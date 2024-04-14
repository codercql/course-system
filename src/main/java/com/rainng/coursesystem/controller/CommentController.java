package com.rainng.coursesystem.controller;

import com.rainng.coursesystem.model.vo.request.CommentVO;
import com.rainng.coursesystem.model.vo.request.ReplyVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-04-10 00:12
 **/
@Api("评论增删改查")
@RequestMapping("/comment")
@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;
    //新增评论 更新-新增回复 查询评论

    @ApiModelProperty("新增评论")
    @PostMapping("/addComment")
    public ResultVO addComment(@RequestBody CommentVO vo) {
         return commentService.addComment(vo);
    }

    @ApiModelProperty("新增回复")
    @PostMapping("/addReply")
    public ResultVO addReply(@RequestBody ReplyVO vo) {
         return commentService.addReply(vo);
    }

    @ApiModelProperty("查询该课程所有评论")
    @GetMapping("/getComment")
    public ResultVO getComment(@RequestParam("courseId") Integer courseId){
        return commentService.getComment(courseId);
    }

}
