package com.codestyle.bizmodel.biz.imp;//package com.codestyle.bizmodel.biz.imp;
//
//import java.util.HashMap;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//
//import com.codestyle.bizmodel.biz.Isample;
//import com.codestyle.bizmodel.bo.Sample;
//
///**
// * @title :业务bizc层代码示例
// * @description :描述了bizc层的代码编写规范
// * @author: zhengYunFei
// * @date: 2012-7-16
// */
//@Service("sample")
//public class SampleImp implements Isample{
//    
//    @Autowired @Qualifier("baseDao")// 自动注入baseDao实例
//	private IBaseDao baseDao;// 注释
//	
//	/**
//	 * @title :添加示例
//	 * @description : 该操作先去缓存查询信息，如果没有再去数据库查询，然后把信息写入
//	 * @param userId:用户标示
//	 * @reteurn: Sample
//	 */
//	public Sample findSample(String userId,String sid){
//	    Sample sample = null; // 声明返回对象
//		String key = "theKey";// 缓存key 项目开发中该key使用时需申请，并定义在全局CacheConstant常量类中
//		boolean flag = true;  // 定义缓存连接标志 
//		
//		try {
//			if(CacheUtil.keyExists(key)){// 先查询缓存，缓存会抛出异常
////			    sample =(Sample)CacheUtil.findObject(key); 
//			}
//		}catch(NullPointerException n){
//		    flag = false;// 缓存连接异常，记录日志，并标记，后续操作不再使用缓存
//		    Log.e("findSample:查询缓存异常", n);
//		}
//		if(sample == null){// 缓存中没有信息 就去数据库查，并把信息添加到缓存中
//		    HashMap<String,String> hm = new HashMap<String,String>();
//		    try {
//		        baseDao.setDbType(FwConstant.DBTYPE_XZ);
//		        sample =(Sample)baseDao.findForObject("statment",hm);
//            } catch (Exception e) {
//                Log.e("findSample:查询sample异常", e);
//            }
//		    if(flag){// 判断缓存是否已被判断连接异常 
////		        CacheUtil.addObjectToday(key, sample);// 将查询数据添加到缓存中 方便下次查询
//		    }
//		}
//		return sample;
//	}
// 
//	 
//}
