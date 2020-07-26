package com.zhangyong.utils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 张勇
 * @Blog: https://blog.csdn.net/zy13765287861
 * @Version: 1.0
 * @Date: 2020-07-25 20:39
 * @PS: 统一返回类型
 */
@Data
public class ResultMsg {
    @ApiModelProperty(value = "是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String, Object> data = new HashMap<String, Object>();

    private ResultMsg() {
    }

    public static ResultMsg ok() {
        ResultMsg r = new ResultMsg();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    public static ResultMsg error() {
        ResultMsg r = new ResultMsg();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public ResultMsg success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public ResultMsg message(String message) {
        this.setMessage(message);
        return this;
    }

    public ResultMsg code(Integer code) {
        this.setCode(code);
        return this;
    }

    public ResultMsg data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public ResultMsg data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }
}