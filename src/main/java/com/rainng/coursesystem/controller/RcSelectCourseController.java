package com.rainng.coursesystem.controller;

import com.rainng.coursesystem.model.entity.RcProcessEntity;
import com.rainng.coursesystem.model.entity.RcSelectCourseEntity;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.service.RcSelectCourseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2024-05-05 16:42:31
 */
@RestController
@RequestMapping("generator/selectCourse")
public class RcSelectCourseController {
    @Autowired
    private RcSelectCourseService rcSelectCourseService;

    @ApiOperation("通过选课id查询选课详情")
    @PostMapping("/getInfoByScId")
    public ResultVO<RcSelectCourseEntity> getInfoByScId(@RequestParam("scId") Integer scId) {
        return rcSelectCourseService.getInfoByScId(scId);
    }

    @ApiOperation("新增选课关系")
    @PostMapping("/add")
    public ResultVO<String> addSelectCourse(@RequestBody RcSelectCourseEntity entity){
        return rcSelectCourseService.addSelectCourse(entity);
    }

    @ApiOperation("更新选课关系")
    @PostMapping("/update")
    public ResultVO<String> updateSelectCourse(@RequestBody RcSelectCourseEntity entity){
        return rcSelectCourseService.updateSelectCourse(entity);
    }

}
