package com.zhangyong.exception;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: 张勇
 * @Blog: https://blog.csdn.net/zy13765287861
 * @Version: 1.0
 * @Date: 2020-07-26 23:40
 * @PS: 创建自定义异常
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationException extends RuntimeException {

    @ApiModelProperty(value = "状态码")
    private Integer code;

    private String msg;

}
