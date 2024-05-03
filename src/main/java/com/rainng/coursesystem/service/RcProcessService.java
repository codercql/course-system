package com.rainng.coursesystem.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rainng.coursesystem.dao.mapper.ProcessMapper;
import com.rainng.coursesystem.model.entity.RcProcessEntity;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.util.RandomNumUtil;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-03 17:00
 **/
@Service
public class RcProcessService extends BaseService {

    @Autowired
    private ProcessMapper processMapper;

    public ResultVO<RcProcessEntity> getProcessByScId(@RequestParam("scId") Integer scId) {
        LambdaQueryWrapper<RcProcessEntity> eq = new LambdaQueryWrapper<RcProcessEntity>().eq(RcProcessEntity::getScId, scId);
        List<RcProcessEntity> rcProcessEntities = processMapper.selectList(eq);
        return result(rcProcessEntities.get(0));
    }

    @ApiModelProperty("新增学习进度")
    @PostMapping("/add")
    public ResultVO<String> addProcess(@RequestBody RcProcessEntity entity) {
        entity.setProcessId(RandomNumUtil.getRandomNum());
        processMapper.insert(entity);
        return result("新增学习进度成功！");
    }

    @ApiModelProperty("更新学习进度")
    @PostMapping("/update")
    public ResultVO<String> updateProcess(@RequestBody RcProcessEntity entity) {
        processMapper.updateById(entity);
        return result("更新学习进度成功！");
    }
}
