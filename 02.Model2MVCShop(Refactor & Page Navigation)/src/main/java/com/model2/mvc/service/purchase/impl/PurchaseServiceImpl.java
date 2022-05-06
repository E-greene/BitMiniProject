package com.model2.mvc.service.purchase.impl;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.purchase.PurchaseService;
import com.model2.mvc.service.purchase.dao.PurchaseDAO;


public class PurchaseServiceImpl implements PurchaseService {
	
	private PurchaseDAO purchaseDAO;
	
	public PurchaseServiceImpl() {
		purchaseDAO = new PurchaseDAO();
	}
	
	public Purchase getPurchase(int tranNo) throws Exception{
		System.out.println("=====PurchaseServiceImpl PurchaseVO getPurchase ����======");
		return purchaseDAO.findPurchase(tranNo);
	}
	
	public Map<String,Object> getPurchaseList(Search search) throws Exception {
		System.out.println("=====PurchaseServiceImpl HashMap<String,Object> getPurchaseList ���� ======");
		return purchaseDAO.getPurchaseList(search);
	}
	
	public Purchase addPurchase(Purchase purchase) throws Exception{
		System.out.println("=====PurchaseServiceImpl PurchaseVO addPurchase ����======");
		return purchaseDAO.insertPurchase(purchase);
	}
	
	public void updateTranCode(Purchase purchase) throws Exception {
		System.out.println("===PurchaseServiceImpl updateTranCode() ����=========");
		purchaseDAO.updateTranCode(purchase);
	}
	
	public void updatePurchase(Purchase purchase) throws Exception{
		System.out.println("===PurchaseServiceImpl updatePurchase() ����=========");
		purchaseDAO.updatePurchase(purchase);
	}
	
}
