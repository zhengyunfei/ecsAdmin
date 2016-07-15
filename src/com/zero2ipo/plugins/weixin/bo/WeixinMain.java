package com.zero2ipo.plugins.weixin.bo;

import com.alibaba.fastjson.JSON;
import com.zero2ipo.plugins.weixin.utils.MyJsonUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.*;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jeewx.api.wxuser.user.model.Wxuser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 模拟登陆 获取数据 发送消息
 * 
 * @author wang
 * @version V1.0
 */
public class WeixinMain {

	// 模拟登陆的url
	public static String LOGIN_URL = "http://mp.weixin.qq.com/cgi-bin/login?lang=zh_CN";
	// 模拟登陆后发送信息的url
	public static String SEND_MSG = "http://mp.weixin.qq.com/cgi-bin/singlesend?t=ajax-response&lang=zh_CN";
	public final static String HOST = "http://mp.weixin.qq.com";

	public final static String FANS_URL = "http://mp.weixin.qq.com/cgi-bin/contactmanagepage?t=wxm-friend&lang=zh_CN&pagesize=10&pageidx=0&type=0&groupid=0";
	public final static String LOGOUT_URL = "http://mp.weixin.qq.com/cgi-bin/logout?t=wxm-logout&lang=zh_CN";
	public final static String DOWNLOAD_URL = "http://mp.weixin.qq.com/cgi-bin/downloadfile?";
	public final static String VERIFY_CODE = "http://mp.weixin.qq.com/cgi-bin/verifycode?";
	public final static String POST_MSG = "https://mp.weixin.qq.com/cgi-bin/masssend?t=ajax-response";
	public final static String VIEW_HEAD_IMG = "http://mp.weixin.qq.com/cgi-bin/viewheadimg";
	public final static String GET_IMG_DATA = "http://mp.weixin.qq.com/cgi-bin/getimgdata";
	public final static String GET_REGIONS = "http://mp.weixin.qq.com/cgi-bin/getregions";
	public final static String GET_MESSAGE = "http://mp.weixin.qq.com/cgi-bin/getmessage";
	public final static String OPER_ADVANCED_FUNC = "http://mp.weixin.qq.com/cgi-bin/operadvancedfunc";
	public final static String MASSSEND_PAGE = "http://mp.weixin.qq.com/cgi-bin/masssendpage";
	public final static String FILE_MANAGE_PAGE = "http://mp.weixin.qq.com/cgi-bin/filemanagepage";
	public final static String OPERATE_APPMSG = "https://mp.weixin.qq.com/cgi-bin/operate_appmsg";
	public final static String FMS_TRANSPORT = "http://mp.weixin.qq.com/cgi-bin/fmstransport";
	public final static String CONTACT_MANAGE_PAGE = "http://mp.weixin.qq.com/cgi-bin/contactmanagepage";
	public final static String OPER_SELF_MENU = "http://mp.weixin.qq.com/cgi-bin/operselfmenu";
	public final static String REPLY_RULE_PAGE = "http://mp.weixin.qq.com/cgi-bin/replyrulepage";
	public final static String SINGLE_MSG_PAGE = "http://mp.weixin.qq.com/cgi-bin/singlemsgpage";
	public final static String USER_INFO_PAGE = "http://mp.weixin.qq.com/cgi-bin/userinfopage";
	public final static String DEV_APPLY = "http://mp.weixin.qq.com/cgi-bin/devapply";
	public final static String UPLOAD_MATERIAL = "https://mp.weixin.qq.com/cgi-bin/uploadmaterial?cgi=uploadmaterial&type=2&token=416919388&t=iframe-uploadfile&lang=zh_CN&formId=1";

	public final static String USER_AGENT_H = "User-Agent";
	public final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.172 Safari/537.22";
	public final static String REFERER_H = "Referer";
	public final static String INDEX_URL = "http://mp.weixin.qq.com/cgi-bin/indexpage?t=wxm-index&lang=zh_CN";
	public final static String UTF_8 = "UTF-8";

	private final static HttpClient client = new HttpClient();

	public static Cookie[] cookies;
	public static String cookiestr;
	public static String TOKEN;
	public static int loginErrCode;
	public static String loginErrMsg;
	public static int msgSendCode;
	public static String msgSendMsg;
	public static HashMap<String, String> cook;

	public static int user_count;

