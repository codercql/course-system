package com.rainng.coursesystem.controller;

import com.github.pagehelper.PageInfo;
import com.rainng.coursesystem.config.themis.annotation.Admin;
import com.rainng.coursesystem.model.entity.RcExamEntity;
import com.rainng.coursesystem.model.vo.request.ExamSearchReqVO;
import com.rainng.coursesystem.model.vo.request.RcExamReqVO;
import com.rainng.coursesystem.model.vo.response.ExamDetailVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.service.RcExamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @ApiOperation("考试首页查询")
    @PostMapping("/getExamMainPage")
    public ResultVO<PageInfo<ExamDetailVO>> getExamMainPage(@RequestBody ExamSearchReqVO vo) {
        return rcExamService.getExamMainPage(vo);
    }

    @ApiOperation("新增考试")
    @PostMapping("/add")
    public ResultVO<String> addExam(@RequestBody RcExamReqVO vo) {
        return rcExamService.addExam(vo);
    }

    @ApiOperation("更新考试")
    @PostMapping("/update")
    public ResultVO<String> updateExam(@RequestBody RcExamReqVO vo) {
        return rcExamService.updateExam(vo);
    }

    @ApiOperation("删除考试")
    @GetMapping("/delete")
    public ResultVO<String> deleteExam(@RequestParam("examId") String examId) {
        return rcExamService.deleteExam(examId);
    }

    @ApiOperation("通过学生Id获取考试列表")
    @GetMapping("/getExamListByStudentId")
    public ResultVO<List<ExamDetailVO>> getExamListByStudentId(@RequestParam("studentId") Integer studentId){
        return rcExamService.getExamListByStudentId(studentId);
    }

    @ApiOperation("通过老师Id获取考试列表")
    @GetMapping("/getExamListByTeacherId")
    public ResultVO<List<ExamDetailVO>> getExamListByTeacherId(@RequestParam("teacherId") Integer teacherId){
        return rcExamService.getExamListByTeacherId(teacherId);
    }
}
