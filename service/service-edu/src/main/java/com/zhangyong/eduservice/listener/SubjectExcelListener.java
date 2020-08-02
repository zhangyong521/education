package com.zhangyong.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangyong.eduservice.entity.EduSubject;
import com.zhangyong.eduservice.entity.excel.SubjectData;
import com.zhangyong.eduservice.service.EduSubjectService;
import com.zhangyong.exception.EducationException;

/**
 * @Author: 张勇
 * @Blog: https://blog.csdn.net/zy13765287861
 * @Version: 1.0
 * @Date: 2020-08-02 23:30
 * @PS: EasyExcel监听
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    /**
     * 因为SubjectExcelListener不能交给Spring进行管理，需要自己new，不能注入对象
     * 不能实现数据库操作
     * 处理办法：使用参数构造器
     *
     * @param subjectData
     * @param analysisContext
     */

    public EduSubjectService subjectService;

    /**
     * 有参构造
     *
     * @param subjectService
     */
    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    /**
     * 无参构造
     */
    public SubjectExcelListener() {
    }

    /**
     * 读取Excel内容，一行一行读取
     *
     * @param subjectData
     * @param analysisContext
     */
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData == null) {
            throw new EducationException(20001, "文件数据为空");
        }
        //一行一行读取，每个读物两个值，第一个为一级分类，第二个为二级分类
        // 添加一级分类
        EduSubject existOneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        //判断一级分类是否存在
        if (existOneSubject == null) {
            existOneSubject = new EduSubject();
            existOneSubject.setParentId("0");
            existOneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(existOneSubject);
        }
        //添加二级分类
        //获取一级分类的Id值
        String pid = existOneSubject.getId();
        EduSubject existTwoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        //判断一级分类是否存在
        if (existTwoSubject == null) {
            existTwoSubject = new EduSubject();
            existTwoSubject.setParentId(pid);
            existTwoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(existTwoSubject);
        }

    }

    /**
     * 判断一级分类不能重复添加
     *
     * @return
     */
    private EduSubject existOneSubject(EduSubjectService subjectService, String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", "0");
        EduSubject oneSubject = subjectService.getOne(wrapper);
        return oneSubject;
    }


    /**
     * 判断二级分类不能重复添加
     *
     * @return
     */
    private EduSubject existTwoSubject(EduSubjectService subjectService, String name, String pid) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pid);
        EduSubject twoSubject = subjectService.getOne(wrapper);
        return twoSubject;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
