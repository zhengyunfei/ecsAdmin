package com.zero2ipo.plugins.weixin.bo;

/**
 * Created with IntelliJ IDEA.
 * User: Kone
 * Date: 13-5-21
 * Time: 上午12:16
 * To change this template use File | Settings | File Templates.
 */

/**
 * 粉丝数量json对象
 *
 * @author wang
 */
public class FansCount {
    private int id;
    private String name;
    private int cnt;

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

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

}
