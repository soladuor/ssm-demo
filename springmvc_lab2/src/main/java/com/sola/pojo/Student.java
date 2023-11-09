package com.sola.pojo;

public class Student {
    private String id;                      // id
    // private Integer classId;
    private SchoolClass schoolClass;    // 班级
    private String studentName;             // 姓名
    private String logoUrl;                 // 头像
    private Integer grade;                  // 年级
    private Integer aGrade;                 // A成绩
    private Integer bGrade;                 // B成绩
    private Integer cGrade;                 // C成绩
    private Integer zGrade;                 // Z成绩

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", schoolClass=" + schoolClass +
                ", studentName='" + studentName + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", grade=" + grade +
                ", aGrade=" + aGrade +
                ", bGrade=" + bGrade +
                ", cGrade=" + cGrade +
                ", zGrade=" + zGrade +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SchoolClass getSchoolClass() {
        return schoolClass;
    }

    public void setSchoolClass(SchoolClass schoolClass) {
        this.schoolClass = schoolClass;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getaGrade() {
        return aGrade;
    }

    public void setaGrade(Integer aGrade) {
        this.aGrade = aGrade;
    }

    public Integer getbGrade() {
        return bGrade;
    }

    public void setbGrade(Integer bGrade) {
        this.bGrade = bGrade;
    }

    public Integer getcGrade() {
        return cGrade;
    }

    public void setcGrade(Integer cGrade) {
        this.cGrade = cGrade;
    }

    public Integer getzGrade() {
        return zGrade;
    }

    public void setzGrade(Integer zGrade) {
        this.zGrade = zGrade;
    }
}
