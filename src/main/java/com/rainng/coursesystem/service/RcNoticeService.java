package com.rainng.coursesystem.service;

import com.github.pagehelper.PageInfo;
import com.rainng.coursesystem.dao.mapper.NoticeMapper;
import com.rainng.coursesystem.model.entity.RcNoticeEntity;
import com.rainng.coursesystem.model.vo.request.NoticeSearchReqVO;
import com.rainng.coursesystem.model.vo.response.NoticeSearchResVO;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import com.rainng.coursesystem.util.RandomNumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-03 17:00
 **/
@Service
public class RcNoticeService extends BaseService{
    @Autowired
    private NoticeMapper noticeMapper;

    public ResultVO<PageInfo<NoticeSearchResVO>> getNoticeMainPage(NoticeSearchReqVO vo) {
        List<NoticeSearchResVO> noticeMainPage = noticeMapper.getNoticeMainPage(vo);
        PageInfo<NoticeSearchResVO> pageInfo = new PageInfo<>(noticeMainPage);
        return result(pageInfo);
    }

    public ResultVO<String> addNotice(RcNoticeEntity entity){
        entity.setNoticeId(RandomNumUtil.getRandomNum());
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        noticeMapper.insert(entity);
        return result("公告新增成功！");
    }

    public ResultVO<String> updateNotice(RcNoticeEntity entity){
        entity.setUpdateTime(new Date());
        noticeMapper.updateById(entity);
        return result("公告更新成功！");
    }


    public ResultVO<String> deleteNotice(String noticeId){
        noticeMapper.deleteById(noticeId);
        return result("公告删除成功！");
    }
}
