package com.rainng.coursesystem.controller.student;

import com.rainng.coursesystem.config.themis.annotation.Student;
import com.rainng.coursesystem.controller.BaseController;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.service.student.CourseSelectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.*;


@Api("学生课程管理")
@Student
@RequestMapping("/student/course/select")
//@RestController
public class CourseSelectController extends BaseController {
    private final CourseSelectService service;

    public CourseSelectController(CourseSelectService service) {
        this.service = service;
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String courseName, String teacherName) {
        return service.getPageCount(courseName, teacherName);
    }


    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String courseName, String teacherName) {
        return service.getPage(index, courseName, teacherName);
    }

    @PostMapping("/{id}")
    public ResultVO create(@PathVariable Integer id) {
        return service.create(id);
    }

    /***
    * @Description:  分页查询所有课程
    * @Param: []
    * @return: com.rainng.coursesystem.model.vo.response.ResultVO
    * @Author: chenqiulu
    * @Date: 2024/4/12
    */
//    @PostMapping
//    public ResultVO getMainPage(@RequestBody reqVO){
//
//    }
}
