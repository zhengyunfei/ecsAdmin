package com.zero2ipo.cfj.user.webc;

import com.zero2ipo.cfj.invitationCode.bizc.IInvitationCode;
import com.zero2ipo.cfj.invitationCode.bo.InvitationCodeBo;
import com.zero2ipo.cfj.message.bizc.ITelMessage;
import com.zero2ipo.cfj.newstation.bizc.INewsTation;
import com.zero2ipo.cfj.newstation.bo.NewsTationBo;
import com.zero2ipo.cfj.user.bizc.IVipManage;
import com.zero2ipo.cfj.user.bo.OrganizationUserInfoBo;
import com.zero2ipo.cfj.user.bo.UserBo;
import com.zero2ipo.cfj.user.bo.UserInfoBo;
import com.zero2ipo.cfj.user.bo.Users;
import com.zero2ipo.common.GlobalConstant;
import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.plugins.code.biz.ICodeManage;
import com.zero2ipo.plugins.weixin.bizc.IUserWeiXinMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/vipManage")
public class VipManageCtrl  extends BaseCtrl{
	@Autowired @Qualifier("vipManage")
	private IVipManage vipManage;//会员分类业务处理类
	@Autowired @Qualifier("invitationCode")
	private IInvitationCode invitationCode;
	//自动注入codeManage标准代码管理业务操作接口
	@Autowired @Qualifier("codeManage")
	private ICodeManage codeManage;
	@Autowired @Qualifier("telMessage")
	private ITelMessage telMessage;
	@Autowired @Qualifier("newsTation")
	private INewsTation newsTation;
	@Autowired @Qualifier("userWeiXinMsgService")
	private IUserWeiXinMsgService userWeiXinMsgService;
	private static final String organType="organ";
	@RequestMapping("/initPage.shtml")
	public ModelAndView initPage(String userId){
		ModelAndView mv=new ModelAndView();
		mv.addObject("userId",userId);
		mv.setViewName("/s9/newstation/newstation_add.jsp");
		return mv;
	}
	/**
	*新增
	*@author zhengYunFei
	*@date Thu Dec 11 10:47:14 GMT+08:00 2014
	*/
	@RequestMapping("/add.shtml")
	@ResponseBody
	public Map<String,Object>  add(NewsTationBo bo){
		Map<String,Object> map=new HashMap<String, Object>();
		boolean flag = true;
		try{
			newsTation.add(bo);
		}catch (Exception e) {
			flag=false;
			BaseLog.e(this.getClass(), "新增成功", e);
		}
		map.put("result",flag);
		return map;
	}
	/*
	 * 发送处理类注入
	 */

    /**
     * @title: 初始会员页面
     * @description: 初始会员页面
     * @author: zhengYunFei
     * @date: 2013-10-15 16:53
     * @return：/s9/vip/vipManage_init.jsp
     */
    @RequestMapping("forInit.shtml")
    public ModelAndView forInit(String userType){
    	ModelAndView mv=new ModelAndView();
    	if(organType.equals(userType)){
    		mv.setViewName("/s9/vip/organManage_init.jsp");
    	}else{
    		mv.setViewName("/s9/vip/vipManage_init.jsp");
    	}
    	//会员状态
    	Map<String,Object> queryMap=new HashMap<String, Object>();
		queryMap.put("codeName", GlobalConstant.VIP_STATUS);
		Map<String,Object> infoMap=codeManage.getCodeValue(queryMap);
		mv.addObject("statusMap",infoMap);
		//会员等级
		Map<String,Object> vipRankMap=new HashMap<String, Object>();
		vipRankMap.put("codeName", GlobalConstant.VIP_RANK);
		Map<String,Object> vipRankResultMap=codeManage.getCodeValue(vipRankMap);
		mv.addObject("vipRankMap",vipRankResultMap);
        return mv;
    }
    @RequestMapping("initAreaPage.shtml")
    public ModelAndView initAreaPage(String userId,String codeArea,String type){
    	ModelAndView mv=new ModelAndView("../s9/area/addArea.jsp");
    	mv.addObject("userId",userId);
    	mv.addObject("codeArea",codeArea);
    	mv.addObject("type",type);
    	return mv;
    }
    
