package com.rainng.coursesystem.controller.admin;

import com.rainng.coursesystem.config.themis.annotation.Admin;
import com.rainng.coursesystem.controller.BaseController;
import com.rainng.coursesystem.model.entity.TeacherEntity;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.service.admin.TeacherService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Admin(Admin.TEACHER_MANAGE)
@RequestMapping("/admin/teacher")
@RestController
public class TeacherController extends BaseController {
    private final TeacherService service;

    public TeacherController(TeacherService service) {
        this.service = service;
    }

    @ApiOperation("教师信息查询")
    @GetMapping("/{id}")
    public ResultVO get(@PathVariable Integer id) {
        return service.get(id);
    }

    @PostMapping
    public ResultVO create(@RequestBody @Validated TeacherEntity entity) {
        return service.create(entity);
    }

    @ApiOperation("删除教师信息")
    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        return service.delete(id);
    }

    @ApiOperation("教师信息更新")
    @PostMapping("/update")
    public ResultVO update(@RequestBody @Validated TeacherEntity entity) {
        return service.update(entity);
    }

    @RequestMapping("/page/count")
    public ResultVO getPageCount(String departmentName, String name) {
        return service.getPageCount(departmentName, name);
    }

    @RequestMapping("/page")
    public ResultVO getPage(String departmentName, String name) {
        return service.getPage(1, departmentName, name);
    }

    @RequestMapping("/page/{index}")
    public ResultVO getPage(@PathVariable Integer index, String departmentName, String name) {
        return service.getPage(index, departmentName, name);
    }

    @Admin
    @RequestMapping("/names")
    public ResultVO listName() {
        return service.listName();
    }

    @GetMapping("/getTeacherList")
    public ResultVO<List<TeacherEntity>> getTeacherList(@RequestParam("teacherName")  String teacherName){
        return service.getTeacherList(teacherName);
    }
}
