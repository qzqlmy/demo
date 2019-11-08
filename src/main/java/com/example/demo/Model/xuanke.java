package com.example.demo.Model;

import java.io.Serializable;

public class xuanke implements Serializable {
    private Integer id;
    private  String studentid;
    private  String name;
    private  String subject;

    public xuanke(Integer id, String studentid, String name, String subject) {
        this.id = id;
        this.studentid = studentid;
        this.name = name;
        this.subject = subject;
    }
    public xuanke(String studentid, String name, String subject) {
        this.id = id;
        this.studentid = studentid;
        this.name = name;
        this.subject = subject;
    }

    public xuanke() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudentid() {
        return studentid;
    }

    public void setStudentid(String studentid) {
        this.studentid = studentid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "xuanke{" +
                "id=" + id +
                ", studentid='" + studentid + '\'' +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