    /**
     * @title: 初始会员页面
     * @description: 初始会员页面
     * @author: zhengYunFei
     * @date: 2013-10-15 16:53
     * @return：/s9/vip/vipManage_init.jsp
     */
    @RequestMapping("vipManageAddInit.shtml")
    public ModelAndView forAddInit(){
    	ModelAndView mv=new ModelAndView("/s9/vip/vipManage_add.jsp");
    	Map<String,Object> queryMap=new HashMap<String, Object>();
		queryMap.put("codeName", GlobalConstant.USER_APPLY_STATUS);
		Map<String,Object> infoMap=codeManage.getCodeValue(queryMap);
		mv.addObject("statusMap",infoMap);
        return mv;
    }
    /**
     * @title: 查找会员分类信息列表
     * @description:查找会员信息列表
     * @param : curNo  分页-页码
     * @param : curSize  分页-页面加载记录条数
     * @author: zhengYunFei
     * @date: 2013-10-15 16:53
     * @return:jsonMap
     */
    @RequestMapping("vipInfoList.shtml")
    @ResponseBody
    public Map<String,Object>  vipInfoList(String curNo, String curSize, String mobile,String userName,String userStatus,String userType){
    	 Map<String,Object> jsonMap = new HashMap<String, Object>();
    	 try {
    		 /************* 分页处理 ************/
 			int skip;
 			int max;
 			if (StringUtil.isNullOrEmpty(curNo))
 				curNo = "0";
 			if (StringUtil.isNullOrEmpty(curSize))
 				curSize = "20";
 			skip = Integer.parseInt(curNo);
 			max = Integer.parseInt(curSize);
 			/************  分页处理结束 ***********/
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mobile", mobile);		//用户手机号
			int total=0;
        	total=vipManage.queryVipInfoListCount(map);
        	List<Users> vipInfo = null;
        	if(total>0){
				vipInfo = vipManage.queryVipInfoList(map, (skip-1)*max, max);
        	}
        	jsonMap.put("Rows", vipInfo);
    		jsonMap.put("Total", total);
		} catch (Exception e) {
			e.printStackTrace();
			BaseLog.e(this.getClass(), "forLinkTypeinitAjax.shtml:会员分类信息初始化有误", e);
		}
		return jsonMap;
    }
    /**
	 * @title： 用户信息    新增
	 * @description: 存储系统用户的信息,不同管理人员对用户信息的添加操作
	 * @param: user   系统用户信息实体类
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:25
	 * @return： ModelAndView 新增用户成功、失败信息
	 */
    @RequestMapping("addUser.shtml")
    public ModelAndView addUser(UserBo user) {
    	ModelAndView mv=new ModelAndView("/s9/vip/vipManage_add.jsp");
    	String backInfo = null;
    	try{
			backInfo = vipManage.addUser(user);
    	}catch (Exception e) {
    		BaseLog.e(this.getClass(), "addSysOper 添加运维人员", e);
		}
    	mv.addObject("backInfo", backInfo);
    	mv.addObject("user", user);
		return mv;
    }
    /**
	 * @title： 用户信息	修改初始化
	 * @description: 修改系统用户的信息,对系统用户信息的修改操作
	 * @param: userId   系统用户标识
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:30
	 * @return： ModelAndView 修改用户信息的成功、失败信息
	 */
    @RequestMapping("updUserInit.shtml")
    public ModelAndView updUserInit(String userId) {
    	ModelAndView mv = new ModelAndView("/s9/vip/vipManage_upd.jsp");
		try {
			//用户信息修改初始化
			Users  user = vipManage.updUserInit(userId);
		    mv.addObject("user", user);
		  /*  Map<String,Object> queryMap=new HashMap<String, Object>();
			queryMap.put("codeName", GlobalConstant.USER_APPLY_STATUS);
			Map<String,Object> infoMap=codeManage.getCodeValue(queryMap);
			mv.addObject("statusMap",infoMap);*/
		} catch (Exception e) {
		    BaseLog.e(this.getClass(), "upSysOperInit 修改运维人员前查询",e);
		}
		return mv;
    }