	/**
	 * 模拟登录信息,记录cookie token等信息
	 * 
	 * @param loginUser
	 * @param loginPwd
	 * @return
	 */
	public static boolean weixin_login(String loginUser, String loginPwd) {
		try {
			PostMethod post = new PostMethod(LOGIN_URL);
			post.setRequestHeader(USER_AGENT_H, USER_AGENT);
			post.setRequestHeader(REFERER_H, INDEX_URL);
			NameValuePair[] params = new NameValuePair[] {
					new NameValuePair("username", loginUser),
					new NameValuePair("pwd", DigestUtils.md5Hex(loginPwd.getBytes())), new NameValuePair("f", "json"),
					new NameValuePair("imagecode", "") };
			post.setQueryString(params);
			int status = client.executeMethod(post);
			System.out.println("会话的返回值：" + status);
			if (status == HttpStatus.SC_OK) {
				String ret = post.getResponseBodyAsString();
				System.out.println(ret);
				LoginJson retcode = JSON.parseObject(ret, LoginJson.class);
				if (retcode.getRet() == 302 && retcode.getErrCode() == 0) {
					cookies = client.getState().getCookies();
					StringBuffer cookie = new StringBuffer();
					cook = new HashMap<String, String>();
					for (Cookie c : client.getState().getCookies()) {
						cook.put(c.getName(), c.getValue());
						cookie.append(c.getName()).append("=").append(c.getValue()).append(";");
					}
					cookiestr = cookie.toString();
					String errMsg = retcode.getErrMsg();
					TOKEN = errMsg.substring(errMsg.lastIndexOf("=") + 1, errMsg.length());
					return true;
				}
				int errCode = retcode.getErrCode();
				switch (errCode) {
				case -1:
					loginErrMsg = "系统错误";
					System.out.println("系统错误");
					return false;
				case -2:
					loginErrMsg = "帐号或密码错误";
					System.out.println("帐号或密码错误");
					return false;
				case -3:
					loginErrMsg = "密码错误";
					System.out.println("密码错误");
					return false;
				case -4:
					loginErrMsg = "不存在该帐户";
					System.out.println("不存在该帐户");
					return false;
				case -5:
					loginErrMsg = "访问受限";
					System.out.println("访问受限");
					return false;
				case -6:
					loginErrMsg = "需要输入验证码";
					System.out.println("需要输入验证码");
					return false;
				case -7:
					loginErrMsg = "此帐号已绑定私人微信号，不可用于公众平台登录";
					System.out.println("此帐号已绑定私人微信号，不可用于公众平台登录");
					return false;
				case -8:
					loginErrMsg = "邮箱已存在";
					System.out.println("邮箱已存在");
					return false;
				case -200:
					loginErrMsg = "因频繁提交虚假资料，该帐号被拒绝登录";
					System.out.println("因频繁提交虚假资料，该帐号被拒绝登录");
					return false;
				case -94:
					loginErrMsg = "使用邮箱登陆";
					System.out.println("使用邮箱登陆");
					return false;
				case 0:
					loginErrMsg = "成功登陆";
					System.out.println("成功登陆");
					return true;
				default:
					loginErrMsg = "未知的返回......";
					System.out.println("未知的返回......");
					return false;
				}
			}
		} catch (Exception e) {
			String info = "【登录失败】【发生异常：" + e.getMessage() + "】";
			System.err.println(info);
			return false;
		}
		return false;
	}

	/**
	 * 访问用户管理 获取关注列表
	 * @param pagesize 单页数量(测试可以设置为9999，一次取全部用户)
	 * @param pageidx 当前页数(0开始)
	 * @return
	 * @throws org.apache.commons.httpclient.HttpException
	 * @throws java.io.IOException
	 */
	public static List<FansJson> getfans(int pagesize, int pageidx,String token) throws IOException {
		GetMethod get = new GetMethod("http://mp.weixin.qq.com/cgi-bin/contactmanage?t=user/index&pagesize=" + pagesize + "&pageidx=" + pageidx + "&type=0&groupid=0&token=" + token + "&lang=zh_CN");
		get.setRequestHeader(USER_AGENT_H, USER_AGENT);
		get.setRequestHeader(REFERER_H, INDEX_URL);
		get.setRequestHeader("Cookie", cookiestr);
		int status = client.executeMethod(get);
		if (status == HttpStatus.SC_OK) {
			String sr = get.getResponseBodyAsString();
			return parseFansCount(sr);

		}
		return null;
	}

