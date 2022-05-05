package com.model2.mvc.service.purchase.impl;

import java.util.HashMap;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;

public class PurchaseServiceImpl implements PurchaseService {
	
	private PurchaseDAO purchaseDAO;
	
	public PurchaseServiceImpl() {
		purchaseDAO = new PurchaseDAO();
	}
	
	public PurchaseVO getPurchase(int tranNo) throws Exception{
		System.out.println("=====PurchaseServiceImpl PurchaseVO getPurchase 시작======");
		return purchaseDAO.findPurchase(tranNo);
	}
	
	public HashMap<String,Object> getPurchaseList(SearchVO searchVO) throws Exception {
		System.out.println("=====PurchaseServiceImpl HashMap<String,Object> getPurchaseList 시작 ======");
		return purchaseDAO.getPurchaseList(searchVO);
	}
	
	public PurchaseVO addPurchase(PurchaseVO purchaseVO) throws Exception{
		System.out.println("=====PurchaseServiceImpl PurchaseVO addPurchase 시작======");
		return purchaseDAO.insertPurchase(purchaseVO);
	}
	
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		System.out.println("===PurchaseServiceImpl updateTranCode() 시작=========");
		purchaseDAO.updateTranCode(purchaseVO);
	}
	
	public void updatePurchase(PurchaseVO purchaseVO) throws Exception{
		System.out.println("===PurchaseServiceImpl updatePurchase() 시작=========");
		purchaseDAO.updatePurchase(purchaseVO);
	}
	
}
