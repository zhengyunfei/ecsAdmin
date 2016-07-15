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
import com.zero2ipo.plugins.pic.bizc.IPicManage;
import com.zero2ipo.plugins.pic.bo.PictureBo;
/**
 * 
 * @title 图片管理
 * @author ZhengYunFei
 * @date 2014-9-10
 */
@Controller
@RequestMapping("/picManage")
public class PicManageCtrl extends BaseCtrl{
	@Autowired @Qualifier("picManage")
	private IPicManage picManage;
	/**
	 *@title 页面初始化
	 *@date 2014-9-10
	 *@author ZhengYunfei
	 * @return
	 */
	@RequestMapping("forInit.shtml")
	public ModelAndView forInit(String id){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/s9/pic/picManage_init.jsp");
		mv.addObject("id",id);
		return mv;
	}
	/**
	 *@title find页面初始化
	 *@date 2014-9-10
	 *@author ZhengYunfei
	 * @return
	 */
	@RequestMapping("forFindInit.shtml")
	public ModelAndView forFindInit(String pid,String backInfo,String id){
		ModelAndView mv=new ModelAndView();
		List<PictureBo> list=picManage.findPictureById(pid,id);
		mv.addObject("list",list);
		if(!StringUtil.isNullOrEmpty(pid)){
			mv.addObject("pid",pid);
		}else{
			mv.addObject("pid","");
		}
		if(!StringUtil.isNullOrEmpty(backInfo)){
			mv.addObject("backInfo", backInfo);
		}
		mv.setViewName("/s9/pic/find.jsp");
		return mv;
	}
	/**
	 *@title add页面初始化
	 *@date 2014-9-10
	 *@author ZhengYunfei
	 * @return
	 */
	@RequestMapping("forAddInit.shtml")
	public ModelAndView forAddInit(String pid){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/s9/pic/add.jsp");
		if(!StringUtil.isNullOrEmpty(pid)){
			mv.addObject("pid",pid);
		}else{
			mv.addObject("pid","");
		}
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
	public Map<String,Object> add(PictureBo bo){
		Map<String,Object> map=new HashMap<String, Object>();
		int count=0;
		count=picManage.add(bo);
		map.put("count", count);
		if(!StringUtil.isNullOrEmpty(bo)){
			map.put("id",bo.getPid());
		}
		return map;
	}
	/**
	 *@title update页面初始化
	 *@date 2014-9-10
	 *@author ZhengYunfei
	 * @return
	 */
	@RequestMapping("forUpdateInit.shtml")
	public ModelAndView forUpdateInit(String id,String pid){
		ModelAndView mv=new ModelAndView();
		mv.setViewName("/s9/pic/update.jsp");
		PictureBo bo=new PictureBo();
		List<PictureBo> list=new ArrayList<PictureBo>();
		 list=picManage.findPictureById(pid,id);
		 if(!StringUtil.isNullOrEmpty(list)&&list.size()>0){
			 bo=list.get(0);
		 }
		 mv.addObject("bo",bo);
		if(!StringUtil.isNullOrEmpty(bo)){
			mv.addObject("id",bo.getId());
			mv.addObject("pid",bo.getPid());
		}
		return mv;
	}
	/**
	 *@title修改
	 *@date 2014-9-10
	 *@author ZhengYunfei
	 * @return
	 */
	@RequestMapping("update.shtml")
	@ResponseBody
	public Map<String,Object> update(PictureBo bo){
		Map<String,Object> map=new HashMap<String, Object>();
		int count=0;
		count=picManage.update(bo);
		map.put("count", count);
		if(!StringUtil.isNullOrEmpty(bo)){
			map.put("id",bo.getId());
			map.put("pid",bo.getPid());
		}
		return map;
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
		count=picManage.delete(ids);
		return count;
	}
}
