package com.rainng.coursesystem.controller;

import com.rainng.coursesystem.config.themis.annotation.Admin;
import com.rainng.coursesystem.model.entity.RcCourseTypeEntity;
import com.rainng.coursesystem.model.vo.response.CourseTypeSearchResVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.service.RcCourseTypeService;
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
 * @create: 2024-05-02 11:16
 **/
@Api("课程类型增删改查")
@Slf4j
@RequestMapping("/courseType")
@RestController
public class RcCourseTypeController {
    @Autowired
    private RcCourseTypeService rcCourseTypeService;

    @ApiOperation("新增课程类型")
    @PostMapping("/add")
    public ResultVO<String> addCourseType(@RequestBody RcCourseTypeEntity entity) {
        return rcCourseTypeService.addCourseType(entity);
    }

    @ApiOperation("更新课程类型")
    @PostMapping("/update")
    public ResultVO<String> updateCourseType(@RequestBody RcCourseTypeEntity entity) {
        return rcCourseTypeService.updateCourseType(entity);
    }

    @ApiOperation("删除课程类型")
    @GetMapping("/delete")
    public ResultVO<String> deleteCourseType(@RequestParam("typeId") String typeId) {
        return rcCourseTypeService.deleteCourseType(typeId);
    }

    @ApiOperation("查询课程类型,不传typeId默认查全部分类的第一二层")
    @GetMapping("/getTypeMainPage")
    public ResultVO<List<CourseTypeSearchResVO>> getTypeMainPage(@RequestParam(name = "typeId",required = false) Integer typeId){
        return rcCourseTypeService.getTypeMainPage(typeId);
    }
}
