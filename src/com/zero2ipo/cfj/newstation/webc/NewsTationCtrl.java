package com.zero2ipo.cfj.newstation.webc;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zero2ipo.cfj.newstation.bizc.INewsTation;
import com.zero2ipo.cfj.newstation.bo.NewsTationBo;
import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.log.BaseLog;
   /**
    * cfjNewsTation 实体类
    * Thu Dec 11 10:47:14 GMT+08:00 2014 郑云飞
    */ 
	@Controller
	@RequestMapping("newsTation")
	public class NewsTationCtrl extends BaseCtrl{
	@Autowired @Qualifier("newsTation")
	private INewsTation newsTation;
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
}

