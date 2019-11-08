package com.example.demo.Model;

import java.io.Serializable;

public class student implements Serializable {
    private Integer id;
    private  String studentid;
    private  String name;
    private  String classname;

    public student(Integer id, String studentid, String name, String classname) {
        this.id = id;
        this.studentid = studentid;
        this.name = name;
        this.classname = classname;
    }
    public student( String studentid, String name, String classname) {
        this.studentid = studentid;
        this.name = name;
        this.classname = classname;
    }

    public student() {
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

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @Override
    public String toString() {
        return "student{" +
                "id=" + id +
                ", studentid='" + studentid + '\'' +
                ", name='" + name + '\'' +
                ", classname='" + classname + '\'' +
                '}';
    }
}
