package com.zero2ipo.plugins.servlet;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.zero2ipo.common.Regexs;
import com.zero2ipo.plugins.menu.biz.IsysMenu;
import com.zero2ipo.plugins.user.bo.User;

/**
 * @title springMVC权限过滤器以及登录过滤器
 * @author ZhengYunFei
 * @date 2014-9-29
 */
@Repository
public class SystemInterceptor extends  HandlerInterceptorAdapter {
	//自动注入菜单项接口
	@Resource(name = "menu")
	private IsysMenu sysMenu;
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		  HttpServletRequest req = request;
	      HttpServletResponse res = response;
	        String uri = Regexs.splitUrl(req.getRequestURI());
	        uri=uri.replace("//", "/");
	        String o_url = req.getRequestURI();
	   
	        req.setAttribute("path", req.getContextPath());
	        //过滤器器中维护的特定url页面不进行拦截
	        if(!uri.matches("/c/login")&&!o_url.contains(".html")&&!uri.matches("/c")){
	            //如果没有登陆，或者请求session超时都返回重新登陆 
	            User so = (User) req.getSession().getAttribute("user");
	            if(so == null || so.equals("")){
	                PrintWriter out = res.getWriter() ;
	                out.print("<html>") ;
	                out.print("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />") ;
	                out.print("<script> ") ;   
	                out.print("window.top.location='../index.html?errorType=7';") ; 
	                out.print("</script>") ; 
	                out.print("</html>") ;
	                return false;
	            }else{
	            	//已经登录的用户，防止其越权，访问其没有权限的url
	            	//根据此登录用户的ID查询该用户所拥有的url权限
	            	String userId=so.getUserId();
	            	List<String> list=sysMenu.findMenuListByUserId(userId);
	            	//判断uri是否存在list中，如果存在
	            	if(!list.contains(uri)){
	            		PrintWriter out = res.getWriter() ;
	            		//非法操作,该用户试图访问无权限的uri
	 	                out.print("<html>") ;
	 	                out.print("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />") ;
	 	                out.print("<script> ") ;   
	 	                out.print("window.top.location='../index.html?errorType=10';") ; 
	 	                out.print("</script>") ; 
	 	                out.print("</html>") ;
	            		return false;
	            	}
	            }
	         }
	        return super.preHandle(request, response, handler);  
	}
}
