package com.rainng.coursesystem.model;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-08 21:09
 **/

import lombok.Data;

import java.io.Serializable;

/**
 * @author YuePeng
 * @date 2022/12/22 11:52
 * 存放文件字节流和文件名
 */
@Data
public class DownloadFileDto implements Serializable {

    private static final long serialVersionUID = 2648469408255550980L;

    // 存放文件名
    private String fileName = "";

    // 存放文件字节流
    private byte[] byteDataArr = new byte[0];

}