	/**
	 * 访问用户管理 获取用户详细信息
	 * @param fakeid 用户fakeid，对应getfans()中的getId()
	 * @return
	 * @throws org.apache.commons.httpclient.HttpException
	 * @throws java.io.IOException
	 */
	public static InfoJson getfansinfo(String fakeid,String token) throws IOException {
		PostMethod post = new PostMethod("http://mp.weixin.qq.com/cgi-bin/getcontactinfo?t=ajax-getcontactinfo&token=" + token + "&lang=zh_CN");
		post.setRequestHeader(USER_AGENT_H, USER_AGENT);
		post.setRequestHeader(REFERER_H, INDEX_URL);
		post.setRequestHeader("Cookie", cookiestr);
		post.addParameter("fakeid", fakeid);
		int status = client.executeMethod(post);
		if (status == HttpStatus.SC_OK) {
			String sr = post.getResponseBodyAsString();
			return JSON.parseObject(sr, InfoJson.class);
		}
		return null;
	}
	public static Wxuser getWechatUserInfo(String openId,String taken){
		Wxuser wxuser=new Wxuser();
		wxuser.setOpenid(openId);
		String url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+taken+"&openid="+openId;
		net.sf.json.JSONObject result=null;
		HttpClient client = new HttpClient();
		GetMethod get = new GetMethod(url);
		try {
			client.executeMethod(get);
			String returnJson=get.getResponseBodyAsString();
			returnJson = new String(returnJson.getBytes("ISO8859_1"),"UTF-8");
			result = net.sf.json.JSONObject.fromObject(returnJson);

			if(result.containsKey("nickname")){
				String nickname=result.get("nickname")+"";
				wxuser.setNickname(nickname);
			}
			String headimgurl="";
			if(result.containsKey("headimgurl")){
				headimgurl= result.get("headimgurl")+"";
				wxuser.setHeadimgurl(headimgurl);
			}

			String sex="";
			if(result.containsKey("sex")){
				sex=result.get("sex")+"";
				if("1".equals(sex)){
					wxuser.setSex("男");
				}else if("2".equals(sex)){
					wxuser.setSex("女");
				}else{
					wxuser.setSex(sex);
				}
			}
			String country="";
			if(result.containsKey("country")){
				country=result.get("country")+"";
				wxuser.setCountry(country);
			}
			String province="";
			if(result.containsKey("province")){
				province=result.get("province")+"";
				wxuser.setProvince(province);
			}
			String city="";
			if(result.containsKey("city")){
				city=result.get("city")+"";
				wxuser.setCity(city);
			}
			long subscribe_time = 0;
			if(result.containsKey("subscribe_time")){
				subscribe_time=result.getLong("subscribe_time");
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
				String date = sdf.format(new Date(subscribe_time*1000));
				wxuser.setSubscribe_time(date);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return wxuser;
	}
	public static List<String> getAllWeiXinUser(String accessToken) {
		List<String> openIds = new ArrayList<String>();
		// 上传文件请求路径
		String action = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="
				+ accessToken;
		try {
			/*URL urlGet = new URL(action);
			HttpURLConnection http = (HttpURLConnection) urlGet
					.openConnection();
			http.setRequestMethod("GET"); // 必须是get方式请求
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			String result = new String(jsonBytes, "UTF-8");*/
			com.google.gson.JsonObject jsonObj = MyJsonUtils.getJsonObject(action);
			//System.out.println("users" + jsonObj.get("data"));
			JSONObject json1 = new JSONObject(jsonObj.get("data").toString());
			//System.out.println(json1.toString());
			JSONArray json2 = new JSONArray(json1.get("openid").toString());
			for (int i = 0; i < json2.length(); i++) {
				openIds.add(i, json2.getString(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return openIds;
	}
	/**
	 * 解析返回的页面数据 获取组的数据和当前组的10个人员信息
	 * @param text
	 * @return
	 */
	private static List<FansJson> parseFansCount(String text) {
		try {
			StringBuffer json = new StringBuffer();
			// 先取出最外出的json对象的String
			final String start = "cgiData=";
			for (int i = text.indexOf(start) + start.length(), len = text.length(); i < len; i++) {
				char ci = text.charAt(i);
				if (ci == ';') {
					break;
				}
				json.append(text.charAt(i));
			}
			System.out.println("json.toString()=="+json.toString());
			//总的数据结构JSON string数据
			String temp = json.toString();
			// 在取出group的json
			StringBuffer grouptempjson = new StringBuffer();
			final String grouptempstart = "\"groups\":";
			for (int i = temp.indexOf(grouptempstart) + grouptempstart.length(), len = temp.length(); i < len; i++) {
				char ci = temp.charAt(i);
				if (ci == ']') {
					grouptempjson.append(temp.charAt(i));
					break;
				}
				grouptempjson.append(temp.charAt(i));
			}
			System.out.println(grouptempjson.toString());
			List<FansCount> fans = JSON.parseArray(grouptempjson.toString(), FansCount.class);
			if (null != fans && !fans.isEmpty()) {
				int count = 0;
				for (FansCount fan : fans) {
					System.out.println(fan.getName()+"======"+fan.getCnt());
					count = count + fan.getCnt();
				}
				user_count = count;
				System.out.println("目前粉丝总数为：" + count);
			}
			// 在取出用户的JSON数据
			StringBuffer friendtempjson = new StringBuffer();
			final String friendtempstart = "\"contacts\":";
			for (int i = temp.indexOf(friendtempstart) + friendtempstart.length(), len = temp.length(); i < len; i++) {
				char ci = temp.charAt(i);
				if (ci == ']') {
					friendtempjson.append(temp.charAt(i));
					break;
				}
				friendtempjson.append(temp.charAt(i));
			}
			System.out.println(friendtempjson.toString());
			List<FansJson> friends = JSON.parseArray(friendtempjson.toString(),
					FansJson.class);
			if (null != friends && !friends.isEmpty()) {
				for (FansJson fan : friends) {
					System.out.println(fan.getId()+"======"+fan.getNick_name()+"===="+fan.getRemark_name()+"===="+fan.getGroup_id());
				}
			}
			return friends;
		} catch (Exception e) {
			String info = "【解析粉丝数失败】 " + "\t\n【文本：】\t\n" + "\t\n" + "【发生异常：" + e.getMessage() + "】";
			System.err.println(info);
			return null;
		}
	}

	/**
	 * 模拟登陆发送消息1
	 * @param cookie 登陆获取的COOK
	 * @param content 发送的信息内容
	 * @param fakeId 粉丝的ID
	 * @throws java.io.IOException
	 */
	public static void sendMsg(Map<String, String> cookie, String content, String fakeId) throws IOException {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("tofakeid", fakeId);
		map.put("content", content);
		map.put("error", "false");
		map.put("token", TOKEN);
		map.put("type", "1");
		map.put("ajax", "1");

		String referrerUrl = "http://mp.weixin.qq.com/cgi-bin/singlemsgpage?msgid=&source=&count=20&t=wxm-singlechat&fromfakeid==" + fakeId + "&token=" + TOKEN + "&lang=zh_CN";

		Document document = Jsoup.connect(SEND_MSG).referrer(referrerUrl).data(map).cookies(cookie).post();
		Element body = document.body();
		System.out.println(body.text());

	}

	/**
	 * 模拟发送消息2
	 * @param str 文本信息内容
	 * @param fakeId 用户粉丝ID
	 * @return
	 */
	public static boolean sendMessageById(String str, String fakeId) { try {
			String url = "http://mp.weixin.qq.com/cgi-bin/singlesend?t=ajax-response&lang=zh_CN&ajax=1&content="
					+ URLEncoder.encode(str, "UTF-8")
					+ "&error=false&tofakeid="
					+ fakeId
					+ "&type=1&token="
					+ TOKEN;
			HttpClient client = new HttpClient();
			PostMethod getMethod = new PostMethod(url);
			getMethod.setRequestHeader("Cookie", cookiestr);
			getMethod.setRequestHeader("Referer","http://mp.weixin.qq.com/cgi-bin/singlemsgpage?fromfakeid=" + fakeId + "&msgid=&source=&count=20&t=wxm-singlechat&lang=zh_CN");
			client.executeMethod(getMethod);
			String returnStr = getMethod.getResponseBodyAsString();
			System.out.println(returnStr);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) throws Exception {

		if (weixin_login("2246741661@qq.com", "jhhjec123")) {
			List<FansJson> flist = getfans(9999, 0,"");
			if (flist != null) {
				String user_id = "", user_name = "";
				for (int i = 0; i < flist.size(); i++) {
					user_id = flist.get(i).getId();
					user_name = flist.get(i).getNick_name();
					System.out.print(user_id);
					System.out.println("	" + user_name);
				}
			}

//			InfoJson userinfo = getfansinfo("131689000");
//			if (userinfo != null) {
//				System.out.println("FakeId=" + userinfo.getFakeId());
//				System.out.println("NickName=" + userinfo.getNickName());
//				System.out.println("ReMarkName=" + userinfo.getReMarkName());
//				System.out.println("Username=" + userinfo.getUsername());
//				System.out.println("Signature=" + userinfo.getSignature());
//				System.out.println("Country=" + userinfo.getCountry());
//				System.out.println("Province=" + userinfo.getProvince());
//				System.out.println("City=" + userinfo.getCity());
//				System.out.println("Sex=" + userinfo.getSex());
//				System.out.println("GroupID=" + userinfo.getGroupID());
//			}
		}

		sendMessageById("亲、你好、请问有什么可以帮到你的吗？12312", "741388300");
		sendMsg(cook, "亲、你好、请问有什么可以帮到你的吗？12312", "741388300");

	}
}
