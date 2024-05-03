package com.rainng.coursesystem.controller;

import com.github.pagehelper.PageInfo;
import com.rainng.coursesystem.config.themis.annotation.Admin;
import com.rainng.coursesystem.model.entity.RcExamEntity;
import com.rainng.coursesystem.model.vo.request.ExamSearchReqVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.service.RcExamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-03 17:49
 **/
@Api("考试增删改查")
@Slf4j
@RequestMapping("/exam")
@RestController
public class RcExamController {
    @Autowired
    private RcExamService rcExamService;

    @ApiModelProperty("考试首页查询")
    @PostMapping("/getExamMainPage")
    public ResultVO<PageInfo<RcExamEntity>> getExamMainPage(@RequestBody ExamSearchReqVO vo) {
        return rcExamService.getExamMainPage(vo);
    }

    @ApiModelProperty("新增作业")
    @PostMapping("/add")
    public ResultVO<String> addExam(@RequestBody RcExamEntity entity) {
        return rcExamService.addExam(entity);
    }

    @ApiModelProperty("更新作业")
    @PostMapping("/update")
    public ResultVO<String> updateExam(@RequestBody RcExamEntity entity) {
        return rcExamService.updateExam(entity);
    }

    @ApiModelProperty("删除作业")
    @PostMapping("/delete")
    public ResultVO<String> deleteExam(@RequestParam("examId") String examId) {
        return rcExamService.deleteExam(examId);
    }
}
