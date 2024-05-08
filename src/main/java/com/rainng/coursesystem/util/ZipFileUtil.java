package com.rainng.coursesystem.util;

import com.rainng.coursesystem.model.DownloadFileDto;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @program: course-system
 * @description:
 * @author: chenqiulu
 * @create: 2024-05-08 21:08
 **/
@Slf4j
public class ZipFileUtil {
    public static byte[] zipFile(List<DownloadFileDto> downloadFileDtoList) throws Exception {

        // 将字节写到一个字节输出流里
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ZipOutputStream out = new ZipOutputStream(baos)) {
            // 创建zip file in memory
            for (DownloadFileDto downloadFileDto : downloadFileDtoList) {
                ZipEntry entry = new ZipEntry(downloadFileDto.getFileName());
                entry.setSize(downloadFileDto.getByteDataArr().length);
                out.putNextEntry(entry);
                out.write(downloadFileDto.getByteDataArr());
                out.closeEntry();
            }
        } catch (IOException e) {
            log.error("压缩zip数据出现异常", e);
            throw new RuntimeException("压缩zip包出现异常");
        }
        return baos.toByteArray();
    }
}
