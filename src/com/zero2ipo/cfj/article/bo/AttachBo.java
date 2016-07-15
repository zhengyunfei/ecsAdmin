package com.zero2ipo.cfj.article.bo;

   /**
    * eehAttach 实体类
    * Fri Feb 26 14:29:24 GMT+08:00 2016 wangli
    */ 

public class AttachBo{
	private String id;
	private String articleid;
	private String title;
	private String filepath;
	private String uploadauthor;
	private String uploadtime;
	private String viewname;
	public void setId(String id){
	this.id=id;
	}
	public String getId(){
		return id;
	}
	public void setArticleid(String articleid){
	this.articleid=articleid;
	}
	public String getArticleid(){
		return articleid;
	}
	public void setTitle(String title){
	this.title=title;
	}
	public String getTitle(){
		return title;
	}
	public void setFilepath(String filepath){
	this.filepath=filepath;
	}
	public String getFilepath(){
		return filepath;
	}
	public void setUploadauthor(String uploadauthor){
	this.uploadauthor=uploadauthor;
	}
	public String getUploadauthor(){
		return uploadauthor;
	}
	public void setUploadtime(String uploadtime){
	this.uploadtime=uploadtime;
	}
	public String getUploadtime(){
		return uploadtime;
	}
	public void setViewname(String viewname){
	this.viewname=viewname;
	}
	public String getViewname(){
		return viewname;
	}

}

