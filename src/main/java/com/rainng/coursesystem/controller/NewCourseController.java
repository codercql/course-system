package com.rainng.coursesystem.controller;

import com.rainng.coursesystem.model.vo.request.CourseSearchReqVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.service.admin.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: course-system
 * @description: 课程相关
 * @author: chenqiulu
 * @create: 2024-04-12 21:59
 **/
@Api("新版课程增删改查")
@Slf4j
@RequestMapping("/course")
@RestController
public class NewCourseController extends BaseController{
    @Autowired
    private CourseService courseService;


    public ResultVO getMainPage(CourseSearchReqVO vo){
        return result("");
    }



}
