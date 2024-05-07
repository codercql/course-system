package com.rainng.coursesystem.model.vo.request;

import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-03 18:37
 **/
@Data
public class ExamSearchReqVO {
    @ApiModelProperty("考试名称 模糊查询")
    private String title;

    @ApiModelProperty("考试内容 模糊查询")
    private String content;

    @ApiModelProperty("分页参数")
    private PageInfo<ExamSearchReqVO> pageParam;
}
