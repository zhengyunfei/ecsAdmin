package com.zero2ipo.eeh.AttendanceSetting.bo;

/**
 * 教师
 * Created by Administrator on 2016/2/24.
 */
public class AttendanceSettingBo implements  java.io.Serializable {
    public long id;
    public String before_xx_minute;
    public String after_xx_minute;
    public String after_xx_minute_late;
    public String after_xx_minute_duty;
    public String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBefore_xx_minute() {
        return before_xx_minute;
    }

    public void setBefore_xx_minute(String before_xx_minute) {
        this.before_xx_minute = before_xx_minute;
    }

    public String getAfter_xx_minute() {
        return after_xx_minute;
    }

    public void setAfter_xx_minute(String after_xx_minute) {
        this.after_xx_minute = after_xx_minute;
    }

    public String getAfter_xx_minute_late() {
        return after_xx_minute_late;
    }

    public void setAfter_xx_minute_late(String after_xx_minute_late) {
        this.after_xx_minute_late = after_xx_minute_late;
    }

    public String getAfter_xx_minute_duty() {
        return after_xx_minute_duty;
    }

    public void setAfter_xx_minute_duty(String after_xx_minute_duty) {
        this.after_xx_minute_duty = after_xx_minute_duty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
