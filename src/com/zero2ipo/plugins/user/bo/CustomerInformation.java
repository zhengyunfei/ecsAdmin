package com.zero2ipo.plugins.user.bo;
import java.util.Date;

   /**
    * customerInformation 实体类
    * Mon Feb 02 13:23:29 GMT+08:00 2015 zhengyunfei
    */ 


public class CustomerInformation{
	private int customerId;
	private String tempId;
	private String institutionsName;
	private String cfjId;
	private String memberName;
	private String salesId;
	private String originalSales;
	private String recommendedProject;
	private String funds;
	private String financialAssets;
	private String averageIncome;
	private String cityId;
	private String netWorth;
	private String registeredCapital;
	private String industryAttributes;
	private String investmentPreference;
	private String onceMoney;
	private String customerSource;
	private String introduce;
	private String note;
	private String createUser;
	private Date createTime;
	private String updateUser;
	private Date updateTime;
	public void setCustomerId(int customerId){
	this.customerId=customerId;
	}
	public int getCustomerId(){
		return customerId;
	}
	public void setTempId(String tempId){
	this.tempId=tempId;
	}
	public String getTempId(){
		return tempId;
	}
	public void setInstitutionsName(String institutionsName){
	this.institutionsName=institutionsName;
	}
	public String getInstitutionsName(){
		return institutionsName;
	}
	public void setCfjId(String cfjId){
	this.cfjId=cfjId;
	}
	public String getCfjId(){
		return cfjId;
	}
	public void setMemberName(String memberName){
	this.memberName=memberName;
	}
	public String getMemberName(){
		return memberName;
	}
	public void setSalesId(String salesId){
	this.salesId=salesId;
	}
	public String getSalesId(){
		return salesId;
	}
	public void setOriginalSales(String originalSales){
	this.originalSales=originalSales;
	}
	public String getOriginalSales(){
		return originalSales;
	}
	public void setRecommendedProject(String recommendedProject){
	this.recommendedProject=recommendedProject;
	}
	public String getRecommendedProject(){
		return recommendedProject;
	}
	public void setFunds(String funds){
	this.funds=funds;
	}
	public String getFunds(){
		return funds;
	}
	public void setFinancialAssets(String financialAssets){
	this.financialAssets=financialAssets;
	}
	public String getFinancialAssets(){
		return financialAssets;
	}
	public void setAverageIncome(String averageIncome){
	this.averageIncome=averageIncome;
	}
	public String getAverageIncome(){
		return averageIncome;
	}
	public void setCityId(String cityId){
	this.cityId=cityId;
	}
	public String getCityId(){
		return cityId;
	}
	public void setNetWorth(String netWorth){
	this.netWorth=netWorth;
	}
	public String getNetWorth(){
		return netWorth;
	}
	public void setRegisteredCapital(String registeredCapital){
	this.registeredCapital=registeredCapital;
	}
	public String getRegisteredCapital(){
		return registeredCapital;
	}
	public void setIndustryAttributes(String industryAttributes){
	this.industryAttributes=industryAttributes;
	}
	public String getIndustryAttributes(){
		return industryAttributes;
	}
	public void setInvestmentPreference(String investmentPreference){
	this.investmentPreference=investmentPreference;
	}
	public String getInvestmentPreference(){
		return investmentPreference;
	}
	public void setOnceMoney(String onceMoney){
	this.onceMoney=onceMoney;
	}
	public String getOnceMoney(){
		return onceMoney;
	}
	public void setCustomerSource(String customerSource){
	this.customerSource=customerSource;
	}
	public String getCustomerSource(){
		return customerSource;
	}
	public void setIntroduce(String introduce){
	this.introduce=introduce;
	}
	public String getIntroduce(){
		return introduce;
	}
	public void setNote(String note){
	this.note=note;
	}
	public String getNote(){
		return note;
	}
	public void setCreateUser(String createUser){
	this.createUser=createUser;
	}
	public String getCreateUser(){
		return createUser;
	}
	public void setCreateTime(Date createTime){
	this.createTime=createTime;
	}
	public Date getCreateTime(){
		return createTime;
	}
	public void setUpdateUser(String updateUser){
	this.updateUser=updateUser;
	}
	public String getUpdateUser(){
		return updateUser;
	}
	public void setUpdateTime(Date updateTime){
	this.updateTime=updateTime;
	}
	public Date getUpdateTime(){
		return updateTime;
	}
	public static CustomerInformation boToEntity(CustomerInformation bo){
	CustomerInformation entity=new CustomerInformation();
	entity.setCustomerId(bo.getCustomerId());
	entity.setTempId(bo.getTempId());
	entity.setInstitutionsName(bo.getInstitutionsName());
	entity.setCfjId(bo.getCfjId());
	entity.setMemberName(bo.getMemberName());
	entity.setSalesId(bo.getSalesId());
	entity.setOriginalSales(bo.getOriginalSales());
	entity.setRecommendedProject(bo.getRecommendedProject());
	entity.setFunds(bo.getFunds());
	entity.setFinancialAssets(bo.getFinancialAssets());
	entity.setAverageIncome(bo.getAverageIncome());
	entity.setCityId(bo.getCityId());
	entity.setNetWorth(bo.getNetWorth());
	entity.setRegisteredCapital(bo.getRegisteredCapital());
	entity.setIndustryAttributes(bo.getIndustryAttributes());
	entity.setInvestmentPreference(bo.getInvestmentPreference());
	entity.setOnceMoney(bo.getOnceMoney());
	entity.setCustomerSource(bo.getCustomerSource());
	entity.setIntroduce(bo.getIntroduce());
	entity.setNote(bo.getNote());
	entity.setCreateUser(bo.getCreateUser());
	entity.setCreateTime(bo.getCreateTime());
	entity.setUpdateUser(bo.getUpdateUser());
	entity.setUpdateTime(bo.getUpdateTime());
	return entity;
	}
	public static CustomerInformation entityToBo(CustomerInformation bo){
	CustomerInformation entity=new CustomerInformation();
	entity.setCustomerId(bo.getCustomerId());
	entity.setTempId(bo.getTempId());
	entity.setInstitutionsName(bo.getInstitutionsName());
	entity.setCfjId(bo.getCfjId());
	entity.setMemberName(bo.getMemberName());
	entity.setSalesId(bo.getSalesId());
	entity.setOriginalSales(bo.getOriginalSales());
	entity.setRecommendedProject(bo.getRecommendedProject());
	entity.setFunds(bo.getFunds());
	entity.setFinancialAssets(bo.getFinancialAssets());
	entity.setAverageIncome(bo.getAverageIncome());
	entity.setCityId(bo.getCityId());
	entity.setNetWorth(bo.getNetWorth());
	entity.setRegisteredCapital(bo.getRegisteredCapital());
	entity.setIndustryAttributes(bo.getIndustryAttributes());
	entity.setInvestmentPreference(bo.getInvestmentPreference());
	entity.setOnceMoney(bo.getOnceMoney());
	entity.setCustomerSource(bo.getCustomerSource());
	entity.setIntroduce(bo.getIntroduce());
	entity.setNote(bo.getNote());
	entity.setCreateUser(bo.getCreateUser());
	entity.setCreateTime(bo.getCreateTime());
	entity.setUpdateUser(bo.getUpdateUser());
	entity.setUpdateTime(bo.getUpdateTime());
	return entity;
	}
}

