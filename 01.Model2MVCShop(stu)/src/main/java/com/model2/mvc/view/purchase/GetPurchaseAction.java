package com.model2.mvc.view.purchase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.framework.Action;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class GetPurchaseAction extends Action{

	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("GetPurchaseAction Ω√¿€");

		
		int tranNo = Integer.parseInt(request.getParameter("tranNo"));
		
		//HttpSession httpSession = request.getSession(true);
		//UserVO userVO = (UserVO)httpSession.getAttribute("user");
		
		PurchaseService service = new PurchaseServiceImpl();
		PurchaseVO purchaseVO = service.getPurchase(tranNo);
		
		request.setAttribute("purchaseVO" , purchaseVO);
		
		return "forward:/purchase/getPurchase.jsp";
	}

}
