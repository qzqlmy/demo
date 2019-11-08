package com.example.demo;

import com.example.demo.entity.Article;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 商品搜索管理Service实现类
 * Created by macro on 2018/6/19.
 */
@Service
public class EsProductServiceImpl implements EsProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsProductServiceImpl.class);

    @Autowired
    private ArticleRepository productRepository;

    @Override
    public int importAll() {
        List<Article> esProductList = new ArrayList<Article>();
        Article article = new Article();
        productRepository.deleteAll();
        article.setId((long) 1);
        article.setTitle("《蝶恋花》");
        article.setContent("槛菊愁烟兰泣露，罗幕轻寒，燕子双飞去。明月不谙离恨苦，斜光到晓穿朱户。昨夜西风凋碧树，独上高楼，望尽天涯路。欲寄彩笺兼尺素，山长水阔知何处？");
        article.setCreateTime(new Date());
        article.setUpdateTime(new Date());
        article.setViewCount(678);
        esProductList.add(article);



        Article article2 = new Article();
        article2.setId((long) 2);
        article2.setTitle("《蝶恋花》");
        article2.setContent("伫倚危楼风细细，望极春愁，黯黯生天际。草色烟光残照里，无言谁会凭阑意。拟把疏狂图一醉，对酒当歌，强乐还无味。衣带渐宽终不悔，为伊消得人憔悴。");
        article2.setCreateTime(new Date());
        article2.setUpdateTime(new Date());
        article.setViewCount(367);
        esProductList.add(article2);


        Article article3 = new Article();
        article3.setId((long) 3);
        article3.setTitle("《青玉案·元夕》");
        article3.setContent("东风夜放花千树，更吹落，星如雨。宝马雕车香满路。凤箫声动，玉壶光转，一夜鱼龙舞。蛾儿雪柳黄金缕，笑语盈盈暗香去。众里寻他千百度，蓦然回首，那人却在，灯火阑珊处。");
        article3.setCreateTime(new Date());
        article3.setUpdateTime(new Date());
        article3.setViewCount(786);
        esProductList.add(article3);
        productRepository.saveAll(esProductList);

        return 1;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Article create(Long id) {
        return null;
    }

    @Override
    public Article create(Article article) {
        Article result = null;
        result = productRepository.save(article);

        return result;
    }

    @Override
    public void delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<Article> esProductList = new ArrayList<>();
            for (Long id : ids) {
                Article esProduct = new Article();
                esProduct.setId(id);
                esProductList.add(esProduct);
            }
            productRepository.deleteAll(esProductList);
        }
    }




    @Override
    public Page<Article> search(String title, String content, Pageable pageable) {

       return productRepository.findByTitleOrContent(title, content,  pageable);


    }
    @Override
    public Page<Article> search() {

        return productRepository.findAll();


    }
}
