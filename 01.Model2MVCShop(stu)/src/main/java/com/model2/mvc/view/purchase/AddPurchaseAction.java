package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class AddPurchaseAction extends Action {

	public AddPurchaseAction() {
		
	}
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("AddPurchaseAction ½ÃÀÛ================>");
		ProductVO productVO = new ProductVO();
		ProductService productService = new ProductServiceImpl();
		
		productVO = productService.getProduct(Integer.parseInt(request.getParameter("prodNo")));
		
		
		PurchaseVO purchaseVO = new PurchaseVO();
		
		HttpSession session = request.getSession(true);
		
		purchaseVO.setPaymentOption(request.getParameter("paymentOption"));
		purchaseVO.setDivyRequest(request.getParameter("receiverRequest"));
		purchaseVO.setDivyDate(request.getParameter("receiverDate"));
		purchaseVO.setReceiverName(request.getParameter("receiverName"));
		purchaseVO.setReceiverPhone(request.getParameter("receiverPhone"));
		purchaseVO.setDivyAddr(request.getParameter("receiverAddr"));
		purchaseVO.setBuyer((UserVO)session.getAttribute("user"));
		purchaseVO.setPurchaseProd(productVO);
		purchaseVO.setTranCode("1");
		
		
		System.out.println("AddPurchaseAction¿¡ purchaseVO"+purchaseVO);
		
		PurchaseService service = new PurchaseServiceImpl();
		purchaseVO = service.addPurchase(purchaseVO);
		
		request.setAttribute("purchaseVO", purchaseVO);
		
		return "forward:/purchase/readPurchaseView.jsp";
	}
}
