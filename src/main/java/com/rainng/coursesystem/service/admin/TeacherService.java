package com.rainng.coursesystem.service.admin;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rainng.coursesystem.dao.mapper.TeacherMapper;
import com.rainng.coursesystem.manager.admin.TeacherManager;
import com.rainng.coursesystem.model.entity.TeacherEntity;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.service.BaseService;
import com.rainng.coursesystem.service.UserService;
import com.rainng.coursesystem.util.RandomNumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class TeacherService extends BaseService {
    private final TeacherManager manager;
    private final UserService userService;
    @Autowired
    private TeacherMapper teacherMapper;

    public TeacherService(TeacherManager manager, UserService userService) {
        this.manager = manager;
        this.userService = userService;
    }

    public ResultVO getPageCount(String departmentName, String name) {
        return result(manager.getPageCount(departmentName, name));
    }

    public ResultVO getPage(Integer index, String departmentName, String name) {
        return result(manager.getPage(index, departmentName, name));
    }

    public ResultVO get(Integer id) {
        TeacherEntity entity = manager.get(id);
        if (entity == null) {
            return failedResult("教师Id: " + id + "不存在!");
        }

        entity.setTeacherPassword("");
        return result(entity);
    }

    public ResultVO update(TeacherEntity entity) {
        TeacherEntity originEntity = manager.get(entity.getTeacherId());
        if (originEntity == null) {
            return failedResult("教师Id: " + entity.getTeacherId() + "不存在!");
        }
//        if (manager.getDepartmentById(entity.getDepartmentId()) == null) {
//            return failedResult("所属系Id: " + entity.getDepartmentId() + "不存在!");
//        }

        if (entity.getTeacherPassword().equals("")) {
            entity.setTeacherPassword(originEntity.getTeacherPassword());
        } else {
            entity.setTeacherPassword(userService.computePasswordHash(entity.getTeacherPassword()));
        }

        manager.update(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer id) {
        if (manager.get(id) == null) {
            return failedResult("教师Id: " + id + "不存在!");
        }
//        if (manager.hasCourse(id)) {
//            return failedResult("此教师还有教授的课程未被删除");
//        }

        manager.delete(id);
        return result("删除成功");
    }

    public ResultVO create(TeacherEntity entity) {
        entity.setTeacherId(RandomNumUtil.getRandomNum());
        entity.setTeacherPassword(userService.computePasswordHash(entity.getTeacherPassword()));

        manager.create(entity);
        return result("添加成功");
    }

    public ResultVO listName() {
        return result(manager.listName());
    }

    public ResultVO<List<TeacherEntity>> getTeacherList(String teacherName) {
        LambdaQueryWrapper<TeacherEntity> qw = new LambdaQueryWrapper<>();
        if(!StringUtils.isEmpty(teacherName)){
            qw.like(TeacherEntity::getTeacherName,teacherName);
        }
        return result(teacherMapper.selectList(qw));
    }

}
