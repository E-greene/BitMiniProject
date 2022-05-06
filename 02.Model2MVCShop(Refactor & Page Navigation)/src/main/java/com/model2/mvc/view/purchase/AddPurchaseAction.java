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
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class AddPurchaseAction extends Action {

	public AddPurchaseAction() {
		
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("AddPurchaseAction ½ÃÀÛ================>");
		Product product = new Product();
		ProductService productService = new ProductServiceImpl();
		
		product = productService.getProduct(Integer.parseInt(request.getParameter("prodNo")));
		
		
		Purchase purchase = new Purchase();
		
		HttpSession session = request.getSession(true);
		
		purchase.setPaymentOption(request.getParameter("paymentOption"));
		purchase.setDivyRequest(request.getParameter("receiverRequest"));
		purchase.setDivyDate(request.getParameter("receiverDate"));
		purchase.setReceiverName(request.getParameter("receiverName"));
		purchase.setReceiverPhone(request.getParameter("receiverPhone"));
		purchase.setDivyAddr(request.getParameter("receiverAddr"));
		purchase.setBuyer((User)session.getAttribute("user"));
		purchase.setPurchaseProd(product);
		purchase.setTranCode("1");
		
		
		System.out.println("AddPurchaseAction¿¡ purchaseVO"+purchase);
		
		PurchaseService service = new PurchaseServiceImpl();
		purchase = service.addPurchase(purchase);
		
		request.setAttribute("purchase", purchase);
		
		return "forward:/purchase/readPurchaseView.jsp";
	}
}
