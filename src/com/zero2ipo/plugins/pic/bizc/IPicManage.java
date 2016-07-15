package com.zero2ipo.plugins.pic.bizc;

import java.util.List;

import com.zero2ipo.plugins.pic.bo.PictureBo;

public interface IPicManage {
   /**
    * 新增
    *@title
    *@date 2014-10-13
    *@author ZhengYunfei
    * @return
    */
	public int add(PictureBo bo);
	/**
	 * 修改
	 *@title
	 *@date 2014-10-13
	 *@author ZhengYunfei
	 * @return
	 */
	public int update(PictureBo bo);
	/**
	 * 删除
	 *@title
	 *@date 2014-10-13
	 *@author ZhengYunfei
	 * @return
	 */
	public int delete(String id);
	/**
	 * 查询
	 *@title
	 *@date 2014-10-13
	 *@author ZhengYunfei
	 * @return
	 */
	public List<PictureBo> find(String name);
	/**
	 * 根据pid查询轮播图
	 *@title
	 *@date 2014-10-14
	 *@author ZhengYunfei
	 * @param pid
	 * @return
	 */
	public List<PictureBo> findPictureById(String pid, String id);

}
