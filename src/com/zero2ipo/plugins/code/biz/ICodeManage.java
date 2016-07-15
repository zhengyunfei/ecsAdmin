/**
 * @(#)ICodeManage.java	07/03/2013
 * 
 * Copyright (c) 2013 S9,Inc.All rights reserved.
 * Created by 2013-07-03 
 */
package com.zero2ipo.plugins.code.biz;

import java.util.List;
import java.util.Map;

import com.zero2ipo.plugins.code.bo.CodeInfo;
import com.zero2ipo.plugins.code.bo.CodeSort;
import org.jeewx.api.wxuser.user.model.Wxuser;

/**
 * @title: 标准代码管理维接口定义
 * @description: 针对标准代码分类管理、标准代码项的一个维护管理接口的定义
 * @author： zhengYunFei
 * @date： 2013-07-03 10:10
 */
public interface ICodeManage {
	/**
     * @title: 获取代码分类信息树
     * @description: 获取代码分类信息树 
     * @param bo {codeSortType:(代码分类信息，非必须参数),codeSortName:(代码分类名称，非必须参数)}
     * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 * @return list 代码分类列表
     */
	public List<CodeSort> findCodeSortTree(CodeSort bo);
	/**
	 * @title 根据codeSortId获取代码分类信息
	 * @description:根据codeSortId获取代码分类信息
	 * @param codeSortId 
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 * @return bo 代码分类信息实体
	 */
	public CodeSort findCodeSort(String codeSortId);
	/**
	 * @title：查询代码分类信息
	 * @description:查询代码分类信息
	 * @param bo {codeSortId：} 
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 * @return list 代码分类信息
	 */
	public List<CodeSort> findCodeSortList(CodeSort bo);
	/**
	 * @title： 保存代码分类信息实体
	 * @description:保存代码分类信息实体
	 * @param bo 代码分类信息实体
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 */
	public String  addCodeSort(CodeSort bo);
	/**
	 * @title 修改代码分类信息实体
	 * @description:修改代码分类信息实体
	 * @param bo 代码分类信息实体
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 */
	public void updCodeSort(CodeSort bo);
	/**
	 * @title 根据代码分类信息ID删除记录
	             	同时删除子节点（分类信息对应的代码信息）
	 * @description:  删除代码分类信息记录同时删除子节点       	
	 * @param codeSortId
	 */
	public void delCodeSort(String codeSortId);
	/**
	 * @title 根据codeId获取代码信息
	 * @description:根据codeId获取代码项信息
	 * @param codeId
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 * @return bo 代码项信息实体
	 */
	public CodeInfo findCodeInfo(Map<String, Object> map);
	/**
	 * @title 查询代码项信息列表
	 * @description:查询代码项信息列表
	 * @param bo {codeSortId} 
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 * @return list 代码项信息列表
	 */
	public List<CodeInfo> findCodeInfoList(CodeInfo bo, int curNo, int curSize);
	/**
	 * @title 查询代码项信息列表
	 * @description:查询代码项信息列表
	 * @param bo {codeSortId} 
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 * @return list 代码项信息列表
	 */
	public List<CodeInfo> findCodeInfoList(CodeInfo bo);
	/**
	 * @title 查询代码信息记录总数
	 * @description:查询代码信息记录总数
	 * @param bo {codeSortId } 
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 * @return count  代码项记录数量
	 */
	public int findCodeInfoCount(CodeInfo bo);
	/**
	 * @title： 保存代码项信息实体到数据库
	 * @description:保存代码项信息实体到数据库
	 * @param bo 代码项信息实体
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 */
	public void addCodeInfo(CodeInfo bo);
	/**
	 * @title 修改代码项信息实体到数据库
	 * @description:修改代码项信息实体到数据库
	 * @param bo 代码项信息实体
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 */
	public void updCodeInfo(CodeInfo bo);
	/**
	 * @title 根据代码项信息ID删除记录
	 * @description:根据代码项信息ID删除记录
	 * @param codeIds 代码信息ID字符串{codeIds = "codeID1,codeID2..."} 
	 * @author: zhengYunFei
     * @date: 2013-07-03 10:20
	 */
	public void delCodeInfo(String codeIds);
	/**
     * @title: 根据代码id或者代码名称查询字典
     * @description:根据代码id或者代码名称查询字典
     * @param : codeId  代码标志
     * @param :codeName 代码名称
     * @author: zhengYunFei
     * @date: 2014-09-15 10:20
     * @return: map
     */
	public Map<String,Object> getCodeValue(Map<String, Object> map);
	 
	/**
     * @title: 查询分类名字是否已经存在
     * @description: 查询分类名字是否已经存在
     * @param : boolean
     * @author: zhengYunFei
     * @date: 2014-09-16 10:20
     */
    public int findCodeSortNameIsExist(String codeSortName);
    /**
     *自动生成代码值
     *@title
     *@date 2010-9-16
     *@author ZhengYunfei
     * @return
     */
	public String generateCodeValue(String codeSortId);
	/**
	 * 根据codeValue查询CodeName
	 *@title
	 *@date 2014-9-24
	 *@author ZhengYunfei
	 * @param codeValue
	 * @return
	 */
	public String findCodeNameByValue(String codeValue);

	public CodeSort findCodeSortByMap(Map<String, Object> queryMap);

	public int findWxUserCount(Map<String, Object> map);

	public List<Wxuser> findWxUserList(Map<String, Object> map, int i, int max);

	public void DelWxUser(String userId);

	public void addWxUser(Wxuser wxuser);

	public List<Wxuser> findWxUserList(Map<String, Object> map);
}
