package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;


public class UpdateTranCodeAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("======UpdateTranCodeAction Ω√¿€=======");
		Purchase purchase = new Purchase();
		Product product = new Product();
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		String tranCode = request.getParameter("tranCode");
		
		String menu = request.getParameter("menu");
		
		String user = request.getParameter("user");
		
		product.setProdNo(prodNo);
		purchase.setPurchaseProd(product);
		purchase.setTranCode(tranCode);
		
		PurchaseService service = new PurchaseServiceImpl();
		service.updateTranCode(purchase);
		
		if(user.equals("user")) {
			return "/listPurchase.do";
		}else {
			return "redirect:/listProduct.do?menu="+menu;
		}
		
	}
	
}
