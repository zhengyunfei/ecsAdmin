package com.zero2ipo.eeh.pewstudent.bo;

/**
 * 教学楼
 * Created by Administrator on 2016/2/24.
 */
public class PewStudentBo implements  java.io.Serializable {
   public int id;//主键
   public String className;//班级
   public String name;//姓名
   public String xhnum;//学号
   public String sex;//性别
   public String type;//类型
   public String electiveCourse;//选修课程
   public String classRoom;//上课教室
   public String  seatNumer;//座位号
   public String  schoolTime;//上课时间
   public String status;//已审、待审
   public String courseType;//0 学生选课 1 老师选课
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getXhnum() {
        return xhnum;
    }

    public void setXhnum(String xhnum) {
        this.xhnum = xhnum;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getElectiveCourse() {
        return electiveCourse;
    }

    public void setElectiveCourse(String electiveCourse) {
        this.electiveCourse = electiveCourse;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getSeatNumer() {
        return seatNumer;
    }

    public void setSeatNumer(String seatNumer) {
        this.seatNumer = seatNumer;
    }

    public String getSchoolTime() {
        return schoolTime;
    }

    public void setSchoolTime(String schoolTime) {
        this.schoolTime = schoolTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }
}
