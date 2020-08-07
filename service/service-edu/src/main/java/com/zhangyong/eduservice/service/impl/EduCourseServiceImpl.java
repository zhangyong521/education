package com.zhangyong.eduservice.service.impl;

import com.zhangyong.eduservice.entity.EduCourse;
import com.zhangyong.eduservice.entity.EduCourseDescription;
import com.zhangyong.eduservice.entity.vo.CourseInfoVo;
import com.zhangyong.eduservice.mapper.EduCourseMapper;
import com.zhangyong.eduservice.service.EduCourseDescriptionService;
import com.zhangyong.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangyong.exception.EducationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author zhangyong
 * @since 2020-08-04
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService courseDescriptionService;

    @Override
    public String saveCourseInfo(CourseInfoVo courseInfoVo) {
        //向课程里添加课程基本信息
        //CourseInfoVo转化为EduCourse
        EduCourse eduCourse = new EduCourse();
        BeanUtils.copyProperties(courseInfoVo, eduCourse);
        int insert = baseMapper.insert(eduCourse);

        if (insert == 0) {
            //添加失败
            throw new EducationException(20001, "添加课程失败");
        }
        //得到课程ID
        String courseId = eduCourse.getId();

        //2、向课程描述表添加数据edu_course_description
        EduCourseDescription courseDescription = new EduCourseDescription();
        courseDescription.setDescription(courseInfoVo.getDescription());
        //因为课程和描述是1对1的关系，所以把课程ID设置到描述ID进行关联
        courseDescription.setId(courseId);
        courseDescriptionService.save(courseDescription);

        return courseId;
    }
}
