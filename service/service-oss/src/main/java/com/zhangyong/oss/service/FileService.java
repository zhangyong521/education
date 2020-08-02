package com.zhangyong.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 张勇
 * @Blog: https://blog.csdn.net/zy13765287861
 * @Version: 1.0
 * @Date: 2020-08-02 10:45
 * @PS: OSS的接口
 */
public interface FileService {


    /**
     * 文件上传至阿里云
     * @param file
     * @return
     */
    String uploadFileAvatar(MultipartFile file);
}
