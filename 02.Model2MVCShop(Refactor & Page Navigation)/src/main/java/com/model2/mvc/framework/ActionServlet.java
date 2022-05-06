package com.model2.mvc.framework;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.util.HttpUtil;


public class ActionServlet extends HttpServlet {
	
	///Field
	private RequestMapping requestMapping;
	
	///Method
	@Override
	public void init() throws ServletException {
		super.init();
		String resources=getServletConfig().getInitParameter("resources");
		requestMapping=RequestMapping.getInstance(resources);
		
		System.out.println("ActionServlet init() resources : "+resources);
	}

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) 
																						throws ServletException, IOException {
		System.out.println("ActionServlet service() ����==========================");
		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		String requestPath = url.substring(contextPath.length());
		System.out.println("\nActionServlet.service() RequestURI : "+requestPath);
		System.out.println("\nActionServlet.service() url : "+url);
		System.out.println("\nActionServlet.service() contextPath : "+contextPath);
		
		
		try{
			Action action = requestMapping.getAction(requestPath);
			action.setServletContext(getServletContext());
			
			String resultPage=action.execute(request, response);
			String path=resultPage.substring(resultPage.indexOf(":")+1);
			
			if(resultPage.startsWith("forward:")){
				HttpUtil.forward(request, response, path);
			}else{
				HttpUtil.redirect(response, path);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
}