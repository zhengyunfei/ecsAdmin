package com.zero2ipo.cfj.upload.webc;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zero2ipo.common.GlobalConstant;

/**
 * 浏览服务器的图片
 * @version: v1.0
 * @project_name:NormandyPosition
 * @description:
 * <p>
 *
 * </p>
 */
@Controller
@RequestMapping("/browerServer.shtml")
public class FileBrowerController {
	protected final Logger logger = Logger
			.getLogger(FileBrowerController.class);
	/** ~~~ 上传文件的保存路径 */
	private static final String FILE_UPLOAD_DIR = "upload";
	/** ~~~ 上传文件的保存的下一级路径，标示存储类型 */
	private static final String FILE_UPLOAD_SUB_IMG_DIR = "img";
	/** ~~~ 为了能让CKEDITOR加载到上传的图片，此处将位置限制在了freemarker下*/
	 /** ~~~ 系统默认建立和使用的以时间字符串作为文件名称的时间格式*/
    private static final String DEFAULT_SUB_FOLDER_FORMAT_AUTO = "yyyyMMdd";
	private static final String FOR_FREEMARKER_LOAD_DIR = "freemarker";
    /** ~~~ 这里扩充一下格式，防止手动建立的不统一*/
    private static final String DEFAULT_SUB_FOLDER_FORMAT_NO_AUTO = "yyyy-MM-dd";
    /** ~~~ 每个上传子目录保存的文件的最大数目 */
    private static final int MAX_NUM_PER_UPLOAD_SUB_DIR = 500;
    /** ~~~ 上传文件的最大文件大小 */
    private static final long MAX_FILE_SIZE = 1024 * 1024 * 2;

