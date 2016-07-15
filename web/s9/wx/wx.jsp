<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"
	import="java.io.*,java.util.*,net.sf.json.JSONObject,java.security.*,javax.servlet.http.*,org.apache.http.*,org.apache.http.client.*,org.apache.http.impl.client.*,org.apache.http.client.methods.*,org.apache.http.util.*"%>
<%!

     String appid = "wx012ecb18bf0b6025";
     String secret = "8b14aa1d25394ab4f2ae3dd79e7f50d5";

    //String appid = "wx7393cb9657ac571e";
	//String secret = "3d6cea95445aa56f3f0e77efe17680e9";
	 

	public int calLastedTime(Date startDate) {
		long a = new Date().getTime();
		long b = startDate.getTime();
		int c = (int) ((a - b) / 1000);
		return c;
	}

	public String gettoken(HttpServletRequest request,
			HttpServletResponse response) {
		String url;
		HttpGet httppost;
		HttpClient httpClient;
		String access_token;
		String content1="";
		HttpResponse responses;
		try {
			Map tokenMap = (Map) request.getSession().getServletContext()
					.getAttribute("tokenMap");
			if (tokenMap == null) {
				System.out.println("tokenMap为空");
				url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="
						+ appid + "&secret=" + secret;
				httppost = new HttpGet(url);
				httpClient = new DefaultHttpClient();
				response.setContentType("text/html;charset=UTF-8");
				responses = httpClient.execute(httppost);
				//content1 = EntityUtils.toString(responses.getEntity());
				content1="";	
				access_token = content1.split("access_token\":\"")[1]
						.split("\"")[0];
				Map map = new HashMap();
				map.put("time", new Date());
				map.put("token", access_token);
				request.getSession().getServletContext().setAttribute(
						"tokenMap", map);
			} else if (calLastedTime((Date) tokenMap.get("time")) > 110 * 60) {
				System.out.println("tokenMap过期 ");
				url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=="
						+ appid + "&secret=" + secret;
				httppost = new HttpGet(url);
				httpClient = new DefaultHttpClient();
				response.setContentType("text/html;charset=UTF-8");
				responses = httpClient.execute(httppost);
				//content1 = EntityUtils.toString(responses.getEntity());
				 content1="";
				access_token = content1.split("access_token\":\"")[1]
						.split("\"")[0];
				Map map = new HashMap();
				map.put("time", new Date());
				map.put("token", access_token);
				request.getSession().getServletContext().setAttribute(
						"tokenMap", map);

			} else {
				access_token = (String) tokenMap.get("token");
				System.out.println("有access_token " + access_token + "==时间"
						+ tokenMap.get("time"));

			}
		} catch (Exception e) {
			// TODO: handle exception
			access_token = null;
		}
		return access_token;
	}

	public static String getTicket(HttpServletRequest request,
			HttpServletResponse response, String token) {
		String ticket = null;
		String url;
		HttpGet httppost;
		HttpClient httpClient;
		HttpResponse responses;
		url = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token="
				+ token + "&type=jsapi";
		httppost = new HttpGet(url);
		httpClient = new DefaultHttpClient();
		try {
			responses = httpClient.execute(httppost);
			response.setContentType("text/html;charset=UTF-8");
			//String content1 = EntityUtils.toString(responses.getEntity());
			String content1="";
			JSONObject tk = JSONObject.fromObject(content1);
			Integer code = (Integer) tk.get("errcode");
			if (code == 0) {
				ticket = (String) tk.get("ticket");
			}
			System.out.println("ticket==" + ticket);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ticket;
	}

	public static Map<String, String> sign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";

		//注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket + "&noncestr=" + nonce_str
				+ "&timestamp=" + timestamp + "&url=" + url;
		System.out.println(string1);

		try {
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);
		System.out.println("nonceStr=" + nonce_str);
		System.out.println("timestamp=" + timestamp);
		System.out.println("\n signature =" + signature);
		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash) {
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}%>
<%
	String methods = request.getParameter("methods");
	String path = request.getParameter("path");
	System.out.println("methods=="+methods+"  path="+path);
	if (null != methods && methods.equals("config")) {
		org.json.JSONObject obj = new org.json.JSONObject();
		if (request.getSession().getAttribute("session_ticket") == null) {
			System.out.println("第一次获取token");
			String token = gettoken(request, response);
			System.out.println("1111token===" + token);
			String tiket = getTicket(request, response, token);
			request.getSession().setAttribute("session_ticket", tiket);
		}
		String tiket = (String) request.getSession().getAttribute(
				"session_ticket");
		System.out.println("获取session中的tiket=" + tiket);
		Map<String, String> m = sign(tiket, path);
		obj.put("appId", appid);
		obj.put("timestamp", m.get("timestamp"));
		obj.put("nonceStr", m.get("nonceStr"));
		obj.put("signature", m.get("signature"));
		System.out.println(obj.toString());
		response.getWriter().print(obj);
	}
%>
