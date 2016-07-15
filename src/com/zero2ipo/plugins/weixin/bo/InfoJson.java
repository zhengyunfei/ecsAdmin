package com.zero2ipo.plugins.weixin.bo;

/**
 * 粉丝信息JSON实体
 * 
 * @author wang
 * @version 1.1
 */

public class InfoJson {
	private String FakeId;
	private String NickName;
	private String ReMarkName;
	private String Username;
	private String Signature;
	private String Country;
	private String Province;
	private String City;
	private String Sex;
	private int GroupID;

	public String getFakeId() {
		return FakeId;
	}

	public void setFakeId(String fakeId) {
		FakeId = fakeId;
	}

	public String getNickName() {
		return NickName;
	}

	public void setNickName(String nickName) {
		NickName = nickName;
	}

	public String getReMarkName() {
		return ReMarkName;
	}

	public void setReMarkName(String reMarkName) {
		ReMarkName = reMarkName;
	}

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getSignature() {
		return Signature;
	}

	public void setSignature(String signature) {
		Signature = signature;
	}

	public String getCountry() {
		return Country;
	}

	public void setCountry(String country) {
		Country = country;
	}

	public String getProvince() {
		return Province;
	}

	public void setProvince(String province) {
		Province = province;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public int getGroupID() {
		return GroupID;
	}

	public void setGroupID(int groupID) {
		GroupID = groupID;
	}

}
