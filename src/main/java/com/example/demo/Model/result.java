package com.example.demo.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value = "返回数据模型类")
public class result {
    @ApiModelProperty(value = "状态码")
    private Integer code;

    @ApiModelProperty(value = "请求描述")
    private String msg;

    @ApiModelProperty(value = "返回数据")
    private List<Book> aa;

    public result(Integer code, String msg, List<Book> aa) {
        this.code = code;
        this.msg = msg;
        this.aa = aa;
    }

    public result() {
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

    public List<Book> getAa() {
        return aa;
    }

    public void setAa(List<Book> aa) {
        this.aa = aa;
    }
}
