package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;

public class AddPurchaseViewAction extends Action{

	public String execute( HttpServletRequest request, HttpServletResponse response ) throws Exception {
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		ProductService service = new ProductServiceImpl();
		Purchase purchase = new Purchase();
		Product product = service.getProduct(prodNo);
		
		purchase.setPurchaseProd(product);
		
		HttpSession httpSession = request.getSession(true);
		User user = (User)httpSession.getAttribute("user");
		
		request.setAttribute("purchase", purchase);
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
	
	
}
