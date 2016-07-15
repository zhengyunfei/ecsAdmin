package com.zero2ipo.plugins.image.bo;

import java.io.File;
import java.util.Date;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zero2ipo.plugins.image.AttachmentEntity;
import com.zero2ipo.plugins.image.AttachmentUtil;

/**
 * AttachmentInfo entity.
 * 附件信息表
 * @author mowei
 */


public class Attachment {
	private long attachmentId;
	//编码
	private String code;
	//类型
	private String type;
	//文件名
	private String fileName;
	//存储路径(相对路径)
	private String path;
	//描述
	private String description;
	//上传时间
	private Date uploadTime;
	//上传人
	private String uploadUser;
	
	//调用端临时存储路径
	private String clientTempPath;
	//附件
	private CommonsMultipartFile cfile;
	private File file;
	//文件类型(MIME Type)
	private String contentType;

	// Constructors
	/** default constructor */
	public Attachment() {
	}

	public long getAttachmentId() {
		return attachmentId;
	}



	public void setAttachmentId(long attachmentId) {
		this.attachmentId = attachmentId;
	}



	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getUploadUser() {
		return uploadUser;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}

	
	public String getClientTempPath() {
		return clientTempPath;
	}

	public void setClientTempPath(String clientTempPath) {
		this.clientTempPath = clientTempPath;
	}
	public CommonsMultipartFile getCfile() {
		return cfile;
	}

	public void setCfile(CommonsMultipartFile cfile) {
		this.cfile = cfile;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		if(file!=null){
			this.file = file;
			this.clientTempPath = this.file.getPath();
		}
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public String getContentType() {
		return contentType;
	}

	
	public void setFileFileName(String fileFileName) {
		this.fileName = fileFileName;
	}

	
	public void setFileContentType(String fileContentType) {
		this.contentType = fileContentType;
	}
	
	public static AttachmentEntity boToEntity(Attachment bo){
		AttachmentEntity entity = new AttachmentEntity();
		entity.setAttachmentId(bo.getAttachmentId());
		entity.setClientTempPath(bo.getClientTempPath());
		entity.setCode(bo.getCode());
		entity.setDescription(bo.getDescription());
		entity.setFileName(bo.getFileName());
		entity.setPath(bo.getPath());
		entity.setType(bo.getType());
		entity.setUploadTime(bo.getUploadTime());
		entity.setUploadUser(bo.getUploadUser());
		return entity;
	}
	public static Attachment EntityTobo(AttachmentEntity entity){
		Attachment bo = new Attachment();
		bo.setAttachmentId(entity.getAttachmentId());
		bo.setClientTempPath(entity.getClientTempPath());
		bo.setCode(entity.getCode());
		bo.setDescription(entity.getDescription());
		bo.setFileName(entity.getFileName());
		bo.setPath(entity.getPath());
		bo.setType(entity.getType());
		bo.setUploadTime(entity.getUploadTime());
		bo.setUploadUser(entity.getUploadUser());
		return bo;
	}
	public String getURL() {
		return this.path!=null&&!this.path.equals("") ? AttachmentUtil.getURL(this) : "";
	}
}