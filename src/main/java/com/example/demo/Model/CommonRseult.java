package com.example.demo.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "通用返回数据模型类")
public class CommonRseult <T>{
    @ApiModelProperty(value = "请求状态")
    private Integer code;

    @ApiModelProperty(value = "请求描述")
    private String msg;

    @ApiModelProperty(value = "返回数据")
    private T aa;

    public CommonRseult() {
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getAa() {
        return aa;
    }

    public void setAa(T aa) {
        this.aa = aa;
    }
}
