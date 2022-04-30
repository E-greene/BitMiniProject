package com.model2.mvc.view.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.framework.Action;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.product.impl.ProductServiceImpl;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.impl.PurchaseServiceImpl;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class ListProductAction extends Action {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		SearchVO searchVO = new SearchVO();
		
		String menu = request.getParameter("menu");
		
		int page = 1;
		if(request.getParameter("page")!= null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		searchVO.setPage(page);
		searchVO.setSearchCondition(request.getParameter("searchCondition"));
		searchVO.setSearchKeyword(request.getParameter("searchKeyword"));
		
		String pageUnit=getServletContext().getInitParameter("pageSize");
		searchVO.setPageUnit(Integer.parseInt(pageUnit));
		
		PurchaseService purchaseService = new PurchaseServiceImpl();
		HashMap<String,Object> purchaseMap = purchaseService.getPurchaseList(searchVO);
		List<PurchaseVO> purchaseList = (List<PurchaseVO>) purchaseMap.get("list");
		
		ProductService service = new ProductServiceImpl();
		HashMap<String, Object> map = service.getProductList(searchVO);
		List<ProductVO> productList = new ArrayList<ProductVO>();
		
		
		//getPurchaseList 랑 getProductList prodNo 같으면 trancode 1로 바꾸기
		if(purchaseMap != null) {
			List<ProductVO> newProductList = (List<ProductVO>) map.get("list");
			for(int i=0; i<newProductList.size(); i++) {
				ProductVO prodVO = newProductList.get(i);
				for(int j=0; j<purchaseList.size(); j++) {
					ProductVO purVO = purchaseList.get(j).getPurchaseProd();
					if(prodVO.getProdNo() == purVO.getProdNo() ) {
						prodVO.setProTranCode(purchaseList.get(j).getTranCode());
					}
				}
				System.out.println("구매한 후 ProductVO 확인 "+prodVO);
				productList.add(prodVO);
			}
		}
		
		map.put("list", productList);
		System.out.println("ListProductAction에서 productList :   "+productList);
		
		
		request.setAttribute("menu", menu);
		request.setAttribute("map", map);
		request.setAttribute("searchVO", searchVO);
		
		return "forward:/product/listProduct.jsp";
	}
}
