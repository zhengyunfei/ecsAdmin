package com.zero2ipo.eeh.teachbuild.bo;

/**
 * 教学楼
 * Created by Administrator on 2016/2/24.
 */
public class TeachingBuildingBo implements  java.io.Serializable {
    private int id;//主键
    private String name;//教学楼名称
    private int floors;//楼层数

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

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }
}
