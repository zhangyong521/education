package com.zhangyong.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhangyong.eduservice.entity.EduSubject;
import com.zhangyong.eduservice.entity.excel.SubjectData;
import com.zhangyong.eduservice.entity.subjectvo.OneSubject;
import com.zhangyong.eduservice.entity.subjectvo.TwoSubject;
import com.zhangyong.eduservice.listener.SubjectExcelListener;
import com.zhangyong.eduservice.mapper.EduSubjectMapper;
import com.zhangyong.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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

    /**
     * 课程分类列表（树形）
     *
     * @return
     */
    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        //1、查询出一级分类parent_id = 0
        QueryWrapper<EduSubject> wrapperOne = new QueryWrapper<>();
        wrapperOne.eq("parent_id", "0");
        List<EduSubject> oneSubjectList = baseMapper.selectList(wrapperOne);

        //2、查询出二级分类parentId != 0
        QueryWrapper<EduSubject> wrapperTwo = new QueryWrapper<>();
        wrapperTwo.ne("parent_id", "0");
        List<EduSubject> twoSubjectList = baseMapper.selectList(wrapperTwo);

        //创建List集合，用于存储最终的封装类
        List<OneSubject> finalSubjectList = new ArrayList<>();

        //3、封装一级分类
        //查询出来所有的一级分类list集合遍历，得到每个一级分类对象，获取每个一级分类对象值，
        //封装到要求的list集合里面 List<OneSubject> finalSubjectList
        for (int i = 0; i < oneSubjectList.size(); i++) {
            //得到oneSubjectList每个eduSubject对象
            EduSubject eduSubject = oneSubjectList.get(i);
            //把eduSubject里面的值获取出来，放到OneSubject对象里
            OneSubject oneSubject = new OneSubject();

            //使用spring的BeanUtils将eduSubject拷贝到oneSubject
            BeanUtils.copyProperties(eduSubject, oneSubject);

            //多个OneSubject放到finalSubjectList里面
            finalSubjectList.add(oneSubject);

            //在一级分类循环遍历查询所有的二级分类
            //创建list集合封装每个一级分类的二级分类
            List<TwoSubject> twoFinalSubjectList = new ArrayList<>();

            //遍历二级分类list集合
            for (int m = 0; m < twoSubjectList.size(); m++) {
                //获取每个二级分类
                EduSubject subjectTwo = twoSubjectList.get(m);

                //判断二级分类parent_id和一级分类id是否一样
                if (subjectTwo.getParentId().equals(eduSubject.getId())) {
                    //把subjectTwo值复制到TwoSubject里面，并放到twoFinalSubjectList里面
                    TwoSubject twoSubject = new TwoSubject();
                    BeanUtils.copyProperties(subjectTwo, twoSubject);
                    twoFinalSubjectList.add(twoSubject);
                }
            }
            //把一级下面所有二级分类放到一级分类里面
            oneSubject.setChildren(twoFinalSubjectList);
        }

        return finalSubjectList;
    }
}
