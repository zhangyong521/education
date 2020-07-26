package com.zhangyong.eduservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangyong.eduservice.entity.EduTeacher;
import com.zhangyong.eduservice.service.EduTeacherService;
import com.zhangyong.eduservice.entity.vo.TeacherQuery;
import com.zhangyong.utils.ResultMsg;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author zhangyong
 * @ApiParam 用于接口测试，没有实际的业务作用
 * @since 2020-07-25
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;

    @ApiOperation(value = "所有讲师列表")
    @GetMapping("findAll")
    public ResultMsg findAllTeacher() {

        List<EduTeacher> list = teacherService.list(null);
        return ResultMsg.ok().data("list", list);
    }

    @ApiOperation(value = "根据ID逻辑删除讲师")
    @DeleteMapping("{id}")
    public ResultMsg removeById(
            @ApiParam(name = "id", value = "讲师ID", required = true)
            @PathVariable String id) {
        teacherService.removeById(id);
        return ResultMsg.ok();
    }


    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public ResultMsg pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit
    ) {
        Page<EduTeacher> pageParam = new Page<>(page, limit);
        teacherService.page(pageParam, null);
        //获取数据
        List<EduTeacher> records = pageParam.getRecords();
        //总的页数
        long total = pageParam.getTotal();

        return ResultMsg.ok().data("total", total).data("records", records);
    }

    @ApiOperation(value = "分页讲师列表")
    @PostMapping("{page}/{limit}")
    public ResultMsg pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "teacherQuery", value = "查询对象", required = false)
            @RequestBody TeacherQuery teacherQuery) {

        Page<EduTeacher> pageParam = new Page<>(page, limit);

        teacherService.pageQuery(pageParam, teacherQuery);
        List<EduTeacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();

        return ResultMsg.ok().data("total", total).data("rows", records);
    }

    @ApiOperation(value = "新增讲师")
    @PostMapping("save")
    public ResultMsg save(
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher eduTeacher) {
        teacherService.save(eduTeacher);
        return ResultMsg.ok();
    }

    @ApiOperation(value = "根据Id查询讲师")
    @GetMapping("{id}")
    public ResultMsg getById(
            @ApiParam(name = "id", value = "讲师id", required = true)
            @PathVariable String id) {
        EduTeacher teacher = teacherService.getById(id);
        return ResultMsg.ok().data("item", teacher);
    }

    @ApiOperation(value = "根据Id修改讲师")
    @PutMapping("{id}")
    public ResultMsg UpdateById(
            @ApiParam(name = "id", value = "讲师id", required = true)
            @PathVariable String id,
            @ApiParam(name = "teacher", value = "讲师对象", required = true)
            @RequestBody EduTeacher eduTeacher) {
        EduTeacher byId = teacherService.getById(id);
        String idId = byId.getId();
        if (byId != null) {
            eduTeacher.setId(idId);
            teacherService.updateById(eduTeacher);
            return ResultMsg.ok();
        }
        return ResultMsg.error();
    }

}

