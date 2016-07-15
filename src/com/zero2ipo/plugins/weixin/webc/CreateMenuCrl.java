package com.zero2ipo.plugins.weixin.webc;

import com.zero2ipo.common.web.BaseCtrl;
import com.zero2ipo.framework.util.StringUtil;
import com.zero2ipo.plugins.CodeCommon;
import com.zero2ipo.plugins.code.biz.ICodeManage;
import com.zero2ipo.plugins.code.bo.CodeInfo;
import com.zero2ipo.plugins.code.bo.CodeSort;
import com.zero2ipo.plugins.conf.bizc.IConfManage;
import com.zero2ipo.plugins.conf.bo.ConfValue;
import com.zero2ipo.plugins.weixin.utils.GetAccessTokenUtil;
import com.zero2ipo.plugins.weixin.utils.MenuUtil;
import com.zero2ipo.plugins.weixin.utils.WeixinUtil;
import com.zero2ipo.plugins.wxmenu.bo.Button;
import com.zero2ipo.plugins.wxmenu.bo.CommonButton;
import com.zero2ipo.plugins.wxmenu.bo.ComplexButton;
import com.zero2ipo.plugins.wxmenu.bo.Menu;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求处理的核心类
 *
 */
@Controller
@RequestMapping("wxmenu")
public class CreateMenuCrl extends BaseCtrl{
	  // 菜单创建（POST） 限100（次/天）
        public static String MENU_CREATE = MenuUtil.URL_MENU_CREATE;
	    @Autowired
		private ICodeManage codeManage;
		@Autowired
		private IConfManage confManage;
	    /**暂时这个先这样，后期从数据库里面读取*/

	    @RequestMapping("createMenuAjax.shtml")
		@ResponseBody
	    public Map<String,Object> findUserInfoListAjax(){
	    	
	    	Map<String,Object> result=new HashMap<String, Object>();
	    	CodeInfo bo=new CodeInfo();
	    	List<CodeInfo> list=null;
	    	Map<String, Object> queryMap=new HashMap<String, Object>();
	    	queryMap.put("codeType", CodeCommon.CREATE_MENU);
	    	CodeSort sort= codeManage.findCodeSortByMap(queryMap);
	    	if(!StringUtil.isNullOrEmpty(sort)){
	    		bo.setCodeSortId(sort.getCodeSortId());
	    		bo.setPcode(CodeCommon.ROOT);
	    		bo.setValidFlag("1");
	    		list=codeManage.findCodeInfoList(bo);
	    		Menu menu = new Menu();  
	    		Button [] menuBtns=new Button[list.size()];
	    		for(int i=0;i<list.size();i++){
		    		ComplexButton mainBtn=new ComplexButton();
		    		mainBtn.setName(list.get(i).getCodeName());
	    			String codeId=list.get(i).getId();
	    			CodeInfo info=new CodeInfo();
	    			info.setPcode(codeId);
	    			info.setValidFlag("1");
	    			List<CodeInfo> children=codeManage.findCodeInfoList(info);
	    			if(!StringUtil.isNullOrEmpty(children)&&children.size()>0){
	    				Button [] btns=new Button[children.size()];
	    				for(int j=0;j<children.size();j++){
	    					CodeInfo codeInfo=children.get(j);
	    				    String type=codeInfo.getCodeType();
	    				    String url=codeInfo.getContent2();
	    					CommonButton btn=new CommonButton();
		    				btn.setName(codeInfo.getCodeName());
		    				btn.setType(codeInfo.getCodeType());
		    				if(CodeCommon.MENU_TYPE_CLICK.equals(type)){
		    					btn.setKey(url);
		    				}else{
								btn.setUrl(url);
							}
		    				
		    				btns[j]=btn;
	    				}
	    				 mainBtn.setSub_button(btns);
	    			
	    			}
	    			menuBtns[i]=mainBtn;
	    		}
	    		menu.setButton(menuBtns);
	    		String jsonMenu = JSONObject.fromObject(menu).toString();
				System.out.println("jsonMenu===="+jsonMenu);
	    		CreateMenu(jsonMenu);
	    		result.put("success","true");
	    	}
	    	
	    	 //JSONObject jsono = JSONObject.fromObject(list);
	    	 //JSONObject jsono = JSONObject.fromObject(list);
	    	 
	    	 //JSONArray array=JSONArray.fromObject(list);
	    
	    	return result;
	    }
	    public String CreateMenu(String jsonMenu) {

	        String resultStr = "";
	        // 调用接口获取token

			Map<String,Object> m=new HashMap<String, Object>();
			m.put("confKey", CodeCommon.AppID);
			ConfValue c=confManage.findConfValueByMap(m);
			m.put("confKey", CodeCommon.AppSecret);
			ConfValue c2=confManage.findConfValueByMap(m);
			String appid=c.getConfValue();
			String appSecret=c2.getConfValue();
			String token=GetAccessTokenUtil.getAccess_token(appid,appSecret);

	        if (token != null) {
	            // 调用接口创建菜单
	            int result = createMenu(jsonMenu, token);
	            // 判断菜单创建结果
	            if (0 == result) {
	                resultStr = "菜单创建成功";
	            } else {
	                resultStr = "菜单创建失败，错误码：" + result;
	            }
	            System.out.println(resultStr);
	        }
			System.out.println("resultStr========="+resultStr);
	        return resultStr;
	    }

	    /**
	     * 创建菜单
	     *
	     * @param jsonMenu
	     *            菜单的json格式
	     * @param accessToken
	     *            有效的access_token
	     * @return 0表示成功，其他值表示失败
	     */
	    public static int createMenu(String jsonMenu, String accessToken) {

	        int result = 0;
	        // 拼装创建菜单的url
	        String url = MENU_CREATE+"?access_token="+accessToken;
	        // 调用接口创建菜单
	        JSONObject jsonObject = WeixinUtil.httpRequest(url, "POST", jsonMenu);

	        if (null != jsonObject) {
	            if (0 != jsonObject.getInt("errcode")) {
	                result = jsonObject.getInt("errcode");
	            }
	        }

	        return result;
	    }
	    public static void main(String[] args) {
	    
	    	
	    	
		}
}
