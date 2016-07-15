package com.codestyle.bizmodel.biz;

import com.codestyle.bizmodel.bo.Sample;


/**
 * @title :接口编写规范示例
 * @description :这是一个描述bizc层的代码编写规范的示例接口
 * @author: zhengYunFei
 * @date: 2012-7-16
 */
public interface Isample {
    
    /**
     * @title :添加示例
     * @description : 该操作先去缓存查询信息，如果没有再去数据库查询，然后把信息写入
     * @param userId:用户标示 
     * @param sid:会话标示 
     */
    public Sample findSample(String userId, String sid);
}
