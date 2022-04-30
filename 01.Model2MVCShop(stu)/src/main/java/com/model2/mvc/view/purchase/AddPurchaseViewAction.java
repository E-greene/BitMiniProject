package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class AddPurchaseViewAction extends Action{

	public String execute( HttpServletRequest request, HttpServletResponse response ) throws Exception {
		int prodNo = Integer.parseInt(request.getParameter("prodNo"));
		
		ProductService service = new ProductServiceImpl();
		PurchaseVO purchaseVO = new PurchaseVO();
		ProductVO productVO = service.getProduct(prodNo);
		
		purchaseVO.setPurchaseProd(productVO);
		
		HttpSession httpSession = request.getSession(true);
		UserVO userVO = (UserVO)httpSession.getAttribute("user");
		
		request.setAttribute("purchaseVO", purchaseVO);
		
		return "forward:/purchase/addPurchaseView.jsp";
	}
	
	
}
