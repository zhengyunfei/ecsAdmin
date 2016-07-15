package com.zero2ipo.eeh.devconf.bo;

/**
 * 设备配置
 * Created by Administrator on 2016/2/24.
 */
public class DevConfBo implements  java.io.Serializable {
    public long id;
    public String dev_no;
    public String dev_name;
    public String ip;
    public String mac;
    public String dev_status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDev_no() {
        return dev_no;
    }

    public void setDev_no(String dev_no) {
        this.dev_no = dev_no;
    }

    public String getDev_name() {
        return dev_name;
    }

    public void setDev_name(String dev_name) {
        this.dev_name = dev_name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getDev_status() {
        return dev_status;
    }

    public void setDev_status(String dev_status) {
        this.dev_status = dev_status;
    }
}
