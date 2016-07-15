package com.codestyle.bizmodel.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.codestyle.bizmodel.bo.Sample;
import com.codestyle.common.business.web.SampleBaseCtrl;
import com.google.gson.JsonObject;


/**
 * @title :spring mvc webc层编码规范示例
 * @description :描述了一个controller的编码示例
 * @author: yangyi
 * @date: 2012-08-15
 */
@Controller
@RequestMapping("/sample")
public class SampleCtrl extends SampleBaseCtrl{
    /****
     * 注解使用说明：
     * @RequestMapping ：
     * @ResponseBody ：
     * @Qualifier ：
     * @Autowired ：
     * */
	//成员变量自动注入示例，自动注入userInfoService业务操作对象
	@Autowired @Qualifier("userInfoService")
//	private IUserInfoService userInfoService;//注释
	
	//示例方法1，常规请求方法示例
	/**
     * @title :用户登陆
     * @description :1，描述了登录的注释规范和修改格式
     *               2，Action层不做具体业务实现的处理，只是参数的接收和逻辑的判断，
     *                  具体业务实现都放在相应的bizc层
     * @param :wusers 用户信息
     * @return: ModelAndView:返回数据模型以及视图名称
     */
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request,HttpServletResponse response,HttpSession session,Cookie cookie,Sample wusers){
	    // 变量声明
		ModelAndView mv = new ModelAndView("/jsp/ms/login_suc.jsp");
	    try {
    		//1, 校验用户名和密码格式
    			// 具体的验证代码 Validate....
    		//2, 校验用户名和密码是否正确
    			// todo...
    		
    		//3, 判断用户是否激活
	        //add|upd|del by zhangsan_2010-07-06 描述：根据新需求添加了用户激活判断的业务逻辑 
    		    // 张三添加|更新|删除的判断激活代码。。。。
	        //end add|upd|del 
    		
    		// 修改描述：李四   2010-08-01 修改了激活方式 
    		// 李四修改的激活方式代码。。。。
	        
	        //mv.set...添加参数到页面
    		//mv.addObject(String attributeName, Object attributeValue);
	        //4, 登录成功
    			// todo...
	    } catch (Exception e) {
//            Log.e("login:登陆异常", e);
            mv.setViewName("/jsp/common/500.shtml");
        }
		return mv;
	}
	
    //示例方法2，json数据的处理
	/**
     * @title :手机绑定
     * @description :1，描述了手机绑定的注释规范和修改格式
     *               2，Action层不做具体业务实现的处理，只是参数的接收和逻辑的判断，
     *                  具体业务实现都放在相应的bizc层
     * @param :wusers 用户信息
     * @param :mobileCode 用户信息
     * @param :wusers 用户信息
     * @return: String:响应json格式的字符串
     */
	@RequestMapping("/mobileBind")
	@ResponseBody
	public String mobileBind(Sample wusers,String mobileCode,String code) {
		JsonObject jsonObject=new JsonObject();
		try {
			//手机验证码以及格式验证
				// 具体的验证代码 Validate....
			//绑定手机
			   // todo...
		} catch (Exception e) {
//			Log.e("mobileBind:手机绑定异常", e);
		}
		return jsonObject.toString();
	}
}
