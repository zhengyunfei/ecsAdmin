package com.zero2ipo.plugins.pic.bizc.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.module.entity.picture.PictureEntity;
import com.zero2ipo.module.entity.picture.PictureTypeEntity;
import com.zero2ipo.plugins.pic.bizc.IPicType;
import com.zero2ipo.plugins.pic.bo.PictureBo;
import com.zero2ipo.plugins.pic.bo.PictureTypeBo;
@Service("picType")
public class PicTypeImpl implements IPicType{
	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao;
	 /**
	    * 新增
	    *@title
	    *@date 2014-10-13
	    *@author ZhengYunfei
	    * @return
	    */
		public int add(PictureTypeBo bo){
			int count=0;
			try {
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				String id = String.valueOf(baseDao.getSerialNo("SEQ_SYS_SUPPORT"));
				bo.setId(id);
				PictureTypeEntity entity=PictureTypeBo.boToEntity(bo);
				baseDao.addObject("addPicType", entity);
				count=1;
			} catch (Exception e) {
				count=0;
				e.printStackTrace();
				BaseLog.e(this.getClass(), "add 新增图片轮播有误", e);
				throw new BaseException("新增图片轮播有误!",e);
			}
			return count;
		}
		/**
		 * 修改
		 *@title
		 *@date 2014-10-13
		 *@author ZhengYunfei
		 * @return
		 */
		public int update(PictureTypeBo bo){
			int count=0;
			try {
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				PictureTypeEntity entity=PictureTypeBo.boToEntity(bo);
				count= baseDao.updObject("updPictureType", entity);
			} catch (Exception e) {
				BaseLog.e(this.getClass(), "update 修改图片轮播有误", e);
				throw new BaseException("修改图片轮播有误!",e);
			}
			return count;
		}
		/**
		 * 删除
		 *@title
		 *@date 2014-10-13
		 *@author ZhengYunfei
		 * @return
		 */
		public int delete(String ids){
			int count=0;
			SqlMapClient client =  baseDao.getSqlMapClient();
			try {
				client.startTransaction();//开启事物
				client.startBatch();//开启批处理模式
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("id",ids.split(","));
				count= baseDao.delObject("deletePicType",map);
				count= baseDao.delObject("deletePictureByPid",map);
				client.executeBatch();//执行批处理模式
			} catch (Exception e) {
				try {
					client.getCurrentConnection().rollback();
				} catch (SQLException e1) {
					BaseLog.e(this.getClass(), "delete 删除图片轮播有误", e);
					throw new BaseException("删除图片轮播有误!",e);
				}
				
			}finally{
	            try {
	                client.commitTransaction();//提交事物
	                client.endTransaction();//结束事物
	            } catch (Exception e) {
	                BaseLog.e(this.getClass(), "addRoleMenu:提交&结束事物异常!", e);
	            }
	        }
			return count;
			
		}
		/**
		 * 查询
		 *@title
		 *@date 2014-10-13
		 *@author ZhengYunfei
		 * @return
		 */
		public List<PictureTypeBo> find(Map<String,Object> query){
			List<PictureTypeBo> list=null;
			try {
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				list= baseDao.findForList("findPictureTypeList", query);
			} catch (Exception e) {
				e.printStackTrace();
				BaseLog.e(this.getClass(), "delete 删除图片轮播有误", e);
				throw new BaseException("删除图片轮播有误!",e);
			}
			return list;
		}
		/**
		 * 根据id查询
		 *@title
		 *@date 2014-10-14
		 *@author ZhengYunfei
		 * @param id
		 * @return
		 */
		public PictureTypeBo find(String id){
			PictureTypeBo bo=null;
			PictureTypeEntity entity=null;
			try {
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("id", id);
				entity= (PictureTypeEntity) baseDao.findForObject("findPictureTypeById", map);
				bo=PictureTypeBo.entityToBo(entity);
			} catch (Exception e) {
				e.printStackTrace();
				BaseLog.e(this.getClass(), " 根据id查询有误", e);
				throw new BaseException(" 根据id查询有误!",e);
			}
			return bo;
		}

}
