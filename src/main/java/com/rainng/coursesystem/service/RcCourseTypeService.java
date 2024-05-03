package com.rainng.coursesystem.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rainng.coursesystem.dao.mapper.CourseTypeMapper;
import com.rainng.coursesystem.model.entity.RcCourseTypeEntity;
import com.rainng.coursesystem.model.vo.response.CourseTypeSearchResVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.util.RandomNumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-02 11:21
 **/
@Service
public class RcCourseTypeService extends BaseService {
    @Autowired
    private CourseTypeMapper courseTypeMapper;

    public ResultVO<String> addCourseType(RcCourseTypeEntity entity) {

        entity.setTypeId(RandomNumUtil.getRandomNum());
        entity.setCreateTime(new Date());
        entity.setUpdatTime(new Date());
        courseTypeMapper.insert(entity);
        return result("新增课程类型成功！");
    }

    public ResultVO<String> updateCourseType(RcCourseTypeEntity entity) {
        courseTypeMapper.updateById(entity);
        return result("更新课程类型成功！");
    }

    public ResultVO<String> deleteCourseType(String typeId) {
        courseTypeMapper.deleteById(typeId);
        return result("删除课程类型成功！");
    }

    public ResultVO<List<CourseTypeSearchResVO>> getTypeMainPage(Integer typeId){
        List<RcCourseTypeEntity> typeEntities = courseTypeMapper.selectList(new QueryWrapper<>());
        List<CourseTypeSearchResVO> resTypeList = new ArrayList();
        if(typeId == null){
            typeEntities.forEach(entity -> {
                if (entity.getParentTypeId() == null){
                    CourseTypeSearchResVO resVO = new CourseTypeSearchResVO();
                    resVO.setTypeEntity(entity);
                    List<RcCourseTypeEntity> subList = new ArrayList<>();
                    typeEntities.forEach(subEntity->{
                        if(subEntity.getParentTypeId() == entity.getTypeId()){
                            subList.add(subEntity);
                        }
                    });
                    resVO.setSubTypeList(subList);
                    resTypeList.add(resVO);
                }
            });
        }else{
            RcCourseTypeEntity entity = courseTypeMapper.selectById(typeId);
            CourseTypeSearchResVO resVO = new CourseTypeSearchResVO();
            resVO.setTypeEntity(entity);
            typeEntities.forEach(subEntity->{
                List<RcCourseTypeEntity> subList = new ArrayList<>();
                if(subEntity.getParentTypeId() == entity.getTypeId()){
                    subList.add(subEntity);
                }
                resVO.setSubTypeList(subList);
                resTypeList.add(resVO);
            });
        }
        return result(resTypeList);
    }

}
