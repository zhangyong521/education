package com.zhangyong.eduservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangyong.eduservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangyong.eduservice.entity.vo.TeacherQuery;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author zhangyong
 * @since 2020-07-25
 */
public interface EduTeacherService extends IService<EduTeacher> {

    /**
     * 按照条件查询
     * @param pageParam
     * @param eduTeacherVO
     */
    void pageQuery(Page<EduTeacher> pageParam, TeacherQuery eduTeacherVO);

}
