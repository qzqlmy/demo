package com.example.demo.Model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/18.
 */
@ApiModel(value = "图书模型类")
public class Book implements Serializable {
    @ApiModelProperty(value = "图书ID")
    private Integer id;

    @ApiModelProperty(value = "图书名称")
    private String title;

    @ApiModelProperty(value = "图书作者")
    private String author;

    @ApiModelProperty(value = "图书出版商")
    private String publish;

    public Book(Integer id, String title, String author, String publish) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publish = publish;
    }

    public Book() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getpublish() {
        return publish;
    }

    public void setpublish(String publish) {
        this.publish = publish;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", pubilish='" + publish + '\'' +
                '}';
    }
}
