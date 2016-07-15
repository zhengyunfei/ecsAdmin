package com.zero2ipo.plugins.weixin.webc;

import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.log.BaseLog;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.plugins.code.biz.ICodeManage;
import com.zero2ipo.plugins.user.bo.User;
import com.zero2ipo.plugins.weixin.bo.WeixinMain;
import com.zero2ipo.plugins.weixin.utils.GetAccessTokenUtil;
import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.wxuser.user.model.Wxuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求处理的核心类
 *
 */
@Controller
@RequestMapping("wx")
public class WeiXinUserCrl extends BaseCtrl{
	    private static final long serialVersionUID = 4440739483644821986L;
	    @Autowired
		private ICodeManage codeManage;
		/**
		 * 页面初始化
		 *@title
		 *@date 2014-10-11
		 *@author ZhengYunfei
		 * @return
		 */
		 @RequestMapping("forInit.shtml")
		public ModelAndView forInit(){
			ModelAndView mv=new ModelAndView();
			mv.setViewName("/s9/weixin/index.jsp");
			return mv;
		}

	/**
	 * 加载所有微信用户数据
	 * @return
	 */
	    @RequestMapping("findAllWeiXinUser.shtml")
		@ResponseBody
	    public Map<String,Object> findUserInfoListAjax(String curNo,String curSize,String curPgn,
	    		String userNo,String nickname,String appid,String secret,String openId,String country,String province,String city,String sex){
			Map<String,Object> jsonMap = new HashMap<String,Object>();
	    	try {
				/************* 分页处理开始 ************/
				int skip;
				int max;
				if (curNo == null || "".equals(curNo))
					curNo = "0";
				if (curSize == null || "".equals(curSize))
					curSize = "20";
				if (curPgn == null || "".equals(curPgn))
					curPgn = "1";
				skip = Integer.parseInt(curNo);
				max = Integer.parseInt(curSize);
				request.setAttribute("curNo", curNo);
				request.setAttribute("curSize", curSize);
				/************* 分页处理结束 ************/ 
				Map<String, Object> map = new HashMap<String, Object>();
				if(!StringUtil.isNullOrEmpty(nickname)){
					map.put("nickname", nickname);	//用户名称
				}
				if(!StringUtil.isNullOrEmpty(openId)){
					map.put("openid", openId);
				}
				if(!StringUtil.isNullOrEmpty(province)){
					map.put("province",province);
				}
				if(!StringUtil.isNullOrEmpty(city)){
					map.put("city",city);
				}
				if(!StringUtil.isNullOrEmpty(sex)){
					map.put("sex",sex);
				}
				User user=(User) session.getAttribute("user");
				//获取当前登录的用户
				String userId="";
				if(!StringUtil.isNullOrEmpty(user)){
					userId= user.getUserId();
					map.put("unionid", userId);
				}
				int total=0;
				total=codeManage.findWxUserCount(map);
				List<Wxuser> result=new ArrayList<Wxuser>();
				result=codeManage.findWxUserList(map,(skip - 1) * max,max);
			    jsonMap.put("Rows", result);
			    jsonMap.put("Total",total);
			} catch (Exception e) {
				e.printStackTrace();
			    BaseLog.e(this.getClass(),"queryOperationPersonList 查询运维人员列表", e);
			}
			return jsonMap;
	    }
	/**
	 * export导出文件
	 */
	@RequestMapping(value="/exportToTxt.shtml",method={RequestMethod.GET})
	public void exportCsv(HttpServletRequest request,HttpServletResponse response,String nickname,String appid,String secret,String openId,String country,String province,String city,String sex){
		ModelAndView mav=new ModelAndView();
		Map<String, Object> map = new HashMap<String, Object>();
		if(!StringUtil.isNullOrEmpty(nickname)){
			map.put("nickname", nickname);	//用户名称
		}
		if(!StringUtil.isNullOrEmpty(openId)){
			map.put("openid", openId);
		}
		if(!StringUtil.isNullOrEmpty(province)){
			map.put("province",province);
		}
		if(!StringUtil.isNullOrEmpty(city)){
			map.put("city",city);
		}
		if(!StringUtil.isNullOrEmpty(sex)){
			map.put("sex",sex);
		}
		User user=(User) session.getAttribute("user");
		//获取当前登录的用户
		String userId="";
		if(!StringUtil.isNullOrEmpty(user)){
			userId= user.getUserId();
			map.put("unionid", userId);
		}
		int total=0;
		total=codeManage.findWxUserCount(map);
		List<Wxuser> list=new ArrayList<Wxuser>();
		list=codeManage.findWxUserList(map);
		//导出txt文件
		response.setContentType("text/plain");
		String fileName="openidlist";
		try {
			fileName = URLEncoder.encode("openidlist", "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.setHeader("Content-Disposition","attachment; filename=" + fileName + ".txt");
		BufferedOutputStream buff = null;
		StringBuffer write = new StringBuffer();
		String enter = "\r\n";
		ServletOutputStream outSTr = null;
		try {
			outSTr = response.getOutputStream(); // 建立
			buff = new BufferedOutputStream(outSTr);
			//把内容写入文件
			if(list.size()>0){
				for (int i = 0; i < list.size(); i++) {
					write.append(list.get(i).getOpenid());
					//write.append(",");
					//write.append(list.get(i).getNickname());
					write.append(enter);
				}
			}
			buff.write(write.toString().getBytes("UTF-8"));
			buff.flush();
			buff.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				buff.close();
				outSTr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 重新加载数据
	 * @throws WexinReqException
	 */
	    @RequestMapping("loadData.shtml")
		@ResponseBody
	    public Map<String,Object> findUserInfoListAjax(String curNo,String curSize,String curPgn,
	    		String userNo,String nickname,String appid,String secret) throws WexinReqException{
	    		Map<String,Object> jsonMap = new HashMap<String,Object>();
	    	try {
				  String accessToken = GetAccessTokenUtil.getAccess_token(appid,secret);
				   List<String> list=WeixinMain.getAllWeiXinUser(accessToken);
				List<Wxuser> result=new ArrayList<Wxuser>();
				User user=(User) session.getAttribute("user");
					//获取当前登录的用户
					String userId="";
					if(!StringUtil.isNullOrEmpty(user)){
						userId= user.getUserId();
					}
			    	codeManage.DelWxUser(userId);
			    	for(int i=0;i<list.size();i++){
						String wxId=list.get(i);
						Wxuser u=WeixinMain.getWechatUserInfo(wxId,accessToken);
						u.setUnionid(userId);
			    		codeManage.addWxUser(u);
			    	}
			    	jsonMap.put("success",true);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
				return jsonMap;
	    }
	  
}
