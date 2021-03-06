package com.model2.mvc.view.product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.User;

public class UpdateProductAction extends Action{
	
	@Override
	public String execute( HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		System.out.println(" UpdateProductActionl String execute(request, response) ????=====================>");
		
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		Product product = new Product();
		product. setProdNo(prodNo);
		product. setProdName(request.getParameter("prodName"));
		product. setProdDetail(request.getParameter("prodDetail"));
		product. setManuDate(request.getParameter("manuDate"));
		product. setPrice(Integer.parseInt(request.getParameter("price")));
		product. setFileName(request.getParameter("fileName"));
		
		ProductService service = new ProductServiceImpl();
		service.updateProduct(product);
		
//		HttpSession session = request.getSession();
//		String sessionId = ((UserVO)session.getAttribute("user")).getUserId();
//		
//		if(sessionId.equals(userId)) {
//			session.setAttribute("user", userVO );
//		}
		
		return "redirect:/getProduct.do?prodNo="+prodNo;
		
	}
}
