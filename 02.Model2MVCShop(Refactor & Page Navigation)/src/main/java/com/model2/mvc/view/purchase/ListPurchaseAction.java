package com.model2.mvc.view.purchase;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;

public class ListPurchaseAction extends Action {
	
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("======ListPurchaseAction 시작=======");
		
		Search search = new Search();
		
		HttpSession session = request.getSession(true);
		User user = (User)session.getAttribute("user");
		
		// 구매목록조회 밑 페이지 선택
//		int page = 1;
//		if(request.getParameter("page") != null) {
//			page = Integer.parseInt(request.getParameter("page"));
//		}
		int currentPage = 1;
		if(request.getParameter("currentPage") != null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
//		search.setPage(page);
		search.setCurrentPage(currentPage);
		search.setSearchCondition(request.getParameter("searchCondition"));
		search.setSearchCondition(request.getParameter("searchKeyword"));
		
//		String pageUnit = getServletContext().getInitParameter("pageSize");
//		search.setPageUnit(Integer.parseInt(pageUnit));
		int pageSize = Integer.parseInt(getServletContext().getInitParameter("pageSize"));
		int pageUnit = Integer.parseInt(getServletContext().getInitParameter("pageUnit"));
		search.setPageSize(pageSize);
		
		// DB에서 검색한 데이터 처리
		PurchaseService service = new PurchaseServiceImpl();
		Map<String, Object> map = service.getPurchaseList(search);
		
		Page resultPage = new Page(currentPage, ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println("ListPurchaseAction :: "+resultPage);
		
		//Model과 View 연결
		request.setAttribute("resultPage", resultPage);
		request.setAttribute("list", map.get("list"));
		request.setAttribute("search", search);
		
//		request.setAttribute("map",map);
//		request.setAttribute("search", search);
		
		return "forward:/purchase/listPurchase.jsp";
	}
}
