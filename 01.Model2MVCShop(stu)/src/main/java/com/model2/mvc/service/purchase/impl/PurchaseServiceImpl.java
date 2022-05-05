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
		System.out.println("=====PurchaseServiceImpl PurchaseVO getPurchase ����======");
		return purchaseDAO.findPurchase(tranNo);
	}
	
	public HashMap<String,Object> getPurchaseList(SearchVO searchVO) throws Exception {
		System.out.println("=====PurchaseServiceImpl HashMap<String,Object> getPurchaseList ���� ======");
		return purchaseDAO.getPurchaseList(searchVO);
	}
	
	public PurchaseVO addPurchase(PurchaseVO purchaseVO) throws Exception{
		System.out.println("=====PurchaseServiceImpl PurchaseVO addPurchase ����======");
		return purchaseDAO.insertPurchase(purchaseVO);
	}
	
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		System.out.println("===PurchaseServiceImpl updateTranCode() ����=========");
		purchaseDAO.updateTranCode(purchaseVO);
	}
	
	public void updatePurchase(PurchaseVO purchaseVO) throws Exception{
		System.out.println("===PurchaseServiceImpl updatePurchase() ����=========");
		purchaseDAO.updatePurchase(purchaseVO);
	}
	
}
