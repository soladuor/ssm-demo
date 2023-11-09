package com.sola.pojo;

import java.util.List;

public class SchoolClass {
    private Integer id; // 班级号
    private String className; // 班级名称
    private List<Student> studentList;

    public SchoolClass() {
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "id=" + id +
                ", className='" + className + '\'' +
                ", studentList=" + studentList +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
