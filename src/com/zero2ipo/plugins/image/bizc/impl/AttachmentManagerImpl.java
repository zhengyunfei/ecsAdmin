package com.zero2ipo.plugins.image.bizc.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.protobuf.ServiceException;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.SnoGerUtil;
import com.zero2ipo.plugins.image.AttachmentEntity;
import com.zero2ipo.plugins.image.AttachmentUtil;
import com.zero2ipo.plugins.image.bizc.IAttachmentManager;
import com.zero2ipo.plugins.image.bo.Attachment;


@Service("attachmentManager")
public class AttachmentManagerImpl implements IAttachmentManager {
	
	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao;

	/**
	 * 上传&保存附件
	 * @param attachment
	 * @throws ServiceException
	 */
	public void saveAttachmentInfo(Attachment attachment) {
		if (attachment == null || attachment.getCfile() == null)
			return;
		SqlMapClient client =  baseDao.getSqlMapClient();
		try {
			//将附件传输至资源服务器
			if (AttachmentUtil.transferToResourceServer(attachment)) {
				Long code = SnoGerUtil.getRandomNumber(19);
				attachment.setCode(code.toString());
				attachment.setUploadTime(new Date());
				AttachmentEntity entity=Attachment.boToEntity(attachment);
				//保存附件信息
				client.insert("addAttachment", entity);
				BaseLog.i(this.getClass(), "保存附件"+attachment.getFileName()+"成功");
			} else {
				//从磁盘上删除文件
				AttachmentUtil.deleteUploadedAttachment(attachment);
				throw new BaseException("保存附件信息出错!");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
//	public void saveXmlAttachmentInfo(Attachment attachment) {
//		if (attachment == null || attachment.getFile() == null)
//			return;
//		SqlMapClient client =  baseDao.getSqlMapClient();
//		try {
//			//将附件传输至资源服务器
//			if (AttachmentUtil.xmlTransferToResourceServer(attachment)) {
//				Long code = SnoGerUtil.getRandomNumber(19);
//				attachment.setCode(code.toString());
//				attachment.setUploadTime(new Date());
//				//保存附件信息
//				client.insert("addInatitutions", attachment);
//				BaseLog.i(this.getClass(), "保存附件"+attachment.getFileName()+"成功");
//			} else {
//				//从磁盘上删除文件
//				AttachmentUtil.deleteUploadedAttachment(attachment);
//				throw new BaseException("保存附件信息出错!");
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}

	/**
	 * 根据code删除附件
	 * @param code
	 * @throws ServiceException
	 */
	public void deleteAttachmentByCode(String code) {
		if (code == null || code.trim().equals(""))
			return;
		SqlMapClient client =  baseDao.getSqlMapClient();
		try {
			//获取附件信息
			Attachment attachment = this.getAttachmentInfoByCode(code);
			if (attachment != null) {
				Map map = new HashMap();
				map.put("inatitutionsId",attachment);
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				
				//删除附件信息记录
				client.delete("delAttachment",map);
				//从磁盘上删除文件
				AttachmentUtil.deleteUploadedAttachment(attachment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 根据code获取文件url
	 * @param code
	 * @return
	 * @throws ServiceException
	 */
	public String getURLByCode(String code) {
		if (code == null || code.trim().equals(""))
			return "";
		Attachment attachment = this.getAttachmentInfoByCode(code);
		return attachment != null ? AttachmentUtil.getURL(attachment) : "";
	}

	/**
	 * 根据附件编码得到上传文件的原名
	 * @param code
	 * @return
	 * @author lei.zhou
	 * @throws ServiceException
	 */
	public String getFileNameByCode(String code) {
		Attachment attachment = this.getAttachmentInfoByCode(code);
		return attachment != null ? attachment.getFileName() : "";
	}

	/** 
	 * 通过附件编码获得附件信息:Bean or Model
	 * @param code
	 * @return
	 * @throws ServiceException 
	 */
	private Attachment getAttachmentInfoByCode(String code){
		if (code == null || code.trim().equals(""))
			return null;
		
		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
		AttachmentEntity entity = (AttachmentEntity)baseDao.findForObject("findAttachmentByCode", code);
		Attachment bo=Attachment.EntityTobo(entity);
		return bo;
	}

	/**
	 * 通过path查找Attachment
	 * @param pictureUrlPortal
	 * @return
	 */
	public Attachment getAttachmentByPath(String path) {
		if (path == null || path.trim().equals(""))
			return null;
		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
		AttachmentEntity entity = (AttachmentEntity)baseDao.findForObject("findAttachmentByPath", path);
		Attachment bo=Attachment.EntityTobo(entity);
		return bo;
	}

}
