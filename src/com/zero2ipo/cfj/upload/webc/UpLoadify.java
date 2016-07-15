package com.zero2ipo.cfj.upload.webc;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
/**
 * 文件上传-文章附件的上传
 * @title 
 * @author ZhengYunFei
 * @date 2014-10-21
 */
public class UpLoadify extends HttpServlet {

	private static final long serialVersionUID = 2384326745121073713L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String path = this.getServletContext().getRealPath("/");
		String sourcePath = path + "upload/source/";
		path = path + "upload/";
		File folder = new File(path);
		File sourceFolder = new File(sourcePath);
		if (!folder.exists()) {
			// 文件夹不存在则创建
			folder.mkdirs();
		}
		if (!sourceFolder.exists()) {
			// 文件夹不存在则创建
			sourceFolder.mkdirs();
		}
		ServletFileUpload sfu = new ServletFileUpload(new DiskFileItemFactory());
		sfu.setHeaderEncoding("UTF-8");
		try {
			List<?> fileList = sfu.parseRequest(request);
			String sourceName = "";
			String extName = "";
			String name = "";
			String sfileName = "";
			String fileName = "";
			for (int i = 0; i < fileList.size(); i++) {
				FileItem fi = (FileItem) fileList.get(i);
				if (!fi.isFormField()) {
					sourceName = fi.getName();
					if (sourceName == null || "".equals(sourceName.trim())) {
						continue;
					}
					if (sourceName.lastIndexOf(".") >= 0) {
						// 扩展名
						name = sourceName.substring(0, sourceName
								.lastIndexOf("."));
						extName = sourceName.substring(sourceName
								.lastIndexOf("."));
					}
					// 文件名规则 前缀 + 时间 + 两位随机数 + 文件分类(标识图片尺寸) + 扩展名
					Calendar ca = Calendar.getInstance();
					DecimalFormat df = new DecimalFormat();
					df.setMinimumIntegerDigits(2);
					String st = "";
					if (st != null && st.length() > 6) {
						st = st.substring(0, 6);
					}
					String dateTime = ca.get(Calendar.YEAR) + ""
							+ df.format(ca.get(Calendar.MONTH) + 1) + ""
							+ df.format(ca.get(Calendar.DATE)) + ""
							+ df.format(ca.get(Calendar.HOUR)) + ""
							+ df.format(ca.get(Calendar.MINUTE)) + ""
							+ df.format(ca.get(Calendar.SECOND));
					Random rand = new Random();
					int rand_num = rand.nextInt(89) + 10;
					fileName = dateTime + "_" + rand_num + extName;
					sfileName = dateTime + "_" + rand_num + extName;
					File saveSourceFile = new File(sourcePath + sfileName);
					fi.write(saveSourceFile);
				}
			}
			response.getWriter().println("/upload/source/" + fileName);
			System.out.println("上传成功=======================================");
		} catch (FileUploadException e) {
			response.getWriter().println("0");
			e.printStackTrace();
		} catch (Exception e) {
			response.getWriter().println("0");
			e.printStackTrace();
		}
	}
}