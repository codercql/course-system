package com.rainng.coursesystem.service.admin;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.rainng.coursesystem.dao.mapper.CourseMapper;
import com.rainng.coursesystem.manager.admin.CourseManager;
import com.rainng.coursesystem.model.bo.CourseItemBO;
import com.rainng.coursesystem.model.entity.CourseEntity;
import com.rainng.coursesystem.model.vo.request.CourseSearchReqVO;
import com.rainng.coursesystem.model.vo.response.CourseSearchResVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.model.vo.response.table.CourseItemVO;
import com.rainng.coursesystem.service.BaseService;
import com.rainng.coursesystem.util.LessonTimeConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.ExceptionUtil;
import org.apache.tomcat.util.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class CourseService extends BaseService {
    private final CourseManager manager;
    private final LessonTimeConverter lessonTimeConverter;

    @Autowired
    private CourseMapper courseMapper;

    public CourseService(CourseManager manager, LessonTimeConverter lessonTimeConverter) {
        this.manager = manager;
        this.lessonTimeConverter = lessonTimeConverter;
    }

    public ResultVO getPageCount(String departmentName, String teacherName, String name) {
        return result(manager.getPageCount(departmentName, teacherName, name));
    }

    public ResultVO getPage(Integer index, String departmentName, String teacherName, String name) {
        List<CourseItemBO> boList = manager.getPage(index, departmentName, teacherName, name);
        List<CourseItemVO> voList = new ArrayList<>(boList.size());

        for (CourseItemBO bo : boList) {
            CourseItemVO vo = new CourseItemVO();
            BeanUtils.copyProperties(bo, vo);
            vo.setTime(lessonTimeConverter.covertTimePart(bo.getTime()));
            voList.add(vo);
        }

        return result(voList);
    }

    public ResultVO get(Integer id) {
        CourseEntity entity = manager.get(id);
        if (entity == null) {
            return failedResult("课程Id: " + id + "不存在!");
        }

        return result(entity);
    }

    public ResultVO<String> update(CourseEntity entity) {
        CourseEntity origin = manager.get(entity.getId());
        if (origin == null) {
            return failedResult("课程Id: " + entity.getId() + "不存在!");
        }
        if (manager.getTeacherById(entity.getTeacherId()) == null) {
            return failedResult("授课教师Id: " + entity.getTeacherId() + "不存在!");
        }
        entity.setSelectedCount(origin.getSelectedCount());
        entity.setUptTm(new Date());

        manager.update(entity);
        return result("更新成功");
    }

    public ResultVO delete(Integer id) {
        if (manager.get(id) == null) {
            return failedResult("课程Id: " + id + "不存在!");
        }
        if (manager.hasStudentCourse(id)) {
            return failedResult("还有学生未退选此课程");
        }

        manager.delete(id);
        return result("删除成功");
    }

    public ResultVO<String> create(CourseEntity entity) {
        if (manager.get(entity.getId()) != null) {
            return failedResult("课程Id: " + entity.getId() + "已存在!");
        }
        if (manager.getTeacherById(entity.getTeacherId()) == null) {
            return failedResult("授课教师Id: " + entity.getTeacherId() + "不存在!");
        }
        entity.setCrtTm(new Date());
        manager.create(entity);
        return result("添加成功");
    }

    public ResultVO<String> getCoverByCourseId(Integer courseId) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return failedResult("requestAttributes == null");
        }
        HttpServletResponse response = requestAttributes.getResponse();
        if (response == null) {
            return failedResult("response == null");
        }
        CourseEntity entity = manager.get(courseId);
        try {
            response.getOutputStream().write(entity.getCover());
        } catch (IOException e) {
            log.error("文件流写入失败={}", e.getMessage());
            e.printStackTrace();
        }
        return result("获取封面成功");
    }

    public ResultVO listName() {
        return result(manager.listName());
    }



}
