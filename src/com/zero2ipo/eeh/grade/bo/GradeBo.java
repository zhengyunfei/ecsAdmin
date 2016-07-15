package com.zero2ipo.eeh.grade.bo;

/**
 * 班级
 * Created by Administrator on 2016/2/24.
 */
public class GradeBo implements  java.io.Serializable {
    private int id;//主键
    private String name;//班级名称
    private String gradeName;//所在年级
    private String teacherName;//班主持名称
    private String gradenum;//班级人数
    private int peopleCount;//实际人数
    private String classCadre;//班团干部
    private String seatStatus;//是否已导入座位表0未导入1已导入

    public int getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(int peopleCount) {
        this.peopleCount = peopleCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGradeName() {
        return gradeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getGradenum() {
        return gradenum;
    }

    public void setGradenum(String gradenum) {
        this.gradenum = gradenum;
    }

    public String getClassCadre() {
        return classCadre;
    }

    public void setClassCadre(String classCadre) {
        this.classCadre = classCadre;
    }

    public String getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(String seatStatus) {
        this.seatStatus = seatStatus;
    }
}
