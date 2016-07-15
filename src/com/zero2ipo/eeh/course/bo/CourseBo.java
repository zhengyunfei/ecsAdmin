package com.zero2ipo.eeh.course.bo;

/**
 * Created by Administrator on 2016/2/24.
 */
public class CourseBo implements  java.io.Serializable {
    public int id;//主键
    public String courseName;//课程名称
    public String kemu;//科目名称
    public String peopleMax;//上线人数
    public String teacher;//授课老师
    public String classRoom;//授课教室
    public String schoolTime;//上课时间 从时段里面过来
    public String week;//星期 从时段里面获取
    public String status;//状态
    public String seatSetStatus;//座位导入状态0：未导入 1 已导入

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKemu() {
        return kemu;
    }

    public void setKemu(String kemu) {
        this.kemu = kemu;
    }

    public String getPeopleMax() {
        return peopleMax;
    }

    public void setPeopleMax(String peopleMax) {
        this.peopleMax = peopleMax;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getSchoolTime() {
        return schoolTime;
    }

    public void setSchoolTime(String schoolTime) {
        this.schoolTime = schoolTime;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public String getStatus() {
        return status==null?"":status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSeatSetStatus() {
        return seatSetStatus==null?"":seatSetStatus;
    }

    public void setSeatSetStatus(String seatSetStatus) {
        this.seatSetStatus = seatSetStatus;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
}
