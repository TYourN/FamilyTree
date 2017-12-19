package cn.items.mssm.Filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PrivateFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse rep=(HttpServletResponse) response;
		StringBuffer basePath=req.getRequestURL();
		HttpSession session=req.getSession();		
		if(session.getAttribute("userinfo")!=null){
			if(basePath.equals("http://localhost:8080/GenealogySys/jsp/manage/index.jsp")){
				@SuppressWarnings("unchecked")
				Map<String,Object> map=(Map<String, Object>) session.getAttribute("userinfo");
				if((map.get("dbid").toString()).equals("0")){
					rep.sendRedirect("../login.jsp");
				}else{
					chain.doFilter(request, response);
				}
			}else{
				chain.doFilter(request, response);
			}			
		}
		else{
			rep.sendRedirect("../login.jsp");		      
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	/** 
	 * @ClassName: PrivateFilter 
	 * @Description: TODO
	 * @author: Administrator
	 * @date: 2017年11月28日 下午2:53:18 
	 */
}
