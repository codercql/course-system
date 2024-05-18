package com.rainng.coursesystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.rainng.coursesystem.dao.mapper.ExamMapper;
import com.rainng.coursesystem.dao.mapper.HomeworkMapper;
import com.rainng.coursesystem.dao.mapper.RcCourseMapper;
import com.rainng.coursesystem.dao.mapper.RcSelectCourseMapper;
import com.rainng.coursesystem.model.entity.RcCourseEntity;
import com.rainng.coursesystem.model.entity.RcExamEntity;
import com.rainng.coursesystem.model.entity.RcSelectCourseEntity;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.model.vo.response.SelectCourseDetailVO;
import com.rainng.coursesystem.util.RandomNumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;


/**
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2024-05-05 16:42:31
 */
@Service
public class RcSelectCourseService extends BaseService {
    @Autowired
    private RcSelectCourseMapper rcSelectCourseMapper;
    @Autowired
    private RcCourseMapper rcCourseMapper;
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private HomeworkMapper homeworkMapper;


    public ResultVO<RcSelectCourseEntity> getInfoByScId(Integer scId) {
        RcSelectCourseEntity rcSelectCourseEntity = rcSelectCourseMapper.selectById(scId);
        return result(rcSelectCourseEntity);
    }


    @Transactional(rollbackFor = Exception.class)
    public ResultVO<String> addSelectCourse(RcSelectCourseEntity entity) {
        LambdaQueryWrapper<RcSelectCourseEntity> eq = new LambdaQueryWrapper<RcSelectCourseEntity>().eq(RcSelectCourseEntity::getScCourseId, entity.getScCourseId()).
                eq(RcSelectCourseEntity::getScStudentId, entity.getScStudentId());
        List<RcSelectCourseEntity> rcSelectCourseEntities = rcSelectCourseMapper.selectList(eq);
        if (rcSelectCourseEntities.size() > 0) {
            return failedResult("已存在选课关系，不能新增");
        }
        entity.setScId(RandomNumUtil.getRandomNum());
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());

        //更新课程表选课人数
        RcCourseEntity rcCourseEntity = rcCourseMapper.selectById(entity.getScCourseId());
        int count = rcCourseEntity.getCourseSelectedCount();
        rcCourseEntity.setCourseSelectedCount(count + 1);
        rcCourseEntity.setUpdateTime(new Date());

        // 查询考试
        entity.setExamId(rcCourseEntity.getCourseExamId());
        rcSelectCourseMapper.insert(entity);
        rcCourseMapper.updateById(rcCourseEntity);
        return result("选课关系新增成功！");
    }


    public ResultVO<String> updateSelectCourse(RcSelectCourseEntity entity) {
        entity.setUpdateTime(new Date());
        rcSelectCourseMapper.updateById(entity);
        return result("选课关系更新成功！");
    }

    public ResultVO<List<SelectCourseDetailVO>> getCourseListByStudentId(@RequestParam("studentId") String studentId) {
        return result(rcSelectCourseMapper.getCourseListByStudentId(studentId));
    }

}
