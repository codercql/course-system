package com.rainng.coursesystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rainng.coursesystem.dao.mapper.HomeworkMapper;
import com.rainng.coursesystem.dao.mapper.RcSelectCourseMapper;
import com.rainng.coursesystem.model.entity.RcHomeworkEntity;
import com.rainng.coursesystem.model.entity.RcSelectCourseEntity;
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

    @ApiModelProperty("通过选课id查询作业")
    @PostMapping("/getHomeworkByScId")
    public ResultVO<RcHomeworkEntity> getHomeworkByScId(Integer scId) {
        LambdaQueryWrapper<RcHomeworkEntity> eq = new LambdaQueryWrapper<RcHomeworkEntity>().eq(RcHomeworkEntity::getScId, scId);
        List<RcHomeworkEntity> rcHomeworkEntities = homeworkMapper.selectList(eq);
        return result(rcHomeworkEntities.get(0));
    }

    public ResultVO<String> addHomework(RcHomeworkEntity entity){
        entity.setHomeworkId(RandomNumUtil.getRandomNum());
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        homeworkMapper.insert(entity);
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

    public ResultVO<List<RcHomeworkEntity>> getHomeworkListByStudentId(Integer studentId) {
        List<RcSelectCourseEntity> rcSelectCourseEntities = rcSelectCourseMapper.selectList(new LambdaQueryWrapper<RcSelectCourseEntity>().eq(RcSelectCourseEntity::getScStudentId, studentId));
        List<Integer> homeworkIdList = rcSelectCourseEntities.stream().map(RcSelectCourseEntity::getHomeworkId).distinct().collect(Collectors.toList());
        return result(homeworkMapper.selectBatchIds(homeworkIdList));
    }
}
