package com.rainng.coursesystem.model.vo.request;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-03 18:26
 **/
@Data
public class NoticeSearchReqVO {

    @ApiModelProperty("公告标题，模糊查询")
    private String title;

    @ApiModelProperty("公告内容，模糊查询")
    private String content;

    @ApiModelProperty("分页参数")
    private PageInfo<NoticeSearchReqVO> pageParam;
}
