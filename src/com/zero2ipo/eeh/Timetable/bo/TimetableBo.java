package com.zero2ipo.eeh.Timetable.bo;

/**
 * Created by Administrator on 2016/3/1.
 * 课程表
 */
public class TimetableBo implements java.io.Serializable{
    public int id;
    public String week;//星期
    public String gradeName;//班级
    public String firstClass;//第一节课
    public String secondClass;//第二节课
    public String threeClass;//第三节课
    public String fourClass;//第四节课
    public String fiveClass;//第五节课
    public String sixClass;//第六节课
    public String sevenClass;//第七节课
    public String eightClass;//第八节课
    public String nineClass;//第八节课

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getgradeName() {
        return gradeName;
    }

    public void setgradeName(String gradeName) {
        this.gradeName = gradeName;
    }


    public String getFirstClass() {
        return firstClass;
    }

    public void setFirstClass(String firstClass) {
        this.firstClass = firstClass;
    }

    public String getSecondClass() {
        return secondClass;
    }

    public void setSecondClass(String secondClass) {
        this.secondClass = secondClass;
    }

    public String getThreeClass() {
        return threeClass;
    }

    public void setThreeClass(String threeClass) {
        this.threeClass = threeClass;
    }

    public String getFourClass() {
        return fourClass;
    }

    public void setFourClass(String fourClass) {
        this.fourClass = fourClass;
    }

    public String getFiveClass() {
        return fiveClass;
    }

    public void setFiveClass(String fiveClass) {
        this.fiveClass = fiveClass;
    }

    public String getSixClass() {
        return sixClass;
    }

    public void setSixClass(String sixClass) {
        this.sixClass = sixClass;
    }

    public String getSevenClass() {
        return sevenClass;
    }

    public void setSevenClass(String sevenClass) {
        this.sevenClass = sevenClass;
    }

    public String getEightClass() {
        return eightClass;
    }

    public void setEightClass(String eightClass) {
        this.eightClass = eightClass;
    }

    public String getNineClass() {
        return nineClass;
    }

    public void setNineClass(String nineClass) {
        this.nineClass = nineClass;
    }
}
