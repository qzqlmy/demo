package com.example.demo.Mapper;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface UserMapper {
    List<Map>  getById(int id);

    List<Map> queryfoodlis(HashMap<String,Object> map);
}