	/**
	 * @title： 用户信息	修改
	 * @description: 修改系统用户的信息,对系统用户信息的修改操作
	 * @param: user   系统用户信息实体类
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:30
	 * @return： ModelAndView 修改用户信息的成功、失败信息
	 */
    @RequestMapping("updUser.shtml")
    public ModelAndView updUser(Users user) {
    	ModelAndView mv=new ModelAndView("/s9/vip/vipManage_upd.jsp");
    	String backInfo = null;
		int flg=0;
    	try{
			backInfo = vipManage.updUser(user);
			flg=1;
    	}catch (Exception e) {
			flg=0;
			e.printStackTrace();
    		BaseLog.e(this.getClass(), "updUser修改用户信息失败", e);
		}
		mv.addObject("flg",flg);
		mv.addObject("backInfo", backInfo);
    	mv.addObject("user", user);
		return mv;
    }
    /**
     * 个人明细信息查询
     */
    @RequestMapping("findUserInfoDetail.shtml")
    public ModelAndView findUserInfoDetail(String userId){
    	ModelAndView mv=new ModelAndView("/s9/vip/vipManage_detail.jsp");
    	UserInfoBo infoBo=vipManage.findUserInfoDetail(userId);
		//UserBo userBo=vipManage.updUserInit(userId);
    	mv.addObject("userInfo",infoBo);
    	//mv.addObject("userBo",userBo);
    	mv.addObject("userId",userId);
    	return mv;
    }
    /**
     * 机构明细信息查询
     */
    @RequestMapping("findOrganInfoDetail.shtml")
    public ModelAndView findOrganInfoDetail(String userId){
    	ModelAndView mv=new ModelAndView("/s9/vip/vipManage_organ_detail.jsp");
    	OrganizationUserInfoBo bo=vipManage.findOrganInfoDetail(userId);
    	mv.addObject("organUserInfo",bo);
    	mv.addObject("userId",userId);
    	mv.addObject("type","organ");
    	return mv;
    }
	/**
	 * @title： 用户信息	修改
	 * @description: 修改系统用户的信息,对系统用户信息的修改操作
	 * @param: user   系统用户信息实体类
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:30
	 * @return： ModelAndView 修改用户信息的成功、失败信息
	 */
    @RequestMapping("updUserAjax.shtml")
    @ResponseBody
    public Map<String,Object> updUserAjax(UserBo user) {
    	Map<String,Object> returnMap=new HashMap<String, Object>();
    	boolean flag=true;
    	try{
    		// vipManage.updUser(user);
    	}catch (Exception e) {
    		flag=false;
    		BaseLog.e(this.getClass(), "updUser修改用户信息失败", e);
		}
    	returnMap.put("result", flag);
		return returnMap;
    }
    /**
	 * @title： 用户信息	修改
	 * @description: 修改系统用户的信息,对系统用户信息的修改操作
	 * @param: user   系统用户信息实体类
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:30
	 * @return： ModelAndView 修改用户信息的成功、失败信息
	 */
    @RequestMapping("updUserInfoAjax.shtml")
    @ResponseBody
    public Map<String,Object> updUserInfoAjax(UserBo user,String type) {
    	String result="";
    	Map<String,Object> returnMap=new HashMap<String, Object>();
    	try{
    		//result=vipManage.updUser(user);
    		//returnMap.put("message", result);
    		returnMap.put("result", true);
    	}catch (Exception e) {
			e.printStackTrace();
    		returnMap.put("result", false);
    		BaseLog.e(this.getClass(), "updUser修改用户信息失败", e);
		}
		return returnMap;
    }
    /**
	 * @title： 用户信息	修改
	 * @description: 修改系统用户的信息,对系统用户信息的修改操作
	 * @param: user   系统用户信息实体类
	 * @author: zhengYunFei
     * @date: 2013-07-08 10:30
	 * @return： ModelAndView 修改用户信息的成功、失败信息
	 */
    @RequestMapping("updOrganInfoAjax.shtml")
    @ResponseBody
    public Map<String,Object> updOrganInfoAjax(OrganizationUserInfoBo organ) {
    	String result="";
    	Map<String,Object> returnMap=new HashMap<String, Object>();
    	try{
    		result=vipManage.updOrganInfo(organ);
    		returnMap.put("message", result);
    		returnMap.put("result", true);
    	}catch (Exception e) {
    		returnMap.put("result", false);
    		BaseLog.e(this.getClass(), "updUser修改用户信息失败", e);
		}
		return returnMap;
    }
    /**
     * @title: 初始会员管理密码重置页面
     * @description: 初始会员管理密码重置页面
     * @author: zhengYunFei
     * @date: 2013-10-15 16:53
     * @return：/s9/vip/vipManage_pwdReset.jsp
     */
    @RequestMapping("initPwdReset.shtml")
    public ModelAndView initPwdReset(String userId){
    	ModelAndView mv = new ModelAndView("/s9/vip/vipManage_pwdReset.jsp");
    	mv.addObject("userId", userId);
    	return mv;
    }
    /**
     * @title: 会员管理密码重置
     * @description: 会员管理密码重置
     * @author: zhengYunFei
     * @date: 2013-10-15 16:53
     * @return：/s9/vip/vipManage_pwdReset.jsp
     */
    @RequestMapping("updResetPwd.shtml")
    public ModelAndView updResetPwd(UserBo vipInfo){
    	ModelAndView mv = new ModelAndView("/s9/vip/vipManage_pwdReset.jsp");
    	try {
    		if(vipManage.updResetPwd(vipInfo)){
    			mv.addObject("msg", "sucess");
    		}
		} catch (Exception e) {
			mv.addObject("msg", "error");
			BaseLog.e(this.getClass(), "updResetPwd:会员管理密码重置有误", e);
		}
    	return mv;
    }
    /**
	 * @title： 用户信息	删除
	 * @description: 删除指定用户标识的系统用户信息,对多个系统用户信息的删除操作
	 * @param: userIds   用户标识（多个用户时用,号隔开）
	 * @author: zhengYunFei
     * @date: 2013-07-08 16:40
	 * @return： Map 删除用户信息的成功、失败信息
	 */
    @RequestMapping("delUser.shtml")
    @ResponseBody
    public Map<String, Object> delUser(String userIds) {
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		String backInfo = "";
		backInfo = vipManage.delUserById(userIds);
		jsonMap.put("message", backInfo);
    	return jsonMap;
    }
    
