package com.zero2ipo.plugins.pic.bizc;

import java.util.List;
import java.util.Map;

import com.zero2ipo.plugins.pic.bo.PictureBo;
import com.zero2ipo.plugins.pic.bo.PictureTypeBo;

public interface IPicType {
   /**
    * 新增
    *@title
    *@date 2014-10-13
    *@author ZhengYunfei
    * @return
    */
	public int add(PictureTypeBo bo);
	/**
	 * 修改
	 *@title
	 *@date 2014-10-13
	 *@author ZhengYunfei
	 * @return
	 */
	public int update(PictureTypeBo bo);
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
	public List<PictureTypeBo> find(Map<String, Object> map);
	/**
	 * 根据id查询
	 *@title
	 *@date 2014-10-14
	 *@author ZhengYunfei
	 * @param id
	 * @return
	 */
	public PictureTypeBo find(String id);

}
