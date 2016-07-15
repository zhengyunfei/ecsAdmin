package com.zero2ipo.plugins.weixin.utils;

 import org.apache.http.HttpEntity;
 import org.apache.http.HttpResponse;
 import org.apache.http.HttpStatus;
 import org.apache.http.client.HttpClient;
 import org.apache.http.client.methods.HttpGet;
 import org.apache.http.impl.client.DefaultHttpClient;
 import org.apache.http.util.EntityUtils;

 import com.google.gson.JsonObject;
 import com.google.gson.JsonParser;

/**
 * Created by Administrator on 2015/7/9.
 */
public class GetAccessTokenUtil {
    /**
     * 获得ACCESS_TOKEN
     *
     * @Title: getAccess_token
     * @Description: 获得ACCESS_TOKEN
     * @param @return 设定文件
     * @return String 返回类型
     * @throws
     */
    public static String getAccess_token(String appid,String secret) {
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+ appid+ "&secret=" + secret;

                // String turl = String.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s", url,appid, secret);
                HttpClient client = new DefaultHttpClient();
                 HttpGet get = new HttpGet(url);
                JsonParser jsonparer = new JsonParser();// 初始化解析json格式的对象
                String result = null;
                try
                {
                       HttpResponse res = client.execute(get);
                      String responseContent = null; // 响应内容
                        HttpEntity entity = res.getEntity();
                      responseContent = EntityUtils.toString(entity, "UTF-8");
                    JsonObject json = jsonparer.parse(responseContent).getAsJsonObject();
                    // 将json字符串转换为json对象
                     if (res.getStatusLine().getStatusCode() == HttpStatus.SC_OK)
                      {
                            if (json.get("errcode") != null)
                               {// 错误时微信会返回错误码等信息，{"errcode":40013,"errmsg":"invalid appid"}
                               }
                            else
                              {// 正常情况下{"access_token":"ACCESS_TOKEN","expires_in":7200}
                                 result = json.get("access_token").getAsString();
                                }
                            }
                   }
               catch (Exception e)
             {
                    e.printStackTrace();
             }
            finally
              {
                    // 关闭连接 ,释放资源
                     client.getConnectionManager().shutdown();
                       return result;
                 }
    }
    public static void main(String args[]){
       // getAccess_token();
    }
}
