package com.zhangyong.eduservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhangyong.eduservice.entity.EduTeacher;
import com.zhangyong.eduservice.mapper.EduTeacherMapper;
import com.zhangyong.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangyong.eduservice.entity.vo.TeacherQuery;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author zhangyong
 * @since 2020-07-25
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {


    @Override
    public void pageQuery(Page<EduTeacher> pageParam, TeacherQuery eduTeacherVO) {
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();

        //按照添加的时间进行排序
        queryWrapper.orderByDesc("gmt_create");

        if (eduTeacherVO == null) {
            baseMapper.selectPage(pageParam, queryWrapper);
            return;
        }
        String name = eduTeacherVO.getName();
        Integer level = eduTeacherVO.getLevel();
        String begin = eduTeacherVO.getBegin();
        String end = eduTeacherVO.getEnd();

        if (!StringUtils.isEmpty(name)) {
            queryWrapper.like("name", name);
        }

        if (!StringUtils.isEmpty(level)) {
            queryWrapper.eq("level", level);
        }

        if (!StringUtils.isEmpty(begin)) {
            queryWrapper.ge("gmt_create", begin);
        }

        if (!StringUtils.isEmpty(end)) {
            queryWrapper.le("gmt_create", end);
        }

        baseMapper.selectPage(pageParam, queryWrapper);

    }
}
