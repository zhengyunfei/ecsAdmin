package com.zero2ipo.plugins.weixin.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Created by Administrator on 2015/7/9.
 */
public class MyJsonUtils {
    /**
     * 获得ACCESS_TOKEN
     *
     * @Title: getAccess_token
     * @Description: 获得ACCESS_TOKEN
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static JsonObject getJsonObject(String url) {

                HttpClient client = new DefaultHttpClient();
                 HttpGet get = new HttpGet(url);
                JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
                JsonObject json=null;
                String result = null;
                try
                {
                       HttpResponse res = client.execute(get);
                      String responseContent = null; // 响应内容
                      HttpEntity entity = res.getEntity();
                      responseContent = EntityUtils.toString(entity, "UTF-8");
                      json = jsonparer.parse(responseContent).getAsJsonObject();

                   }
               catch (Exception e)
             {
                    e.printStackTrace();
             }
            finally
              {
                    // 关闭连接 ,释放资源
                     client.getConnectionManager().shutdown();
              }
        return json;
    }
    public static void main(String args[]){
       // getAccess_token();
    }
}
