package com.zero2ipo.cfj.user.bo;

import com.zero2ipo.module.entity.user.OrganizationUserInfoEntity;

/**
 * 机构用户扩展信息
 * @author zhengyunfei
 *
 */
public class OrganizationUserInfoBo {
	
	private String orgUserInfoId;

	private String userId;

	private String orgName;
	private String orgAssets;
	private String orgDutypersonName;
	private String likeProducts;
    private String orgDutypepsonMobile;
	private String orgDutypersonSex;

	private String orgDutypepsonEmaile;

	private String orgDutypersonPosition;

	private String orgDutypersonCardurl;

	private String orgLicenseUrl;
  
	private String invitationCode;
	private String codeArea;
	private String codeAreaName;
	private String remark;
	
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCodeAreaName() {
		return codeAreaName==null?"":codeAreaName;
	}

	public void setCodeAreaName(String codeAreaName) {
		this.codeAreaName = codeAreaName;
	}

	public String getCodeArea() {
		return codeArea==null?"":codeArea;
	}

	public void setCodeArea(String codeArea) {
		this.codeArea = codeArea;
	}

	public String getOrgUserInfoId() {
		return orgUserInfoId;
	}

	public void setOrgUserInfoId(String orgUserInfoId) {
		this.orgUserInfoId = orgUserInfoId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrgName() {
		return orgName==null?"":orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgDutypersonSex() {
		return orgDutypersonSex==null?"":orgDutypersonSex;
	}

	public void setOrgDutypersonSex(String orgDutypersonSex) {
		this.orgDutypersonSex= orgDutypersonSex;
	}

	public String getOrgDutypepsonEmaile() {
		return orgDutypepsonEmaile==null?"":orgDutypepsonEmaile;
	}

	public void setOrgDutypepsonEmaile(String orgDutypepsonEmaile) {
		this.orgDutypepsonEmaile = orgDutypepsonEmaile;
	}

	public String getOrgDutypersonPosition() {
		return orgDutypersonPosition==null?"":orgDutypersonPosition;
	}

	public void setOrgDutypersonPosition(String orgDutypersonPosition) {
		this.orgDutypersonPosition = orgDutypersonPosition;
	}

	public String getOrgDutypersonCardurl() {
		return orgDutypersonCardurl==null?"":orgDutypersonCardurl;
	}

	public void setOrgDutypersonCardurl(String orgDutypersonCardurl) {
		this.orgDutypersonCardurl = orgDutypersonCardurl;
	}

	public String getOrgLicenseUrl() {
		return orgLicenseUrl==null?"":orgLicenseUrl;
	}

	public void setOrgLicenseUrl(String orgLicenseUrl) {
		this.orgLicenseUrl = orgLicenseUrl;
	}

	public String getInvitationCode() {
		return invitationCode==null?"":invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	
	
	
	public String getOrgAssets() {
		return orgAssets==null?"":orgAssets;
	}

	public void setOrgAssets(String orgAssets) {
		this.orgAssets = orgAssets;
	}

	public String getOrgDutypersonName() {
		return orgDutypersonName==null?"":orgDutypersonName;
	}

	public void setOrgDutypersonName(String orgDutypersonName) {
		this.orgDutypersonName = orgDutypersonName;
	}

	public String getLikeProducts() {
		return likeProducts==null?"":likeProducts;
	}

	public void setLikeProducts(String likeProducts) {
		this.likeProducts = likeProducts;
	}

	public String getOrgDutypepsonMobile() {
		return orgDutypepsonMobile==null?"":orgDutypepsonMobile;
	}

	public void setOrgDutypepsonMobile(String orgDutypepsonMobile) {
		this.orgDutypepsonMobile = orgDutypepsonMobile;
	}

	public static OrganizationUserInfoBo getEntityToBo(OrganizationUserInfoEntity entity){
		OrganizationUserInfoBo bo=new OrganizationUserInfoBo();
		bo.setInvitationCode(entity.getInvitationCode());
		bo.setOrgDutypepsonEmaile(entity.getOrgDutypepsonEmaile());
		bo.setOrgDutypersonCardurl(entity.getOrgDutypersonCardurl());
		bo.setOrgDutypersonPosition(entity.getOrgDutypersonPosition());
		bo.setOrgDutypersonSex(entity.getOrgDutypersonSex());
		bo.setOrgLicenseUrl(entity.getOrgLicenseUrl());
		bo.setOrgName(entity.getOrgName());
		bo.setOrgUserInfoId(entity.getOrgUserInfoId());
		bo.setUserId(entity.getUserId());
		bo.setCodeArea(entity.getCodeArea());
		bo.setInvitationCode(entity.getInvitationCode());
	    bo.setOrgAssets(entity.getOrgAssets());
	    bo.setOrgDutypersonName(entity.getOrgDutypersonName());
	    bo.setLikeProducts(entity.getLikeProducts());
	    bo.setOrgDutypepsonMobile(entity.getOrgDutypepsonMobile());
	    bo.setRemark(entity.getRemark());
		return bo;
	}
	public static  OrganizationUserInfoEntity  getBoToEntity(OrganizationUserInfoBo entity){
		OrganizationUserInfoEntity bo=new OrganizationUserInfoEntity();
		bo.setInvitationCode(entity.getInvitationCode());
		bo.setOrgDutypepsonEmaile(entity.getOrgDutypepsonEmaile());
		bo.setOrgDutypersonCardurl(entity.getOrgDutypersonCardurl());
		bo.setOrgDutypersonPosition(entity.getOrgDutypersonPosition());
		bo.setOrgDutypersonSex(entity.getOrgDutypersonSex());
		bo.setOrgLicenseUrl(entity.getOrgLicenseUrl());
		bo.setOrgName(entity.getOrgName());
		bo.setOrgUserInfoId(entity.getOrgUserInfoId());
		bo.setUserId(entity.getUserId());
		bo.setCodeArea(entity.getCodeArea());
		bo.setInvitationCode(entity.getInvitationCode());
		bo.setOrgAssets(entity.getOrgAssets());
	    bo.setOrgDutypersonName(entity.getOrgDutypersonName());
	    bo.setLikeProducts(entity.getLikeProducts());
	    bo.setOrgDutypepsonMobile(entity.getOrgDutypepsonMobile());
	    bo.setRemark(entity.getRemark());
		return bo;
	}
}
