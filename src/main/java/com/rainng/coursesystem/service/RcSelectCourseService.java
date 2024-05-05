package com.rainng.coursesystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rainng.coursesystem.dao.mapper.RcSelectCourseMapper;
import com.rainng.coursesystem.model.entity.RcSelectCourseEntity;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.util.RandomNumUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;


/**
 *
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2024-05-05 16:42:31
 */
@Service
public class RcSelectCourseService extends BaseService{
    @Autowired
    private RcSelectCourseMapper rcSelectCourseMapper;


    public ResultVO<RcSelectCourseEntity> getInfoByScId(Integer scId) {
        RcSelectCourseEntity rcSelectCourseEntity = rcSelectCourseMapper.selectById(scId);
        return result(rcSelectCourseEntity);
    }


    public ResultVO<String> addSelectCourse(RcSelectCourseEntity entity){
        LambdaQueryWrapper<RcSelectCourseEntity> eq = new LambdaQueryWrapper<RcSelectCourseEntity>().eq(RcSelectCourseEntity::getScCourseId, entity.getScCourseId()).
                eq(RcSelectCourseEntity::getScStudentId, entity.getScStudentId());
        List<RcSelectCourseEntity> rcSelectCourseEntities = rcSelectCourseMapper.selectList(eq);
        if(rcSelectCourseEntities.size()>0){
            return failedResult("已存在选课关系，不能新增");
        }
        entity.setScId(RandomNumUtil.getRandomNum());
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        rcSelectCourseMapper.insert(entity);
        return result("选课关系新增成功！");
    }


    public ResultVO<String> updateSelectCourse(RcSelectCourseEntity entity){
        rcSelectCourseMapper.updateById(entity);
        return result("选课关系更新成功！");
    }

}
