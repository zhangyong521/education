package com.zhangyong.oss.controller;

import com.baomidou.mybatisplus.extension.api.R;
import com.zhangyong.oss.service.FileService;
import com.zhangyong.utils.ResultMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: 张勇
 * @Blog: https://blog.csdn.net/zy13765287861
 * @Version: 1.0
 * @Date: 2020-08-02 10:45
 * @PS: OSS的控制器
 */

@Api(description="阿里云文件管理")
@CrossOrigin //跨域
@RestController
@RequestMapping("/eduoss/fileoss")
public class FileController {

	@Autowired
	private FileService fileService;

	/**
	 * 文件上传
	 *
	 * @param file
	 */
	@ApiOperation(value = "文件上传")
	//上传头像的方法
	@PostMapping
	public ResultMsg uploadOssFile(MultipartFile file) {
		//获取上传文件  MultipartFile
		//返回上传到oss的路径
		String url = fileService.uploadFileAvatar(file);
		return ResultMsg.ok().data("url",url);
	}

}