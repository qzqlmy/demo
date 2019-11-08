package com.example.demo.controller;

import com.example.demo.Model.Book;

public class redisTest {

    public static void main(String[] args) {

        Book user = new Book();
        user.setId(11);
        user.setTitle("test");
        user.setpublish("hello redis");
        user.setAuthor("aaaaa");
        RedisTemplateService redisTemplateService=new RedisTemplateService();
        redisTemplateService.set("key1",user);

        Book us = redisTemplateService.get("key1",Book.class);
        System.out.println(user);
    }
}
