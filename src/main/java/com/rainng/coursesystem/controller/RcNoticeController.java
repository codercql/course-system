package com.rainng.coursesystem.controller;

import com.github.pagehelper.PageInfo;
import com.rainng.coursesystem.model.entity.RcNoticeEntity;
import com.rainng.coursesystem.model.vo.request.NoticeSearchReqVO;
import com.rainng.coursesystem.model.vo.response.NoticeSearchResVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.service.RcNoticeService;
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
 * @create: 2024-05-03 18:19
 **/
@Api("公告增删改查")
@Slf4j
@RequestMapping("/notice")
@RestController
public class RcNoticeController {
    @Autowired
    private RcNoticeService rcNoticeService;

    @ApiOperation("公告首页查询")
    @PostMapping("/getNoticeMainPage")
    public ResultVO<PageInfo<NoticeSearchResVO>> getNoticeMainPage(@RequestBody NoticeSearchReqVO vo) {
        return rcNoticeService.getNoticeMainPage(vo);
    }

    @ApiOperation("新增公告")
    @PostMapping("/add")
    public ResultVO<String> addNotice(@RequestBody RcNoticeEntity entity){
        return rcNoticeService.addNotice(entity);
    }

    @ApiOperation("更新公告")
    @PostMapping("/update")
    public ResultVO<String> updateNotice(@RequestBody RcNoticeEntity entity){
        return rcNoticeService.updateNotice(entity);
    }

    @ApiOperation("删除公告")
    @GetMapping("/delete")
    public ResultVO<String> deleteNotice(@RequestParam("noticeId") String noticeId){
        return rcNoticeService.deleteNotice(noticeId);
    }
}
