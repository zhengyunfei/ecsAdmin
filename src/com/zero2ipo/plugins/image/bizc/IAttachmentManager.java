package com.zero2ipo.plugins.image.bizc;

import com.zero2ipo.plugins.image.bo.Attachment;

public interface IAttachmentManager {
	public void saveAttachmentInfo(Attachment attachment) ;
	

	/**
	 * 根据code删除附件
	 * @param code
	 * @throws ServiceException
	 */
	public void deleteAttachmentByCode(String code) ;
	/**
	 * 根据code获取文件url
	 * @param code
	 * @return
	 * @throws ServiceException
	 */
	public String getURLByCode(String code) ;
	/**
	 * 根据附件编码得到上传文件的原名
	 * @param code
	 * @return
	 * @author lei.zhou
	 * @throws ServiceException
	 */
	public String getFileNameByCode(String code) ;
	
	/**
	 * 通过path查找Attachment
	 * @param pictureUrlPortal
	 * @return
	 */
	public Attachment getAttachmentByPath(String path);
}
