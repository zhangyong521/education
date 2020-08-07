package com.zhangyong.eduservice.service;

import com.zhangyong.eduservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangyong.eduservice.entity.vo.CourseInfoVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author zhangyong
 * @since 2020-08-04
 */
public interface EduCourseService extends IService<EduCourse> {

    /**
     * 添加课程信息
     * @param courseInfoVo
     * @return 返回课程ID
     */
    String saveCourseInfo(CourseInfoVo courseInfoVo);
}
