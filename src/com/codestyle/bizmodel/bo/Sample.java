package com.codestyle.bizmodel.bo;

import java.io.Serializable;

/**
 * @title :实体类代码示例
 * @description :该实体会用于xxx 
 * @author: zhengYunFei
 * @date: 2012-07-16
 */
public class Sample implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
	private Integer age;	// 年龄
	private String name;	// 名字
	private Long num;		// 人数
	private Double money;	// 钱数
	
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getNum() {
		return num;
	}
	public void setNum(Long num) {
		this.num = num;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	
}
