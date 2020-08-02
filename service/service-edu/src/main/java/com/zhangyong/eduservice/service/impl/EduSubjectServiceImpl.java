package com.zhangyong.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.zhangyong.eduservice.entity.EduSubject;
import com.zhangyong.eduservice.entity.excel.SubjectData;
import com.zhangyong.eduservice.listener.SubjectExcelListener;
import com.zhangyong.eduservice.mapper.EduSubjectMapper;
import com.zhangyong.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author zhangyong
 * @since 2020-08-02
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    /**
     * 实现
     *
     * @param file
     */
    @Override
    public void saveSubject(MultipartFile file, EduSubjectService subjectService) {
        try {
            //文件输入流
            InputStream in = file.getInputStream();
            //调用方法进行读取
            EasyExcel.read(in, SubjectData.class, new SubjectExcelListener(subjectService)).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
