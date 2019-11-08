package com.example.demo.Model;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/8/17.
 */
public class user implements Serializable {
    private Integer id;
    private  String email;
    private  String password;

    public user( String email, String password) {
        this.email = email;
        this.password = password;
    }

    public user() {
    }

    public user(Integer id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "user{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
