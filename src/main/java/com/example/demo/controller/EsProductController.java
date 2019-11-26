package com.example.demo.controller;

import com.example.demo.EsProductService;
import com.example.demo.Model.CommonPage;
import com.example.demo.Model.CommonResult;
import com.example.demo.entity.Article;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.demo.HttpClientUtil.doPost;
import static com.example.demo.HttpClientUtil.doPostJson;

/**
 * 搜索商品管理Controller
 * Created by macro on 2018/6/19.
 */
@Controller
@Api(tags = "EsProductController", description = "搜索商品管理")
@RequestMapping("/esProduct")
public class EsProductController {
    @Autowired
    private EsProductService esProductService;

    @ApiOperation(value = "导入所有数据库中商品到ES")
    @RequestMapping(value = "/importAll", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult importAllList() {
        int count = esProductService.importAll();
        return CommonResult.success(count);
    }

    @ApiOperation(value = "根据id删除商品")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult delete(@PathVariable Long id) {
        esProductService.delete(id);
        return CommonResult.success("删除成功!");
    }

    @ApiOperation(value = "根据id批量删除商品")
    @RequestMapping(value = "/delete/batch", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult delete(@RequestParam("ids") List<Long> ids) {
        esProductService.delete(ids);
        return CommonResult.success("删除成功!");
    }

    @ApiOperation(value = "根据id创建商品")
    @RequestMapping(value = "/create/demo", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult create(@RequestBody Article article) {
        Article esProduct = esProductService.create(article);
        if (esProduct != null) {
            return CommonResult.success(esProduct);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "简单搜索")
    @RequestMapping(value = "/search/simple", method = RequestMethod.GET)
    @ResponseBody
   public CommonResult list(@RequestParam(value = "title", required = false) String title,
                              @RequestParam(value = "content", required = false) String content,
                              @RequestParam(value = "pageIndex", defaultValue = "0") int pageIndex,
                              @RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
        Pageable pageable = new PageRequest(pageIndex, pageSize);
        Page<Article> page = esProductService.search(title, content, pageable);
        return CommonResult.success(CommonPage.restPage(page.getContent()));
    }
    @ApiOperation(value = "全量搜索")
    @RequestMapping(value = "/search/all", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult list() {
   //jjjjjjjjjjjjjjjjjjjjjj
        Page<Article> page = (Page<Article>) esProductService.search();
        return CommonResult.success(CommonPage.restPage(page.getContent()));
    }

    public static void main(String[] args) {
        String url="http://135.192.71.87:8888/lip/Blsd/Blsd.qh?ts=NzY2NTg4NTYyMDM1MTphYzJlNDIxNTIzMTI3YjJhZDJiMzI4NjI1OTIzODJlNTJkMTI4YjJiMzJlZDI1YzI=";
       Map<String, String> para=new HashMap<String, String>();
//        para.put("ns_order_no","11111111");
//        para.put("orderNo","222222");
//        para.put("backOrderState","333333333");
//        para.put("backOrderTime","44444444");
//        para.put("backOrderDesc","555555555");
//        para.put("backOrderPStaffCode","333333333");
//        String aa= doPost(url, para);
       // System.out.println(para.size());
        String json=" {\n" +
                "\t\t\t\t\t\t\t\"ns_order_no\": \"111111\"," +
                "\t\t\t\t\t\t\t\"orderNo\": \"22222222\"," +
                "\t\t\t\t\t\t\t\"backOrderState\": \"3333333\"," +
                "\t\t\t\t\t\t\t\"backOrderTime\": \"444444444\"," +
                "\t\t\t\t\t\t    \"backOrderDesc\":  \"555555555\"," +
                "\t\t\t\t\t\t\t\"backOrderPStaffCode\":  \"666666666\"" +
                "\t\t\t\t\t\t}";
        String aa= doPostJson(url, json);
        System.out.println(aa);
    }
}