package com.rainng.coursesystem.controller;

import com.rainng.coursesystem.model.entity.RcProcessEntity;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.service.RcProcessService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-03 16:58
 **/
@Api("学习进度增改查")
@Slf4j
@RequestMapping("/process")
@RestController
public class RcProcessController {
    @Autowired
    private RcProcessService rcProcessService;


    @ApiOperation("通过选课id查询学习进度")
    @PostMapping("/getProcessByScId")
    public ResultVO<RcProcessEntity> getProcessByScId(@RequestParam("scId") Integer scId) {
        return rcProcessService.getProcessByScId(scId);
    }

    @ApiOperation("新增学习进度")
    @PostMapping("/add")
    public ResultVO<String> addProcess(@RequestBody RcProcessEntity entity){
        return rcProcessService.addProcess(entity);
    }

    @ApiOperation("更新学习进度")
    @PostMapping("/update")
    public ResultVO<String> updateProcess(@RequestBody RcProcessEntity entity){
        return rcProcessService.updateProcess(entity);
    }
}
