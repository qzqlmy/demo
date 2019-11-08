package com.example.demo.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

@Document(indexName="chuyun",type="article",indexStoreType="fs",shards=5,replicas=1,refreshInterval="-1")
public class Article {
    //文章ID，这里必须为 id
    @Id
    private Long id;
    //标题
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String title;
    //内容
    @Field(analyzer = "ik_max_word",type = FieldType.Text)
    private String content;
    //浏览量
    private Integer viewCount;
    //发布时间
    private Date createTime;
    //更新时间
    private Date updateTime;
    public Article() {
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public Integer getViewCount() {
        return viewCount;
    }
    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", viewCount=" + viewCount +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
