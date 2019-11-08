package com.example.demo.service;

import com.example.demo.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<Map>  getById(int id){
        return userMapper.getById(id);
    }
   // @Cacheable(value = "UserCache",key = "'user.findAllUsers'")
    public List<Map>  selectList(HashMap<String,Object> map){
        return userMapper.queryfoodlis(map);
    }
}
