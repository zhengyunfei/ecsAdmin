package com.zero2ipo.cfj.upload.webc;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
/**
 * 文件上传-文章附件的上传
 * @title 
 * @author ZhengYunFei
 * @date 2014-10-21
 */
public class DownLoadify extends HttpServlet {

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
		String filePath=request.getParameter("filePath");
		String title=request.getParameter("title");
		String path = this.getServletContext().getRealPath("/");
		path=path+filePath;
	    try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.setHeader("Content-Disposition", "attachment; filename="+ new String( title.getBytes("gb2312"), "ISO8859-1" ) );  
            response.setContentLength(buffer.length);  
            response.setContentType("application/octet-stream");
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            toClient.write(buffer,0,buffer.length);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}
	public static void main(String[] args) {
		System.out.println(JSONArray.fromObject(""));
	}
}