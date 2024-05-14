package com.rainng.coursesystem.controller;

import com.rainng.coursesystem.model.entity.RcHomeworkEntity;
import com.rainng.coursesystem.model.vo.response.RcHomeworkDetailVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.service.RcHomeworkService;
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
 * @create: 2024-05-03 17:15
 **/
@Api("作业增删改查")
@Slf4j
@RequestMapping("/homework")
@RestController
public class RcHomeworkController {
    @Autowired
    private RcHomeworkService rcHomeworkService;


    @ApiOperation("通过选课id查询作业,课程Id为空查询所有作业")
    @GetMapping("/getHomeworkByCourseId")
    public ResultVO<List<RcHomeworkDetailVO>> getHomeworkByScId(@RequestParam(value = "courseId",required = false) Integer courseId) {
        return rcHomeworkService.getHomeworkByCourseId(courseId);
    }

    @ApiOperation("新增作业")
    @PostMapping("/add")
    public ResultVO<String> addHomework(@RequestBody RcHomeworkEntity entity){
        return rcHomeworkService.addHomework(entity);
    }

    @ApiOperation("更新作业")
    @PostMapping("/update")
    public ResultVO<String> updateHomework(@RequestBody RcHomeworkEntity entity){
        return rcHomeworkService.updateHomework(entity);
    }

    @ApiOperation("删除作业")
    @GetMapping("/delete")
    public ResultVO<String> deleteHomework(@RequestParam("homeworkId") String homeworkId){
        return rcHomeworkService.deleteHomework(homeworkId);
    }

    @ApiOperation("通过学生id查询所有作业")
    @GetMapping("/getHomeworkListByStudentId")
    public ResultVO<List<RcHomeworkDetailVO>> getHomeworkListByStudentId(@RequestParam("studentId") Integer studentId) {
        return rcHomeworkService.getHomeworkListByStudentId(studentId);
    }
}
