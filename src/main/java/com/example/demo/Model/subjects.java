package com.example.demo.Model;

import java.io.Serializable;

public class subjects implements Serializable {
    private Integer id;
    private  String studentid;
    private  String subject;
    private  String name;
    private  String classname;

    public subjects() {
    }

    public subjects( String studentid, String subject, String name, String classname) {
        this.studentid = studentid;
        this.subject = subject;
        this.name = name;
        this.classname = classname;
    }

    public subjects(Integer id, String studentid, String subject, String name, String classname) {
        this.id = id;
        this.studentid = studentid;
        this.subject = subject;
        this.name = name;
        this.classname = classname;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubjectid() {
        return studentid;
    }

    public void setSubjectid(String subjectid) {
        this.studentid = studentid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
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
        return "subjects{" +
                "id=" + id +
                ", subjectid='" + studentid + '\'' +
                ", subject='" + subject + '\'' +
                ", name='" + name + '\'' +
                ", classname='" + classname + '\'' +
                '}';
    }
}
