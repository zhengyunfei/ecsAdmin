package com.zero2ipo.eeh.classroom.bo;

import com.zero2ipo.eeh.teachbuild.bo.TeachingBuildingBo;

/**
 * 教学楼
 * Created by Administrator on 2016/2/24.
 */
public class ClassRoomBo implements  java.io.Serializable {
   public int id;//主键
   public  String tbId;//所在教学楼id
   public TeachingBuildingBo teachingBuildingBo;//所在教学楼
   public  String  classId;//所在班级id
   public String name;//教室名称
   public String homeNum;//房间号
   public String floors;//楼层
   public String ip;

    public TeachingBuildingBo getTeachingBuildingBo() {
        return teachingBuildingBo;
    }

    public void setTeachingBuildingBo(TeachingBuildingBo teachingBuildingBo) {
        this.teachingBuildingBo = teachingBuildingBo;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTbId() {
        return tbId;
    }

    public void setTbId(String tbId) {
        this.tbId = tbId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFloors() {
        return floors;
    }

    public void setFloors(String floors) {
        this.floors = floors;
    }

    public String getHomeNum() {
        return homeNum;
    }

    public void setHomeNum(String homeNum) {
        this.homeNum = homeNum;
    }
}
