package com.example.demo.Model;

import java.io.Serializable;

public class subject implements Serializable {
    private Integer id;
    private  String subjectid;
    private  String subject;
    private  String classplace;
    private  String classtime;
    private  String classhour;

    public subject(Integer id, String subjectid, String subject, String classplace, String classtime, String classhour) {
        this.id = id;
        this.subjectid = subjectid;
        this.subject = subject;
        this.classplace = classplace;
        this.classtime = classtime;
        this.classhour = classhour;
    }
    public subject( String subjectid, String subject, String classplace, String classtime, String classhour) {
        this.subjectid = subjectid;
        this.subject = subject;
        this.classplace = classplace;
        this.classtime = classtime;
        this.classhour = classhour;
    }

    public subject() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubjectid() {
        return subjectid;
    }

    public void setSubjectid(String subjectid) {
        this.subjectid = subjectid;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getClassplace() {
        return classplace;
    }

    public void setClassplace(String classplace) {
        this.classplace = classplace;
    }

    public String getClasstime() {
        return classtime;
    }

    public void setClasstime(String classtime) {
        this.classtime = classtime;
    }

    public String getClasshour() {
        return classhour;
    }

    public void setClasshour(String classhour) {
        this.classhour = classhour;
    }

    @Override
    public String toString() {
        return "subject{" +
                "id=" + id +
                ", subjectid='" + subjectid + '\'' +
                ", subject='" + subject + '\'' +
                ", classplace='" + classplace + '\'' +
                ", classtime='" + classtime + '\'' +
                ", classhour='" + classhour + '\'' +
                '}';
    }
}
