package com.example.demo;

import com.example.demo.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 商品搜索管理Service
 * Created by macro on 2018/6/19.
 */
public interface EsProductService {
    /**
     * 从数据库中导入所有商品到ES
     */
    int importAll();

    /**
     * 根据id删除商品
     */
    void delete(Long id);

    /**
     * 根据id创建商品
     */
    Article create(Long id);

    /**
     * 根据id创建商品
     */
    Article create(Article article);
    /**
     * 批量删除商品
     */
    void delete(List<Long> ids);

    /**
     * 根据关键字搜索名称或者副标题
     */
    Iterable<Article> search();
    /**
     * 根据关键字搜索名称或者副标题
     */
    Page<Article> search(String title, String keyword, Pageable pageable);

}
