package com.model2.mvc.service.purchase.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.domain.Purchase;
import com.model2.mvc.service.domain.User;

public class PurchaseDAO {

	public PurchaseDAO() {
		
	}
	
	public Map<String, Object> getPurchaseList(Search search) throws Exception {
		System.out.println("=====PurchaseDAO Hashmap<String,Object> getPurchaseList 시작=====");
		Connection con = DBUtil.getConnection();
		Purchase purchaseVO = null;
		Product productVO = null;
		User userVO = null;
		
		String sql = "select * from transaction ";
		
		sql += " ORDER BY tran_no";

		
		//==> TotalCount
		int totalCount = this.getTotalCount(sql);
		System.out.println("PurchaseDAO  :: totalCount : "+totalCount);
		
		// CurrentPage 게시물만 받도록 Query 다시 구성
		sql = makeCurrentPageSql(sql, search);
		
//		int total = rs.getRow();
//		System.out.println("로우의 수 : "+total);	
		
		PreparedStatement pStmt = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		
		ResultSet rs = pStmt.executeQuery();
		
		rs.last();
		
		System.out.println("search : "+search);
		
		Map<String,Object> map = new HashMap<String,Object>();
		
//		map.put("count", new Integer(total));
		
//		rs.absolute(search.getPage() * search.getPageUnit() - search.getPageUnit()+1);
//		System.out.println("searchVO.getPage() : "+search.getPage());
//		System.out.println("searchVO.getPageUnit() : "+search.getPageUnit());
		
		List<Purchase> list = new ArrayList<Purchase>();
		
		while(rs.next()) {
//		if(total > 0) {
//			if(rs.first()) {
//				for(int i = 0; i<search.getPageUnit(); i++) {
					
					userVO = new User();
					productVO = new Product();
					purchaseVO = new Purchase();
					
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
//					if(!rs.next()) {
//						break;
//					}
				//}
			}
			
		//==> totalCount 정보 저장
		map.put("totalCount", new Integer(totalCount));
		//==> currentPage의 게시물 정보를 갖는 List 저장
		map.put("list", list);
		
//		System.out.println("list.size() : "+list.size());
//		map.put("list", list);
//		System.out.println("map.size() : "+map.size());
		
		con.close();
		rs.close();	
		pStmt.close();
		
		return map;
	}	//end of getPurchaseList()
	
	public Purchase findPurchase(int tranNo) throws Exception{
		System.out.println("PurchaseDAO findPurchase() start");
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM product p, users u, transaction t "
				+"WHERE t.tran_no=? AND t.prod_no=p.prod_no AND t.buyer_id=u.user_id";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, tranNo);
		
		ResultSet rs = pStmt.executeQuery();
		
		Product productVO = null;
		User userVO = null;
		Purchase purchaseVO = null;
		
		while(rs.next()) {
			productVO = new Product();
			productVO. setProdNo(rs.getInt("prod_No"));
			productVO. setProdName(rs.getString("prod_Name"));
			productVO. setProdDetail(rs.getString("prod_Detail"));
			productVO. setManuDate(rs.getString("manufacture_day"));
			productVO. setPrice(rs.getInt("price"));
			productVO. setFileName(rs.getString("IMAGE_FILE"));
			productVO. setRegDate(rs.getDate("REG_DATE"));
			
			userVO = new User();
			userVO.setUserId(rs.getString("user_id"));
			userVO.setUserName(rs.getString("user_name"));
			userVO.setPassword(rs.getString("password"));
			userVO.setRole(rs.getString("role"));
			userVO.setSsn(rs.getString("ssn"));
			userVO.setPhone(rs.getString("cell_phone"));
			userVO.setAddr(rs.getString("addr"));
			userVO.setEmail(rs.getString("email"));
			userVO.setRegDate(rs.getDate("reg_date"));
			
			purchaseVO = new Purchase();
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
	
	public Purchase insertPurchase(Purchase purchaseVO) throws Exception{
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
	
	public void updateTranCode(Purchase purchaseVO) throws Exception {
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
	
	public void updatePurchase(Purchase purchaseVO) throws Exception {
		System.out.println("PurchaseDAO updatePurchase() 시작======>");
		
		Connection con =DBUtil.getConnection();
		
		String sql = "UPDATE transaction "
				+ "SET payment_option = ?, receiver_name= ?, receiver_phone=?, demailaddr=?, dlvy_request=?, dlvy_date=? "
				+ "WHERE tran_no = ? ";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1,purchaseVO.getPaymentOption());
		pStmt.setString(2,purchaseVO.getReceiverName());
		pStmt.setString(3,purchaseVO.getReceiverPhone());
		pStmt.setString(4,purchaseVO.getDivyAddr());
		pStmt.setString(5,purchaseVO.getDivyRequest());
		pStmt.setString(6,purchaseVO.getDivyDate());
		pStmt.setInt(7,purchaseVO.getTranNo());
		
		int updatePurchaseOk = pStmt.executeUpdate();
		
		if(updatePurchaseOk == 1) {
			System.out.println("purchase updatePurchase Success");
		}else {
			System.out.println("purchase updatePurchase Fail");
		}
		
		con.close();
		pStmt.close();
	}
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount) return ==>totalCount : 총 게시물 수
	private int getTotalCount(String sql) throws Exception{
		System.out.println("PurchaseDAO int getTotalCount(String sql) 시작==================>");
		
		sql = "SELECT COUNT(*) "+
		          "FROM ( " +sql+ ") countTable";
		
		Connection con = DBUtil.getConnection();
		PreparedStatement pStmt = con.prepareStatement(sql);
		ResultSet rs = pStmt.executeQuery();
		
		int totalCount = 0;
		if( rs.next() ){
			totalCount = rs.getInt(1);
		}
		
		pStmt.close();
		con.close();
		rs.close();
		
		System.out.println("getTotalCount() totalCount ======= : "+totalCount);
		return totalCount;
		
	}
	
	// 게시판 currentPage Row 만 return
	private String makeCurrentPageSql(String sql, Search search) {
		System.out.println("PurchaseDAO String makeCurrentPageSql(String sql, Search search) 시작==================>");
		
		sql = "SELECT * "+
				"FROM( SELECT inner_table.*, ROWNUM AS row_seq "+
				" FROM("+sql+") inner_table "+
				" WHERE ROWNUM <="+search.getCurrentPage() * search.getPageSize()+") "+
				"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1)+" AND "+search.getCurrentPage()*search.getPageSize();
		
		System.out.println("ProductDAO :: make SQL :: " + sql);
		
		return sql;
	}

}
