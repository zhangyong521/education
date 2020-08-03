package com.zhangyong.eduservice.controller;


import com.zhangyong.eduservice.entity.subjectvo.OneSubject;
import com.zhangyong.eduservice.service.EduSubjectService;
import com.zhangyong.utils.ResultMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author zhangyong
 * @since 2020-08-02
 */
@Api(description = "分类管理")
@RestController
@RequestMapping("/eduservice/subject")
@CrossOrigin
public class EduSubjectController {
    @Autowired
    private EduSubjectService subjectService;

    /**
     * 添加课程分类
     * 获取上传过来的文件，把文件内容读取出来
     *
     * @param file
     * @return
     */
    @ApiOperation(value = "Excel批量导入")
    @PostMapping("addSubject")
    public ResultMsg addSubject(MultipartFile file) {
        //上传过来的Excel文件
        subjectService.saveSubject(file, subjectService);
        return ResultMsg.ok();
    }

    @ApiOperation(value = "课程分类列表（树形）")
    @GetMapping("getAllSubject")
    public ResultMsg getAllSubject() {
        //list集合泛型是一级分类
        List<OneSubject> list = subjectService.getAllOneTwoSubject();
        return ResultMsg.ok().data("list",list);
    }
}

