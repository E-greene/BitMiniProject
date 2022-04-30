package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class UpdateTranCodeAction extends Action {

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("======UpdateTranCodeAction Ω√¿€=======");
		PurchaseVO purchaseVO = new PurchaseVO();
		ProductVO productVO = new ProductVO();
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		String tranCode = request.getParameter("tranCode");
		
		String menu = request.getParameter("menu");
		
		String user = request.getParameter("user");
		
		productVO.setProdNo(prodNo);
		purchaseVO.setPurchaseProd(productVO);
		purchaseVO.setTranCode(tranCode);
		
		PurchaseService service = new PurchaseServiceImpl();
		service.updateTranCode(purchaseVO);
		
		if(user.equals("user")) {
			return "/listPurchase.do";
		}else {
			return "redirect:/listProduct.do?menu="+menu;
		}
		
	}
	
}
