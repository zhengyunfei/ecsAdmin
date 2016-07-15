package com.zero2ipo.plugins.weixin.utils;


import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jeewx.api.core.exception.WexinReqException;
import org.jeewx.api.core.util.HttpRequestProxy;
import org.jeewx.api.wxuser.user.JwUserAPI;
import org.jeewx.api.wxuser.user.model.Wxuser;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * Created by Administrator on 2015/7/9.
 */
public class GetAllWeixinUserUtil {
    /**获取所有关注的用户列表*/
    private final static String getUserUrl="https://api.weixin.qq.com/cgi-bin/user/get?access_token=";
    private final static String GetUserInfo="https://api.weixin.qq.com/cgi-bin/user/info?access_token=";
    private final static String APPID="wxb8819f0a2f3f2e87";
    private final static String APPID_SECRET="9c106f640c8f865f7d6092a095399442";
    /**
     * 获取所有的关注用户
     * 开发者ID

     *AppID(应用ID)wx9a384dfa46797ce5

      *AppSecret(应用密钥)b98a7c7cdce9f9d286a7d9552fd387f3 
     * @return
     */
    public static List<String> getAllWeiXinUser(String appid,String secret) {
       // String accessToken = GetAccessTokenUtil.getAccess_token(Config.APPID,Config.SECRET);
        String accessToken = GetAccessTokenUtil.getAccess_token(appid,secret);
        System.out.println("acc===="+accessToken);
        List<String> openIds = new ArrayList<String>();
        // 上传文件请求路径
        String action = getUserUrl+accessToken;
        try {
         
        	String result=HttpRequestProxy.doGet(action, "UTF-8");
            JSONObject jsonObj = JSONObject.fromObject(result);
            JSONObject json1 = JSONObject.fromObject(jsonObj.get("data").toString());
            JSONArray json2 = JSONArray.fromObject(json1.get("openid").toString());
            System.out.println("关注个数======"+json2.size());
            for (int i = 0; i < json2.size(); i++) {
            	System.out.println(json2.getString(i));
                openIds.add(i, json2.getString(i));
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return openIds;
    }
    public static  void main(String args[]) throws WexinReqException{
    	 String accessToken = GetAccessTokenUtil.getAccess_token(APPID,APPID_SECRET);
    	System.out.println(accessToken);
        getAllWeiXinUser(APPID,APPID_SECRET);
    	//List<WeiXinUserBo> list=
    	//for(int i=0;i<list.size();i++){
    		//System.out.println("id="+i+1+"\t昵称="+list.get(i).getNickname()+"openid="+list.get(i).getOpenid()+"\t性别="+list.get(i).getSex()+"\t国家="+list.get(i).getCountry()+"\t省份="+list.get(i).getProvince()+"\t城市="+list.get(i).getCity()+"\t关注时间="+list.get(i).getSubscribe_time());
    	//}
    }
    public static List<WeiXinUserBo> getUserInfo(String appid,String appsecret){
    	   List<WeiXinUserBo> list=new ArrayList<WeiXinUserBo>();
    	   String accessToken = GetAccessTokenUtil.getAccess_token(appid,appsecret);
          // System.out.println("acc===="+accessToken);
           // 上传文件请求路径
           //String action = getUserUrl+accessToken;
    	List<String> openIds=getAllWeiXinUser(appid,appsecret);
    	
    	for(int i=0;i<openIds.size();i++){
    		   String openid=openIds.get(i);
    		   String action=GetUserInfo+accessToken+"&openid="+openid;
               try {
            	   String result=HttpRequestProxy.doGet(action, "UTF-8");
                 //  System.out.println("Result====="+result);
                   JSONObject jsonObj =JSONObject.fromObject(result);
                  // System.out.println("昵称" + jsonObj.get("nickname")+"\topenid="+jsonObj.get("openid")+"\t用户头像"+jsonObj.get("headimgurl"));
                   WeiXinUserBo bo=new WeiXinUserBo();
                   String headimgurl="";
                   if(jsonObj.has("headimgurl")){
                	   headimgurl= jsonObj.get("headimgurl")+"";
                   }
                   String  nickname="";
                   if(jsonObj.has("nickname")){
                	   nickname=jsonObj.get("nickname").toString();
                   }
                   
                   String openId="";
                   if(jsonObj.has("openid")){
                	   openId=jsonObj.get("openid").toString();
                   }
                   String sex="";
                   if(jsonObj.has("sex")){
                	   sex=jsonObj.get("sex").toString();
                   }
                   String country="";
                   if(jsonObj.has("country")){
                	   country=jsonObj.get("country").toString();
                   }
                   String province="";
                   if(jsonObj.has("province")){
                	   province=jsonObj.get("province").toString();
                   }
                   String city="";
                   if(jsonObj.has("city")){
                	   city=jsonObj.get("city").toString();
                   }
                   long subscribe_time = 0;
                   if(jsonObj.has("subscribe_time")){
                	   subscribe_time=jsonObj.getLong("subscribe_time");
                   }
                   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                  String date = sdf.format(new Date(subscribe_time*1000));
                 bo.setHeadimgurl(headimgurl);
                  bo.setNickname(nickname);
                  bo.setOpenid(openId);
                  if("1".equals(sex)){
                	  bo.setSex("男");
                  }else{
                	  bo.setSex("女");
                  }
                 
                  bo.setCountry(country);
                  bo.setProvince(province);
                  bo.setCity(city);
                  bo.setSubscribe_time(date);
                  list.add(bo);
               } catch (Exception e) {
                  e.printStackTrace();
               }
    	}
		return list;
    }
}
