package com.zero2ipo.cfj.project.bizc;
import com.zero2ipo.cfj.project.bo.ServiceProject;

import java.util.List;
import java.util.Map;
   

public interface IServiceProject{
	/**
	*@author zhengYunFei
	*@date Fri Nov 06 22:50:36 GMT+08:00 2015
	*/
	public String add(ServiceProject bo);
	/**
	*@author zhengYunFei
	*@date Fri Nov 06 22:50:36 GMT+08:00 2015
	*/
	public String update(ServiceProject bo);
	/**
	*@author zhengYunFei
	*@date Fri Nov 06 22:50:36 GMT+08:00 2015
	*/
	public String delete(String id);
	/**
	*@author zhengYunFei
	*@date Fri Nov 06 22:50:36 GMT+08:00 2015
	*/
	public ServiceProject findById(String id);
	/**
	*@author zhengYunFei
	*@date Fri Nov 06 22:50:36 GMT+08:00 2015
	*/
	public List<ServiceProject> findAllList(Map<String, Object> queryMap, int i, int max);
	public int findAllListCount(Map<String, Object> map);
}

