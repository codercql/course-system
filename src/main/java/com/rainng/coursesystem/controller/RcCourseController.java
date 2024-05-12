package com.rainng.coursesystem.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.rainng.coursesystem.config.themis.annotation.Admin;
import com.rainng.coursesystem.config.themis.annotation.Teacher;
import com.rainng.coursesystem.model.entity.RcCourseEntity;
import com.rainng.coursesystem.model.vo.request.CourseSearchReqVO;
import com.rainng.coursesystem.model.vo.response.CourseSearchResVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.service.RcCourseService;
import com.rainng.coursesystem.util.RandomNumUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * @program: course-system
 * @description: 课程相关, 不做权限管控
 * @author: chenqiulu
 * @create: 2024-04-12 21:59
 **/
//@Admin(Admin.ALL)
@Api("新版课程增删改查")
@Slf4j
@RequestMapping("/course")
@RestController("courseController")
public class RcCourseController extends BaseController {
    @Autowired
    private RcCourseService rcCourseService;


    @ApiOperation("课程首页查询接口")
    @PostMapping("/getMainPage")
    public ResultVO<PageInfo<CourseSearchResVO>> getMainPage(@RequestBody CourseSearchReqVO vo) {
        return rcCourseService.getMainPage(vo);
    }

    @ApiOperation("新增课程")
    @PostMapping("/add")
    public ResultVO<String> addCourse(@RequestParam("entity") String entity,
                                      @RequestParam(value = "file", required = false) MultipartFile file,
                                      @RequestParam(value = "cover", required = false) MultipartFile cover) {
        RcCourseEntity courseEntity = JSONObject.parseObject(entity, RcCourseEntity.class);
        if (null != file) {
            try {
                byte[] bytes = file.getBytes();
                courseEntity.setFile(bytes);

            } catch (IOException e) {
                e.printStackTrace();
                log.error("文件获取失败");
            }
        }
        if (null != cover) {
            try {
                byte[] bytes = cover.getBytes();
                courseEntity.setCover(bytes);

            } catch (IOException e) {
                e.printStackTrace();
                log.error("封面获取失败");
            }
        }
        return rcCourseService.create(courseEntity);
    }

    @ApiOperation("更新课程")
    @PostMapping("/update")
    public ResultVO<String> updateCourse(@RequestParam("entity") String entity,
                                         @RequestParam(value = "file", required = false) MultipartFile file,
                                         @RequestParam(value = "cover", required = false) MultipartFile cover) {
        RcCourseEntity courseEntity = JSONObject.parseObject(entity, RcCourseEntity.class);
        if (null != file) {
            try {
                byte[] bytes = file.getBytes();
                courseEntity.setFile(bytes);

            } catch (IOException e) {
                e.printStackTrace();
                log.error("文件获取失败");
            }
        }
        if (null != cover) {
            try {
                byte[] bytes = cover.getBytes();
                courseEntity.setCover(bytes);

            } catch (IOException e) {
                e.printStackTrace();
                log.error("封面获取失败");
            }
        }
        courseEntity.setUpdateTime(new Date());
        return rcCourseService.update(courseEntity);
    }


    @ApiOperation("删除课程")
    @GetMapping("/delete")
    public ResultVO deleteCourse(@RequestParam("courseId") Integer courseId) {
        return rcCourseService.delete(courseId);
    }

    @ApiOperation("通过课程Id列表获取封面和文件压缩包文件，封面图片命名 courseId.jpg 课程文件命名 courseId.zip")
    @PostMapping("/getCoverByCourseIdList")
    public ResultVO<String> getCoverByCourseIdList(@RequestBody List<Integer> courseId, HttpServletResponse response) {
        return rcCourseService.getCoverByCourseIdList(courseId, response);
    }

//    @ApiOperation("通过课程Id获取文件")
//    @GetMapping("/getFileByCourseId")
    public ResultVO<String> getFileByCourseId(@RequestParam("courseId") Integer courseId, HttpServletResponse response) {
        return rcCourseService.getFileByCourseId(courseId, response);
    }

    @ApiOperation("根据学生id获取课程列表")
    @GetMapping("/getCourseListByStudentId")
    public ResultVO<List<RcCourseEntity>> getCourseListByStudentId(@RequestParam("studentId") Integer studentId) {
        return rcCourseService.getCourseListByStudentId(studentId);
    }

}
