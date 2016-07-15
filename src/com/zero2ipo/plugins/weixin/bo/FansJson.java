package com.zero2ipo.plugins.weixin.bo;

/**
 * 粉丝信息JSON实体
 * 
 * @author wang
 * @version 1.1
 */

public class FansJson {
	private String id;// 是粉丝的fakeId
	private String nick_name;
	private String remark_name;
	private int group_id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getRemark_name() {
		return remark_name;
	}

	public void setRemark_name(String remark_name) {
		this.remark_name = remark_name;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

}
