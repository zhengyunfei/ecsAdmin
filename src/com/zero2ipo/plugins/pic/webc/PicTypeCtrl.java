package com.zero2ipo.plugins.pic.webc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.plugins.pic.bizc.IPicType;
import com.zero2ipo.plugins.pic.bo.PictureTypeBo;
/**
 * 
 * @title 图片管理
 * @author ZhengYunFei
 * @date 2014-9-10
 */
@Controller
@RequestMapping("/picType")
public class PicTypeCtrl extends BaseCtrl{
	@Autowired @Qualifier("picType")
	private IPicType picType;
	/**
	 *@title 页面初始化
	 *@date 2014-9-10
	 *@author ZhengYunfei
	 * @return
	 */
	@RequestMapping("forInit.shtml")
	public ModelAndView forInit(String name,String backInfo,String id){
		ModelAndView mv=new ModelAndView();
		List<PictureTypeBo> list=new ArrayList<PictureTypeBo>();
		Map<String,Object> map=new HashMap<String, Object>();
		if(!StringUtil.isNullOrEmpty(name)){
			map.put("name",name);
			mv.addObject("name",name);
		}
		list=picType.find(map);
		mv.addObject("resultList",list);
		if(!StringUtil.isNullOrEmpty(backInfo)){
			mv.addObject("backInfo",backInfo);
		}
		if(!StringUtil.isNullOrEmpty(id)){
			mv.addObject("id",id);
		}else{
			mv.addObject("id","");
		}
		mv.setViewName("/s9/pic/picManage_init.jsp");
		return mv;
	}
	/**
	 *@title find页面初始化
	 *@date 2014-9-10
	 *@author ZhengYunfei
	 * @return
	 */
	@RequestMapping("forFindInit.shtml")
	public ModelAndView forFindInit(String id){
		ModelAndView mv=new ModelAndView();
		PictureTypeBo bo=picType.find(id);
		mv.addObject("bo",bo);
		mv.setViewName("/s9/pic/updateType.jsp");
		return mv;
	}
	/**
	 *@title add页面初始化
	 *@date 2014-9-10
	 *@author ZhengYunfei
	 * @return
	 */
	@RequestMapping("forAddInit.shtml")
	public ModelAndView forAddInit(){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/s9/pic/addType.jsp");
		return mv;
	}
	/**
	 *@title新增
	 *@date 2014-9-10
	 *@author ZhengYunfei
	 * @return
	 */
	@RequestMapping("add.shtml")
	@ResponseBody
	public int add(PictureTypeBo bo){
		int count=0;
		count=picType.add(bo);
		return count;
	}
	/**
	 *@title修改
	 *@date 2014-9-10
	 *@author ZhengYunfei
	 * @return
	 */
	@RequestMapping("update.shtml")
	@ResponseBody
	public int update(PictureTypeBo bo){
		int count=0;
		count=picType.update(bo);
		return count;
	}
	/**
	 *@title删除
	 *@date 2014-9-10
	 *@author ZhengYunfei
	 * @return
	 */
	@RequestMapping("delete.shtml")
	@ResponseBody
	public int delete(String ids){
		int count=0;
		count=picType.delete(ids);
		return count;
	}
}
