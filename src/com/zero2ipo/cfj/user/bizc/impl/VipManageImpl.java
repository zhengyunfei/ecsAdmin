package com.zero2ipo.cfj.user.bizc.impl;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.zero2ipo.cfj.user.bizc.IVipManage;
import com.zero2ipo.cfj.user.bo.OrganizationUserInfoBo;
import com.zero2ipo.cfj.user.bo.UserBo;
import com.zero2ipo.cfj.user.bo.UserInfoBo;
import com.zero2ipo.cfj.user.bo.Users;
import com.zero2ipo.common.GlobalConstant;
import com.zero2ipo.common.SeqConstant;
import com.zero2ipo.framework.FwConstant;
import com.zero2ipo.framework.db.bizc.IBaseDao;
import com.zero2ipo.framework.exception.BaseException;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.module.entity.ir.IrEntity;
import com.zero2ipo.module.entity.user.OrganizationUserInfoEntity;
import com.zero2ipo.module.entity.user.UserEntity;
import com.zero2ipo.module.entity.user.UserInfoEntity;
import com.zero2ipo.plugins.code.biz.ICodeManage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @title :
 * @description :会员管理实现类
 * @author: zhengYunFei
 * @date: 2013-10-15 16:53
 */
@Service("vipManage")
public class VipManageImpl implements IVipManage{
	@Autowired @Qualifier("baseDao")
	private IBaseDao baseDao;
	//自动注入codeManage标准代码管理业务操作接口
	@Autowired @Qualifier("codeManage")
	private ICodeManage codeManage;
	/**
	 * @description: 查询会员列表
	 * @param  skip：页码
	 * @param  max ：最大值
	 * @return：vipInfoList会员列表
	 */
	public List<Users> queryVipInfoList(Map<String, Object> map, int skip, int max){
		List<Users> resultList = new ArrayList<Users>();
		try {
			//设置数据库类型: 网站全局库(01)
    		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
			resultList = (List<Users>)baseDao.findForList("findUsersList", map,skip,max);
    		/*for(int i=0;i<vipInfoList.size();i++){
    			UserEntity entity=vipInfoList.get(i);
    			//UserInfoBo userInfoBo=UserInfoBo.entityToBo(entity.getUserInfo());
    			UserBo bo=UserBo.entityToBo(entity);
    			//bo.setUserInfo(userInfoBo);
    			//将状态编码转换成中文
    			Map<String,Object> queryMap=new HashMap<String, Object>();
    			queryMap.put("codeName", GlobalConstant.VIP_STATUS);
    			Map<String,Object> infoMap=codeManage.getCodeValue(queryMap);
    			//会员状态
    			if(!StringUtil.isNullOrEmpty(infoMap)){
    				bo.setUserStatusName(infoMap.get(bo.getUserStatus())+"");
    			}
    			//会员等级
    			queryMap.put("codeName", GlobalConstant.VIP_RANK);
    			Map<String,Object> vipMap=codeManage.getCodeValue(queryMap);
    			if(!StringUtil.isNullOrEmpty(vipMap)){
    				bo.setUserGroupName(vipMap.get(bo.getUserGroup())+"");
    			}
    			resultList.add(bo);
    		}*/
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "queryVipInfoList 查询会员列表失败",e);
		}
		return resultList;
	}
	public List<UserBo> queryVipInfoList(Map<String, Object> map){
		List<UserBo> resultList = new ArrayList<UserBo>();
		List<UserEntity> vipInfoList = new ArrayList<UserEntity>();
		try {
			//设置数据库类型: 网站全局库(01)
    		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
    		vipInfoList = (List<UserEntity>)baseDao.findForList("findVipInfoList", map);
    		for(int i=0;i<vipInfoList.size();i++){
    			UserEntity entity=vipInfoList.get(i);
    			UserInfoBo userInfoBo=UserInfoBo.entityToBo(entity.getUserInfo());
    			UserBo bo=UserBo.entityToBo(entity);
    			bo.setUserInfo(userInfoBo);
    			//将状态编码转换成中文
    			Map<String,Object> queryMap=new HashMap<String, Object>();
    			queryMap.put("codeName", GlobalConstant.VIP_STATUS);
    			Map<String,Object> infoMap=codeManage.getCodeValue(queryMap);
    			//会员状态
    			if(!StringUtil.isNullOrEmpty(infoMap)){
    				bo.setUserStatusName(infoMap.get(bo.getUserStatus())+"");
    			}
    			//会员等级
    			queryMap.put("codeName", GlobalConstant.VIP_RANK);
    			Map<String,Object> vipMap=codeManage.getCodeValue(queryMap);
    			if(!StringUtil.isNullOrEmpty(vipMap)){
    				bo.setUserGroupName(vipMap.get(bo.getUserGroup())+"");
    			}
    			//将地区编码转换为中文
				/*if(!StringUtil.isNullOrEmpty(userInfoBo.getCodeArea())) {
					AreaBo areaBo = new AreaBo();
					AreaEntity areaEntity = new AreaEntity();
					areaBo.setCode(userInfoBo.getCodeArea());
					areaEntity = (AreaEntity) baseDao.findForObject("findAreaByCode", areaBo);
					if (!StringUtil.isNullOrEmpty(areaEntity)) {
						areaBo = AreaBo.entityToBo(areaEntity);
						bo.getUserInfo().setCodeAreaName(areaBo.getText());
					}
				}*/
    			resultList.add(bo);
    		}
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "queryVipInfoList 查询会员列表失败",e);
		}
		return resultList;
	}
	public List<UserBo> queryOrganInfoList(Map<String, Object> map, int skip, int max){
		List<UserBo> resultList = new ArrayList<UserBo>();
		List<UserEntity> vipInfoList = new ArrayList<UserEntity>();
		try {
			//设置数据库类型: 网站全局库(01)
    		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
    		vipInfoList = (List<UserEntity>)baseDao.findForList("findOrganInfoList", map,skip,max);
    		for(int i=0;i<vipInfoList.size();i++){
    			String userId=vipInfoList.get(i).getUserId();
    			//根据userId查询会员信息
    			Map<String,Object> userMap=new HashMap<String,Object>();
    			userMap.put("userId", userId);
    			UserEntity entity=(UserEntity) baseDao.findForObject("findVipManageById", userMap);
    			OrganizationUserInfoEntity organInfoEntity=(OrganizationUserInfoEntity) baseDao.findForObject("findOrganInfoDetail", userMap);
    			OrganizationUserInfoBo organInfoBo=OrganizationUserInfoBo.getEntityToBo(organInfoEntity);
    			UserBo bo=UserBo.entityToBo(entity);
    			bo.setOrgInfoBo(organInfoBo);
				//真实姓名单独设置
				bo.setUserName(organInfoBo.getOrgName());
    			//将状态编码转换成中文
    			Map<String,Object> queryMap=new HashMap<String, Object>();
    			queryMap.put("codeName", GlobalConstant.VIP_STATUS);
    			Map<String,Object> infoMap=codeManage.getCodeValue(queryMap);
    			//会员状态
    			if(!StringUtil.isNullOrEmpty(infoMap)){
    				bo.setUserStatusName(infoMap.get(bo.getUserStatus())+"");
    			}
    			//会员等级
    			queryMap.put("codeName", GlobalConstant.VIP_RANK);
    			Map<String,Object> vipMap=codeManage.getCodeValue(queryMap);
    			if(!StringUtil.isNullOrEmpty(vipMap)){
    				bo.setUserGroupName(vipMap.get(bo.getUserGroup())+"");
    			}
    			//会员IR
    			if(bo.getUserIrId()!=null&&bo.getUserIrId()!=""){
    				String id= bo.getUserIrId();
    				Map<String,Object> irmap=new HashMap<String, Object>();
    				queryMap.put("irId",id);
    				IrEntity ir= (IrEntity) baseDao.findForObject("findIrByCrmId", map);
    				bo.setUserId(ir.getIrName());
    			}
    			//将地区编码转换为中文
    			/*if(!StringUtil.isNullOrEmpty(organInfoBo.getCodeArea())){
    				AreaBo areaBo=new AreaBo();
        			AreaEntity areaEntity=new AreaEntity();
        			areaEntity.setCode(organInfoBo.getCodeArea());
        			areaBo.setCode(organInfoBo.getCodeArea());
        			areaEntity=(AreaEntity) baseDao.findForObject("findAreaByCode", areaEntity);
        			if(!StringUtil.isNullOrEmpty(areaEntity)){
        				areaBo=AreaBo.entityToBo(areaEntity);
        				bo.getOrgInfoBo().setCodeAreaName(areaBo.getText());
        			}
    			}*/
    			resultList.add(bo);
    		}
		} catch (Exception e) {
			BaseLog.e(this.getClass(), "queryVipInfoList 查询会员列表失败",e);
		}
		return resultList;
	}
	public int queryVipInfoListCount(Map<String, Object> map){
		int count=0;
		try {
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ; 
			if(!map.containsValue("organ")){
				count = (Integer)baseDao.findForObject("findUsersListCount", map);
			}else{
				count = (Integer)baseDao.findForObject("findOrganInfoListCount", map);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	/**
	 * @title： 用户信息    新增
	 * @param: user   系统用户信息实体类
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:25
	 * @return： String 新增用户成功、失败信息
	 */
	public String addUser(UserBo user) {
		String backInfo="保存成功";
		try{
			UserEntity entity=UserBo.boToEntity(user);
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			//序列获取用户标识
			entity.setUserId(baseDao.getSerialNo(SeqConstant.SEQ_SYS_SUPPORT)+"");
			//添加用户
			baseDao.addObject("addVipManage", entity);
			
		}catch(Exception e){
			e.printStackTrace();
			backInfo="保存失败";
		    BaseLog.e(this.getClass(), "addSysOper 添加运维人员", e);
		    throw new BaseException("添加运维人员出错！",e);
		}
		return backInfo;
	}
	/**
	 * @title： 用户信息修改页面初始化
	 * @description: 对不同系统用户信息的查询修改数据初始化
	 * @param: userId   用户标识
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:25
	 * @return： user   系统用户信息实体类
	 */
	public Users updUserInit(String userId) {
		Users bo=null;
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			Map<String,Object> param=new HashMap<String, Object>();
			param.put("userId", userId);
			bo = (Users)baseDao.findForObject("findUsersByMap", param);
		}catch(Exception e){
			e.printStackTrace();
		    BaseLog.e(this.getClass(), "upSysOperInit 修改运维人员前查询", e);
		    throw new BaseException("修改运维人员前查询出错！",e);
		}
		return bo;
	}
	/**
	 * 个人明细查询
	 */
	public UserInfoBo findUserInfoDetail(String userId){
		UserInfoBo bo=null;
		UserInfoEntity entity = null;
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			Map<String,Object> param=new HashMap<String, Object>();
			param.put("userId", userId);
			entity = (UserInfoEntity)baseDao.findForObject("findUserInfoDetail", param);
			if(!StringUtil.isNullOrEmpty(entity)){
				bo=UserInfoBo.entityToBo(entity);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		    BaseLog.e(this.getClass(), "个人明细查询", e);
		    throw new BaseException("个人明细查询出错！",e);
		}
		return bo;
	}
	/**
	 * 机构明细查询
	 */
	public OrganizationUserInfoBo findOrganInfoDetail(String userId){
		OrganizationUserInfoBo bo=null;
		OrganizationUserInfoEntity entity = null;
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			Map<String,Object> param=new HashMap<String, Object>();
			param.put("userId", userId);
			entity = (OrganizationUserInfoEntity)baseDao.findForObject("findOrganInfoDetail", param);
			if(!StringUtil.isNullOrEmpty(entity)){
				bo=OrganizationUserInfoBo.getEntityToBo(entity);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		    BaseLog.e(this.getClass(), "机构明细查询", e);
		    throw new BaseException("机构明细查询出错！",e);
		}
		return bo;
	}
	/**
	 * @title： 用户信息	修改
	 * @description: 修改系统用户的信息,对系统用户信息的修改操作
	 * @param: user   系统用户信息实体类
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:30
	 * @return： String 修改用户信息的成功、失败信息
	 */
	public String updUser(Users bo) {
		String backInfo = "修改成功";
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.updObject("updAppUsers", bo);
		}catch(Exception e){
			e.printStackTrace();
			backInfo = "修改失败";
		    BaseLog.e(this.getClass(), "upSysOper 修改运维人员", e);
		    throw new BaseException("修改运维人员出错！",e);
        }
		return backInfo;
	}
	public String updUserInfo(UserInfoBo user){
		String backInfo = "操作成功";
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			UserInfoEntity entity=UserInfoBo.boToEntity(user);
			baseDao.updObject("updUserInfo", entity);
			String remark=entity.getRemark();
			UserEntity userEntity=new UserEntity();
			userEntity.setRemark(remark);
			userEntity.setUserId(user.getUserId());
			baseDao.updObject("updVipManage", userEntity);
		}catch(Exception e){
			e.printStackTrace();
			backInfo = "修改失败";
		    BaseLog.e(this.getClass(), "upSysOper 修改运维人员", e);
		    throw new BaseException("修改运维人员出错！",e);
        }
		return backInfo;
	}
	public String updOrganInfo(OrganizationUserInfoBo organ){
		String backInfo = "操作成功";
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			OrganizationUserInfoEntity entity=OrganizationUserInfoBo.getBoToEntity(organ);
			baseDao.updObject("updOrganInfo", entity);
		}catch(Exception e){
			e.printStackTrace();
			backInfo = "修改失败";
		    BaseLog.e(this.getClass(), "upSysOper 修改运维人员", e);
		    throw new BaseException("修改运维人员出错！",e);
        }
		return backInfo;
	}

    /**
     * @title: 会员管理密码重置
     * @description: 会员管理密码重置
     * @author: zhengYunFei
     * @date: 2013-10-15 16:53
     * @return：Boolean
     */
	public Boolean updResetPwd(UserBo vipInfo){
		Boolean flag = true;
		try {
			//设置数据库类型: 网站全局库(01)
    		baseDao.setDbType(FwConstant.DBTYPE_GLOBAL) ;
    		Map<String,Object> map=new HashMap<String, Object>();
    		map.put("userPassword", vipInfo.getUserPassword());
    		map.put("id", vipInfo.getUserId().split(","));
    		baseDao.updObject("updResetPwd", map);
		} catch (Exception e) {
			flag = false;
			BaseLog.e(this.getClass(), "updResetPwd 保存友情链接信息失败", e);
		}
		return flag;
	}
	public String updSelectAllUser(Map<String, Object> queryMap){
		String backInfo = "0";
		try{
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			if(queryMap.containsKey("userStatus")){
				baseDao.updObject("updSelectAllUser", queryMap);
			}
			if(queryMap.containsKey("userGroup")){
				baseDao.updObject("updGroupSelectAllUser", queryMap);
			}
			backInfo="1";
		}catch(Exception e){
			backInfo="0";
		}
		return backInfo;
	}
	/**
	 * @title： 用户信息	删除
	 * @description: 删除指定用户标识的系统用户信息,对多个系统用户信息的删除操作
	 * @param: userId   用户标识（多个用户时用,号隔开）
	 * @author: zhengYunFei
     * @date: 2013-07-08 16:40
	 * @return： String 删除用户信息的成功、失败信息
	 */
	@SuppressWarnings("unchecked")
	public String delUserById(String userIds) {
		SqlMapClient client =  baseDao.getSqlMapClient();
		String backInfo = "0";
		try{
			Map map = new HashMap();
			map.put("id",userIds.split(","));
			client.startTransaction();//开启事物
			client.startBatch();//开启批处理模式
			baseDao.setDbType(FwConstant.DBTYPE_GLOBAL);
			baseDao.delObject("delAppUsers", map);
			//baseDao.delObject("deleleUserInfo", map);
			//删除成功
			backInfo = "1";
		}catch(Exception e){
			try {
				client.getCurrentConnection().rollback();//事务回滚
			} catch (SQLException e1) {
				backInfo = "0";	//删除失败
			    BaseLog.e(this.getClass(), "删除用户信息错误", e);
			    throw new BaseException("删除用户信息出错！",e);
			}
		}finally{
            try {
                client.commitTransaction();//提交事物
                client.endTransaction();//结束事物
            } catch (Exception e) {
                BaseLog.e(this.getClass(), "删除用户信息提交结束事物异常!", e);
            }
        }
		return backInfo;
	}
	
}