	@RequestMapping(method = RequestMethod.GET)
	public void processBrower(ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) {
		processBrowerPost(modelMap, request, response);
		return;
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(method = RequestMethod.POST)
	public void processBrowerPost(ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {

		String typeStr = request.getParameter("type");
		String floderName = request.getParameter("fo");
		floderName=floderName.replace("/c", "");
		if (logger.isDebugEnabled()) {
			logger.debug("浏览文件，文件格式:" + typeStr);
		}

		// 定位到目标文件夹 ： 上传目录
		String realPath = "";
		if(StringUtils.isNotBlank(floderName)){
			floderName = URLDecoder.decode(floderName);
			// 如果请求中存在文件夹名称，则定位到文件夹中
			realPath = request.getSession().getServletContext().getRealPath(floderName);
			if(logger.isInfoEnabled()){
				logger.info("sub floder:"+realPath);
			}
		}else if(StringUtils.equalsIgnoreCase(typeStr, "Image")){
			// 如果请求中不存在文件夹名称，则使用默认的文件夹
			 DateFormat df = new SimpleDateFormat(DEFAULT_SUB_FOLDER_FORMAT_AUTO);
		    // String fileName = df.format(new Date());
		     String fileName =getNewTime(request);
			 realPath = request.getSession().getServletContext().getRealPath(""+FOR_FREEMARKER_LOAD_DIR+ File.separator+FILE_UPLOAD_DIR+ File.separator+FILE_UPLOAD_SUB_IMG_DIR)+File.separator+fileName;
			if(logger.isInfoEnabled()){
				logger.info("default floder:"+realPath);
			}
		}

		File folder = new File(realPath);
		if(!folder.exists()){
			return;
		}

		// 存储子目录 ,路径需要从/freemarker开始
		List<String> subFolderSet = new ArrayList<String>();
		// 存储文件夹
		List<String> subFileerSet = new ArrayList<String>();

		File[] subFiles = folder.listFiles();
		if(null != subFiles && 0 < subFiles.length){
			for(int i=0;i < subFiles.length; i++){
				File _file = subFiles[i];
				if(_file.isDirectory()){
					subFolderSet.add(getDefaultFolderFromFreemarker(_file));
				} else {
					//subFileerSet.add(NorStringUtils.getFileName(_file.getName()));
					subFileerSet.add(_file.getName());
				}
			}
		}

		String callback = request.getParameter("CKEditorFuncNum");
		PrintWriter out;

		response.setContentType("text/html");
		response.setCharacterEncoding("GB2312");
		try {
			out = response.getWriter();
			out.println("<script type='text/javascript'>");
			String root=GlobalConstant.PROJECT_ROOT_PATH_NAME;
			// 定义点击选择js
			out.println("function clickPic(obj){");
			out.println("obj=obj.replace('//','/')");
			out.println("window.opener.CKEDITOR.tools.callFunction(" + callback+ ","+URLEncoder.encode("obj")+")");
			out.println("window.close();");
			out.println("}");

			// 定义文件夹点击响应js
			out.println("function view(obj){window.location.href='../c/browerServer.shtml?type=image&CKEditorFuncNum="+callback+"&fo='+obj;}");
			out.println("</script>");
// -->

			// 这里显示一个返回顶级目录，也就是返回freemarker目录
			out.print("<div style='width:100%;float:left;word-break:break-all;' onclick =view('" + root+URLEncoder.encode("/freemarker/upload") + "')>");
			out.print("<span>[根目录]<span/>");
			out.print("</div>");

			// 如果是子文件夹，显示上级目录链接
			if(StringUtils.isNotBlank(floderName) && !checkIsRoot(folder)){
				String parent = getDefaultFolderFromFreemarker(folder.getParentFile());
				out.print("<div style='width:100%;float:left;word-break:break-all;' onclick =view('" + root+URLEncoder.encode(parent) + "')>");
				out.print("<span>[上级目录]"+parent+"<span/>");
				out.print("</div>");
				if(logger.isDebugEnabled()){
					logger.debug("发现上级目录:"+ parent);
				}
			}

			// 如果是文件夹，则显示文件夹并且可以再次触发下级和上级目录
			if(0 < subFolderSet.size()){
				Iterator<String> subFolderSetIndex = subFolderSet.iterator();
				while(subFolderSetIndex.hasNext()){
					String ftemp = subFolderSetIndex.next();
					// 这里url传递的时候，文件分隔符会有问题，需要转义
					out.print("<div style='width:100%;float:left;word-break:break-all;' onclick =view('" + root+URLEncoder.encode(ftemp) + "')>");
					out.print("<span>[下级目录]"+ftemp+"</span>");
					out.print("</div>");

					if(logger.isDebugEnabled()){
						logger.debug("添加子目录："+ftemp);
					}
				}
			}

			// 如果是文件，则点击就选择文件到控件中
			if(0 < subFileerSet.size()){
				Iterator<String> subFileerSetIndex = subFileerSet.iterator();
				while(subFileerSetIndex.hasNext()){
					String ftemp = subFileerSetIndex.next();
					String f = getDefaultFolderFromFreemarker(folder);
					String fileUrl = f + File.separator + ftemp;
					String src=File.separator+fileUrl;
					src=StringUtils.replace(src, "\\", "//");
					fileUrl = StringUtils.replace(File.separator+fileUrl, "\\", "/");
					fileUrl=root+fileUrl;
					out.print("<div style='width:150px;height:150px;float:left;word-break:break-all;padding:5px;background:#666699;margin:5px;'>");
					out.print("<a href='javascript:clickPic(\""+src+"\")'  onclick=clickPic('"+src+"')><img onclick=clickPic('"+src+"') style='border:none;width:145px;height:145px;' src='"+fileUrl+"' mce_src='"+src+"'title='"+fileUrl+"'/></a>");
					out.print("</div>");

					if(logger.isDebugEnabled()){
						logger.debug("添加文件："+fileUrl);
					}
				}
			}

			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从freemarker这一级开始获取文件夹路径
	 * @return
	 */
	private static String getDefaultFolderFromFreemarker(File folder){
		String path = folder.getPath();
		path = path.substring(path.indexOf(FOR_FREEMARKER_LOAD_DIR));
		return path;
	}

	/**
	 * 判断是否是根目录
	 * @param floderName
	 * @return
	 */
	private static boolean checkIsRoot(File folder){
		//String name = NorStringUtils.getFileName(folder.getName());
		String name=folder.getName();
		return StringUtils.equalsIgnoreCase(name, FILE_UPLOAD_DIR);
	}
	 /**
     * 根据当前的时间建立文件夹，时间格式yyyyMMdd
     *
     * @param path
     * @return
     */
    private File buildFileBySysTime(String path) {
        DateFormat df = new SimpleDateFormat(DEFAULT_SUB_FOLDER_FORMAT_AUTO);
        String fileName = df.format(new Date());
        File file = new File(path + File.separator + fileName);
        if (!file.mkdir()) {
            return null;
        }
        return file;
    }
    /**
     * 返回最新的时间
     *@title
     *@date 2014-9-23
     *@author ZhengYunfei
     * @param folderdir
     * @return
     */
    public static String getNewTime(HttpServletRequest request){
		 String realPath = request.getSession().getServletContext().getRealPath(FOR_FREEMARKER_LOAD_DIR);

		 // 一级目录，如果不存在，创建
		 File firstFolder = new File(realPath + File.separator+ FILE_UPLOAD_DIR);
		 if (!firstFolder.exists()) {
		     if (!firstFolder.mkdir()) {
		         return null;
		     }
		 }
		 // 二级目录，如果不存在，创建
		 String folderdir = realPath +  File.separator+FILE_UPLOAD_DIR + File.separator+ FILE_UPLOAD_SUB_IMG_DIR;
		 File floder = new File(folderdir);
		 Date thisDate=null;
    	 Date oldDate=null;
    	 String fileName="";
    	   String[] files = floder.list();
           if (null != files && 0 < files.length) {
               // 含有子文件夹，则获取最新的一个
               int index = -1;
               for (int i = 0; i < files.length; i++) {
                    fileName = files[i];
                   try {
                        thisDate = DateUtils.parseDate(fileName, new String[] {
                               DEFAULT_SUB_FOLDER_FORMAT_AUTO, DEFAULT_SUB_FOLDER_FORMAT_NO_AUTO });
                       if (oldDate == null) {
                           oldDate = thisDate;
                           index = i;
                       } else {
                           if (thisDate.after(oldDate)) {
                               // 保存最新的时间和数组中的下标
                               oldDate = thisDate;
                               index = i;
                           }
                       }
                   } catch (ParseException e) {
                       // 这里异常吃掉，不用做什么，如果解析失败，会建立新的文件夹，防止人为的建立文件夹导致的异常。
                   }
               }// for
           }
		return fileName;
    }
}
