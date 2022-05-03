package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;
import com.model2.mvc.service.purchase.vo.PurchaseVO;
import com.model2.mvc.service.user.vo.UserVO;

public class PurchaseDAO {

	public PurchaseDAO() {
		
	}
	
	public HashMap<String, Object> getPurchaseList(SearchVO searchVO) throws Exception {
		System.out.println("=====PurchaseDAO Hashmap<String,Object> getPurchaseList 시작=====");
		Connection con = DBUtil.getConnection();
		PurchaseVO purchaseVO = null;
		ProductVO productVO = null;
		UserVO userVO = null;
		
		String sql = "select * from transaction ";
		
		sql += " ORDER BY tran_no";
		
		PreparedStatement pStmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		ResultSet rs = pStmt.executeQuery();
		
		rs.last();
		int total = rs.getRow();
		System.out.println("로우의 수 : "+total);
		
		HashMap<String,Object> map = new HashMap<String,Object>();
		
		map.put("count", new Integer(total));
		
		rs.absolute(searchVO.getPage() * searchVO.getPageUnit() - searchVO.getPageUnit()+1);
		System.out.println("searchVO.getPage() : "+searchVO.getPage());
		System.out.println("searchVO.getPageUnit() : "+searchVO.getPageUnit());
		
		List<PurchaseVO> list = new ArrayList<PurchaseVO>();
			
		if(total > 0) {
			if(rs.first()) {
				for(int i = 0; i<searchVO.getPageUnit(); i++) {
					
					userVO = new UserVO();
					productVO = new ProductVO();
					purchaseVO = new PurchaseVO();
					
					productVO.setProdNo(rs.getInt("prod_no"));
					purchaseVO.setPurchaseProd(productVO);
					
					userVO.setUserId(rs.getString("buyer_id"));
					purchaseVO.setBuyer(userVO);
					
					purchaseVO.setTranNo(rs.getInt("tran_no"));
					purchaseVO.setPaymentOption(rs.getString("payment_option"));
					purchaseVO.setReceiverName(rs.getString("receiver_name"));
					purchaseVO.setReceiverPhone(rs.getString("receiver_phone"));
					purchaseVO.setDivyAddr(rs.getString("demailaddr"));
					purchaseVO.setDivyDate(rs.getString("dlvy_date"));
					purchaseVO.setDivyRequest(rs.getString("dlvy_request"));
					purchaseVO.setTranCode(rs.getString("tran_status_code"));
					purchaseVO.setOrderDate(rs.getDate("order_data"));
					
					list.add(purchaseVO);
					if(!rs.next()) {
						break;
					}	
				}
			}
			
		}
		System.out.println("list.size() : "+list.size());
		map.put("list", list);
		System.out.println("map.size() : "+map.size());
		
		con.close();
		rs.close();	
		pStmt.close();
		
		return map;
	}
	
	public PurchaseVO findPurchase(int tranNo) throws Exception{
		System.out.println("PurchaseDAO findPurchase() start");
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM product p, users u, transaction t "
				+"WHERE t.tran_no=? AND t.prod_no=p.prod_no AND t.buyer_id=u.user_id";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, tranNo);
		
		ResultSet rs = pStmt.executeQuery();
		
		ProductVO productVO = null;
		UserVO userVO = null;
		PurchaseVO purchaseVO = null;
		
		while(rs.next()) {
			productVO = new ProductVO();
			productVO. setProdNo(rs.getInt("prod_No"));
			productVO. setProdName(rs.getString("prod_Name"));
			productVO. setProdDetail(rs.getString("prod_Detail"));
			productVO. setManuDate(rs.getString("manufacture_day"));
			productVO. setPrice(rs.getInt("price"));
			productVO. setFileName(rs.getString("IMAGE_FILE"));
			productVO. setRegDate(rs.getDate("REG_DATE"));
			
			userVO = new UserVO();
			userVO.setUserId(rs.getString("user_id"));
			userVO.setUserName(rs.getString("user_name"));
			userVO.setPassword(rs.getString("password"));
			userVO.setRole(rs.getString("role"));
			userVO.setSsn(rs.getString("ssn"));
			userVO.setPhone(rs.getString("cell_phone"));
			userVO.setAddr(rs.getString("addr"));
			userVO.setEmail(rs.getString("email"));
			userVO.setRegDate(rs.getDate("reg_date"));
			
			purchaseVO = new PurchaseVO();
			purchaseVO.setBuyer(userVO);
			purchaseVO.setPurchaseProd(productVO);
			purchaseVO.setTranNo(rs.getInt("tran_no"));
			purchaseVO.setPaymentOption(rs.getString("payment_option"));
			purchaseVO.setReceiverName(rs.getString("receiver_name"));
			purchaseVO.setReceiverPhone(rs.getString("receiver_phone"));
			purchaseVO.setDivyAddr(rs.getString("demailaddr"));
			purchaseVO.setDivyRequest(rs.getString("dlvy_request"));
			purchaseVO.setTranCode(rs.getString("tran_status_code"));
			purchaseVO.setOrderDate(rs.getDate("order_data"));
			purchaseVO.setDivyDate(rs.getString("dlvy_date"));
			
			
		}
		
		con.close();
		pStmt.close();
		rs.close();
		
		return purchaseVO;
		
	}//end of findPurchase()
	
	public PurchaseVO insertPurchase(PurchaseVO purchaseVO) throws Exception{
		System.out.println("PurchaseDAO insertPurchase() start");
		
		Connection con = DBUtil.getConnection();
		
		String sql = "INSERT INTO TRANSACTION(tran_no, prod_no, buyer_id, payment_option, receiver_name, "
				+ "receiver_phone, demailaddr, dlvy_request, tran_status_code, order_data, dlvy_date)"
				+ "VALUES(seq_transaction_tran_no.NEXTVAL, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, SYSDATE, ?) ";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, purchaseVO.getPurchaseProd().getProdNo());
		pStmt.setString(2, purchaseVO.getBuyer().getUserId());
		pStmt.setString(3, purchaseVO.getPaymentOption());
		pStmt.setString(4, purchaseVO.getReceiverName());
		pStmt.setString(5, purchaseVO.getReceiverPhone());
		pStmt.setString(6, purchaseVO.getDivyAddr());
		pStmt.setString(7, purchaseVO.getDivyRequest());
		pStmt.setString(8, purchaseVO.getTranCode());
		pStmt.setString(9, purchaseVO.getDivyDate());
		
		int insertOk = pStmt.executeUpdate();
		
		if(insertOk == 1) {
			System.out.println("purchase Insert Success");
		}else {
			System.out.println("purchase Insert Fail");
		}
		
		
		
		con.close();
		pStmt.close();
		
		return purchaseVO;
	}
	
	public void updateTranCode(PurchaseVO purchaseVO) throws Exception {
		System.out.println("PurchaseDAO updateTranCode() 시작======>");
		
		Connection con = DBUtil.getConnection();
		
		String sql = "UPDATE transaction SET tran_status_code=? WHERE prod_no=? ";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, purchaseVO.getTranCode());
		pStmt.setInt(2, purchaseVO.getPurchaseProd().getProdNo());
		
		int updateTranCodeOk = pStmt.executeUpdate();
		
		if(updateTranCodeOk == 1) {
			System.out.println("purchase updateTranCode Success");
		}else {
			System.out.println("purchase updateTranCode Fail");
		}
		
		con.close();
		pStmt.close();
	}

}
