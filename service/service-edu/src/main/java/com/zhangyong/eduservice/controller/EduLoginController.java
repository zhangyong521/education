package com.zhangyong.eduservice.controller;

import com.zhangyong.utils.ResultMsg;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 张勇
 * @Blog: https://blog.csdn.net/zy13765287861
 * @Version: 1.0
 * @Date: 2020-08-01 8:05
 * @PS: 登录的接口
 */
@Api(description = "测试登录")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

    /**
     * 登录login
     *
     * @return
     */
    @PostMapping("login")
    public ResultMsg login() {
        return ResultMsg.ok().data("token", "admin");
    }

    @GetMapping("info")
    public ResultMsg error() {
        return ResultMsg.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

}
