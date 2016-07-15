package com.zero2ipo.cfj.upload.bo;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class UploadFile {
	private String name;
	private CommonsMultipartFile uploadFile;
	private String filePath;

	public CommonsMultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(CommonsMultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}