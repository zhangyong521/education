package com.zhangyong.eduservice.entity.subjectvo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 张勇
 * @Blog: https://blog.csdn.net/zy13765287861
 * @Version: 1.0
 * @Date: 2020-08-03 23:08
 * @PS: 一级分类的实体类
 */
@Data
public class OneSubject {
    private String id;
    private String title;

    /**
     * 一个一级分类有多个二级分类
     */
    private List<TwoSubject> children = new ArrayList<>();
}