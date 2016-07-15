package com.zero2ipo.plugins.pic.bizc.impl;

import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.module.entity.picture.PictureEntity;
import com.zero2ipo.plugins.pic.bizc.IPicManage;
import com.zero2ipo.plugins.pic.bo.PictureBo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service("picManage")
public class PicManageImpl implements IPicManage{
	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao;
	 /**
	    * 新增
	    *@title
	    *@date 2014-10-13
	    *@author ZhengYunfei
	    * @return
	    */
		public int add(PictureBo bo){
			int count=0;
			try {
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				String id = String.valueOf(baseDao.getSerialNo("SEQ_SYS_SUPPORT"));
				bo.setId(id);
				PictureEntity entity=PictureBo.boToEntity(bo);
				baseDao.addObject("addPicture", entity);
				count=1;
			} catch (Exception e) {
				count=0;
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
		public int update(PictureBo bo){
			int count=0;
			try {
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				PictureEntity entity=PictureBo.boToEntity(bo);
				count= baseDao.updObject("updPicture", entity);
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
			try {
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				Map<String,Object> map=new HashMap<String, Object>();
				map.put("id",ids.split(","));
				count= baseDao.delObject("deletePicture", map);
			} catch (Exception e) {
				BaseLog.e(this.getClass(), "delete 删除图片轮播有误", e);
				throw new BaseException("删除图片轮播有误!",e);
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
		public List<PictureBo> find(String name){
			List<PictureBo> list=null;
			try {
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				list= baseDao.findForList("findPictureList", name);
			} catch (Exception e) {
				BaseLog.e(this.getClass(), "delete 删除图片轮播有误", e);
				throw new BaseException("删除图片轮播有误!",e);
			}
			return list;
		}
		/**
		 * 根据pid查询轮播图
		 *@title
		 *@date 2014-10-14
		 *@author ZhengYunfei
		 * @param pid
		 * @return
		 */
		public List<PictureBo> findPictureById(String pid,String id){
			List<PictureBo> list=new ArrayList<PictureBo>();
			List<PictureEntity> entityList=null;
			try {
				baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
				Map<String,Object> map=new HashMap<String, Object>();
				if(!StringUtil.isNullOrEmpty(pid)&&!"undefined".equals(pid)){
					map.put("pid",pid);
				}
				if(!StringUtil.isNullOrEmpty(id)){
					map.put("id",id);
				}
				entityList=  baseDao.findForList("findPictureById", map);
				for (PictureEntity pictureEntity : entityList) {
					PictureBo bo=PictureBo.entityToBo(pictureEntity);
					list.add(bo);
				}
			} catch (Exception e) {
				e.printStackTrace();
				BaseLog.e(this.getClass(), "delete 删除图片轮播有误", e);
				throw new BaseException("删除图片轮播有误!",e);
			}
			return list;
		}

}