    /**
	 * @title： 批量操作
	 * @param: userIds   用户标识（多个用户时用,号隔开）
	 * @author: zhengYunFei
     * @date: 2013-07-08 16:40
	 */
    @RequestMapping("updSelectAllUser.shtml")
    @ResponseBody
    public Map<String, Object> updSelectAllUser(String userIds,String userStatus,String userGroup,String vipStatus) {
		Map<String,Object> jsonMap = new HashMap<String, Object>();
		Map<String,Object> queryMap=new HashMap<String,Object>();
		if(!StringUtil.isNullOrEmpty(userStatus)){
			queryMap.put("userStatus", userStatus);
		}
		if(!StringUtil.isNullOrEmpty(userGroup)){
			queryMap.put("userGroup", userGroup);
		}
		queryMap.put("id", userIds.split(","));
		String backInfo = "";
		backInfo = vipManage.updSelectAllUser(queryMap);
		//如果原先是待审核状态，现在修改为审核通过的话，需要自动生成邀请码
		if(vipStatus.equals(GlobalConstant.USER_VIP_STATUS_01)&&userStatus.equals(GlobalConstant.USER_VIP_STATUS_02)){
			InvitationCodeBo bo=new InvitationCodeBo();
			bo.setUserId(userIds);
			invitationCode.autoAdd(bo);
		}
		//审核通过,发送微信消息
		if(userStatus.equals(GlobalConstant.USER_VIP_STATUS_02)||userStatus.equals(GlobalConstant.USER_VIP_STATUS_03)){
			String ids[]=userIds.split(",");
			for(int i=0;i<ids.length;i++){
				//UserBo bo=vipManage.updUserInit(ids[i]);
				/*UserEntity entity=UserBo.boToEntity(bo);
				if(!StringUtil.isNullOrEmpty(entity.getOpenId())){
					if(userStatus.equals(GlobalConstant.USER_VIP_STATUS_02)){
						userWeiXinMsgService.sendWeixin_sh_success(entity);
					}else{//审核不通过,发送微信消息
						userWeiXinMsgService.sendWeixin_sh_error(entity);
					}
				}*/
			}

		}
		jsonMap.put("message", backInfo);
    	return jsonMap;
    }
    @RequestMapping("sendMsg.shtml")
    @ResponseBody
    public Map<String,Object> sendMsg(String mobile,String userStatus,String userName){
    	Map<String,Object> jsonMap = new HashMap<String, Object>();
    	String result = sendMsgContent(mobile, userStatus, userName);
		jsonMap.put("result", result);
    	return jsonMap;
    }
	public String sendMsgContent(String mobile, String userStatus, String userName) {
		String value=GlobalConstant.MESSAGE_INFO;
    	String codeValue=value+userStatus;
		if(userStatus.equals("all")){
			codeValue=value;
		}
    	//根据状态status判断发送短信的内容
    	String content=codeManage.findCodeNameByValue(codeValue);
    	String contents="";
    	int length=0;
    	String userObj[]=userName.split(",");
    	length=userObj.length;
    	for(int i=0;i<length;i++){
    		if(i<length-1){
    			contents+=content.replace("***", userObj[i])+",";
    		}else{
    			contents+=content.replace("***", userObj[i]);
    		}
    	}
    	/*//接受返回结果
		String result = sendMessage.mdgxsend(mobile, contents, "", "", "", "");
		//记录短信发送内容
		for(int i=0;i<length;i++){
			TelMessageBo bo=new TelMessageBo();
    		bo.setContent(content);
    		bo.setTel(mobile.split(",")[i]);
    		bo.setTypy(userStatus);
    		bo.setReturnCode(result.split(",")[0]);
    		telMessage.add(bo);
		}*/
		return "";
	}
   //放大图片页面
	@RequestMapping("picPage.shtml")
	public ModelAndView picPage(String src){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/s9/vip/pic.jsp");
		mv.addObject("src",src);
		return mv;
	}
}