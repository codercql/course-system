package com.rainng.coursesystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.rainng.coursesystem.dao.mapper.HomeworkMapper;
import com.rainng.coursesystem.dao.mapper.RcSelectCourseMapper;
import com.rainng.coursesystem.model.entity.RcHomeworkEntity;
import com.rainng.coursesystem.model.entity.RcSelectCourseEntity;
import com.rainng.coursesystem.model.vo.response.RcHomeworkDetailVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.util.RandomNumUtil;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-03 17:00
 **/
@Service
public class RcHomeworkService extends BaseService{
    @Autowired
    private HomeworkMapper homeworkMapper;
    @Autowired
    private RcSelectCourseMapper rcSelectCourseMapper;

    @ApiModelProperty("通过课程id查询作业")
    @PostMapping("/getHomeworkByCourseId")
    public ResultVO<List<RcHomeworkDetailVO>> getHomeworkByCourseId(Integer courseId) {
        return result(homeworkMapper.selectHomeworkDetailByCourseId(courseId));
    }

    public ResultVO<String> addHomework(RcHomeworkEntity entity){
        int homeworkId = RandomNumUtil.getRandomNum();
        entity.setHomeworkId(homeworkId);
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        homeworkMapper.insert(entity);
        if (entity.getCourseId() != null) {
            LambdaQueryWrapper<RcSelectCourseEntity> eq = new LambdaQueryWrapper<RcSelectCourseEntity>().eq(RcSelectCourseEntity::getScCourseId, entity.getCourseId());
            List<RcSelectCourseEntity> rcSelectCourseEntities = rcSelectCourseMapper.selectList(eq);
            rcSelectCourseEntities.forEach(sc -> {
                sc.setUpdateTime(new Date());
                sc.setHomeworkId(homeworkId);
                rcSelectCourseMapper.updateById(sc);
            });
        }
        return result("新增作业成功！");
    }


    public ResultVO<String> updateHomework(RcHomeworkEntity entity){
        entity.setUpdateTime(new Date());
        homeworkMapper.updateById(entity);
        return result("更新作业成功！");
    }


    public ResultVO<String> deleteHomework(String homeworkId){
        homeworkMapper.deleteById(homeworkId);
        return result("更新作业成功！");
    }

    public ResultVO<List<RcHomeworkDetailVO>> getHomeworkListByStudentId(Integer studentId) {
        List<RcSelectCourseEntity> rcSelectCourseEntities = rcSelectCourseMapper.selectList(new LambdaQueryWrapper<RcSelectCourseEntity>().eq(RcSelectCourseEntity::getScStudentId, studentId));
        List<Integer> homeworkIdList = rcSelectCourseEntities.stream().map(RcSelectCourseEntity::getHomeworkId).distinct().collect(Collectors.toList());
        if(CollectionUtils.isEmpty(homeworkIdList)){
            return result(null);
        }
        return result(homeworkMapper.selectHomeworkDetailByHomeworkIdList(homeworkIdList));
    }
}
