package com.example.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;


public class test {
    public static void main(String[] args) {
        removeJceLimit();
        Map jsonMap = new HashMap();
        String jsonDat ="ZG6hJWqiUjdlWjueRujh8WQKc343Bq6ZtP6Onj9SQCLmCWFjbY2u2EnIt1Ufsihk+BhvGSoJdVIEf8b/d8upm+qgqM1h9YS7cu+F6gX9UrNIgRZjJJDQBKTAjRn23Oxr0htwefxiVDvBsp/qLD5ddmXjPN1t/ZE/CXoGdFJRneUJ9YDJUbHnce9Wm+heo9ejToYhp2qgFJj3ClrlqollJkfRs+69S5E3N096I1LAR/+tto9lx59BtjU/IcM+wTZm";
        String jsonData=getStrValue(jsonDat);
        String jsonDatt ="{\"staff_id\":\"1\",\"treeLevel\":\"1\",\"tree_id\":\"888\",\"sqlName\":\"report.hdBranchChannelZnkdTime\",\"ts\":\"NzY2NTg4NTYyMDM1MTphYzJlNDIxNTIzMTI3YjJhZDJiMzI4NjI1OTIzODJlNTJkMTI4YjJiMzJlZDI1YzI=\"}";
        String decodeStr = CipherCode.decryptAES(jsonData);
        try {
            jsonMap = new ObjectMapper().readValue(decodeStr, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String staff_id= (String) jsonMap.get("staff_id");
        System.out.println("解密后："+jsonMap);
        System.out.println(staff_id);
        String encrypt = CipherCode.encryptAES(jsonDatt);

        System.out.println("加密后："+encrypt);
    }
    public static String getStrValue(Object o){
        if(o!=null){
            return o.toString().trim();
        }
        return "";
    }
    private static void removeJceLimit()
    {
        //去除JCE加密限制，只限于Java1.8
        try {
            Field field = Class.forName("javax.crypto.JceSecurity").getDeclaredField("isRestricted");
            field.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

            field.set(null, false);



        } catch (ClassNotFoundException | NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException ex) {
            ex.printStackTrace(System.err);
        }
    }
}
