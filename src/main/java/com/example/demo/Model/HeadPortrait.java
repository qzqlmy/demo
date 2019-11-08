package com.example.demo.Model;

import java.io.Serializable;

public class HeadPortrait implements Serializable {
    private Integer id;
   private String   username;
   private String   HeadPortrait;
    public HeadPortrait(Integer id, String username, String headPortrait) {
        this.id = id;
        this.username = username;
        HeadPortrait = headPortrait;
    }
    public HeadPortrait(String username, String headPortrait) {
        this.username = username;
        HeadPortrait = headPortrait;
    }

    public String getUsername() {
        return username;
    }

    public String getHeadPortrait() {
        return HeadPortrait;
    }

    @Override
    public String toString() {
        return "GetHeadPortrait{" +
                "username='" + username + '\'' +
                ", HeadPortrait='" + HeadPortrait + '\'' +
                '}';
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setHeadPortrait(String headPortrait) {
        HeadPortrait = headPortrait;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
