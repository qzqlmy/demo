package com.example.demo;

import com.example.demo.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * 商品ES操作类
 * Created by macro on 2018/6/19.
 */
public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {

    Page<Article> findByTitleOrContent(String title, String content,  Pageable page);
    Page<Article> findAll();

}
