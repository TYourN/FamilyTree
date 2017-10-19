package cn.items.mssm.entity;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtil {
	/*用于返回JSON数据*/
	public static void write(HttpServletResponse response,Object object)throws Exception{
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out=response.getWriter();
		out.println(object);
		out.flush();
		out.close();
	}
}
