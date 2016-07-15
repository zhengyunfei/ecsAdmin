package com.zero2ipo.plugins.area.bizc;
import java.util.List;
import java.util.Map;

import com.zero2ipo.plugins.area.bo.AreaBo;
   /**
    * cfjProvCityAreaStreet 实体类
    * Mon Jan 26 11:53:50 GMT+08:00 2015 zhengyunfei
    */ 

public interface IArea{
	/**
	*新增
	*@author zhengYunFei
	*@date Mon Jan 26 11:53:50 GMT+08:00 2015
	*/
	public String add(AreaBo bo);
	/**
	*修改
	*@author zhengYunFei
	*@date Mon Jan 26 11:53:50 GMT+08:00 2015
	*/
	public String update(AreaBo bo);
	/**
	*删除
	*@author zhengYunFei
	*@date Mon Jan 26 11:53:50 GMT+08:00 2015
	*/
	public String delete(String id);
	/**
	*根据Id查询
	*@author zhengYunFei
	*@date Mon Jan 26 11:53:50 GMT+08:00 2015
	*/
	public AreaBo findById(String id);
	/**
	*查询所有信息
	*@author zhengYunFei
	*@date Mon Jan 26 11:53:50 GMT+08:00 2015
	*/
	public List<AreaBo> findAllList(Map<String, Object> queryMap, int curNo, int curSize);
}

