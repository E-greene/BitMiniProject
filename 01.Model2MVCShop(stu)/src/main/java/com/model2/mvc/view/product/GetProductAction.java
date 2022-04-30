package com.model2.mvc.view.product;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;

public class GetProductAction extends Action{
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		ProductService service = new ProductServiceImpl();
		ProductVO vo = service.getProduct(prodNo);
		
		request.setAttribute("vo", vo);
		
		//ÄíÅ°
		String history;
	      
	      Cookie cookie = null;
	      
	      Cookie[] cookies = request.getCookies();
	      if (cookies!=null && cookies.length > 0) {
	         for (int i = 0; i < cookies.length; i++) {
	            cookie = cookies[i];
	            if (cookie.getName().equals("history")) {
	               
	               history = URLDecoder.decode(cookie.getValue(),"euc-kr");
	               history +=","+vo.getProdNo();
	               System.out.println(history);
	               cookie = new Cookie("history",URLEncoder.encode(history,"euc-kr"));
	            }else {
	               cookie = new Cookie("history", Integer.toString(prodNo));
	            }
	            
	         }
	      }
	      cookie.setMaxAge(-1);
	      response.addCookie(cookie);
		
		return "forward:/product/getProduct.jsp";
	}
}
