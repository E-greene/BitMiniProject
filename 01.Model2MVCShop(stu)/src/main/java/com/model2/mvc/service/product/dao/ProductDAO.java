package com.model2.mvc.service.product.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.model2.mvc.common.SearchVO;
import com.model2.mvc.common.util.DBUtil;
import com.model2.mvc.service.product.vo.ProductVO;

public class ProductDAO {
	
	public ProductDAO() {
		
	}
	
	public void insertProduct(ProductVO productVO) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "insert into product(PROD_NO, PROD_NAME, PROD_DETAIL, MANUFACTURE_DAY, PRICE, IMAGE_FILE, REG_DATE) \r\n"
				+ "values (seq_product_prod_no.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE)";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, productVO.getProdName());
		pStmt.setString(2, productVO.getProdDetail());
		pStmt.setString(3, productVO.getManuDate().replace("-",""));
		pStmt.setInt(4, productVO.getPrice());
		pStmt.setString(5, productVO.getFileName());
		
		pStmt.executeUpdate();
		
		con.close();
		
	}
	
	public HashMap<String, Object> getProductList(SearchVO searchVO) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "select * from product ";
		
		if(searchVO.getSearchCondition() != null) {
			if(searchVO.getSearchCondition().equals("0")) {
				sql += " WHERE prod_no =  "+searchVO.getSearchKeyword();
			} else if (searchVO.getSearchCondition().equals("1")) {
				sql += " WHERE prod_name = '"+searchVO.getSearchKeyword()+"' ";
			} else /*if(searchVO.getSearchCondition().equals("2"))*/ {
				sql += " WHERE price = ' "+searchVO.getSearchKeyword()+ " ' ";
			}
			
		}
		
		sql += " ORDER BY prod_no";
		
		
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
		
		List<ProductVO> list = new ArrayList<ProductVO>();
		if (total > 0) {
			for( int i = 0; i<searchVO.getPageUnit(); i++) {
				ProductVO vo = new ProductVO();
				vo.setProdNo(rs.getInt("Prod_NO"));
				vo.setProdName(rs.getString("PROD_NAME"));
				vo.setProdDetail(rs.getString("PROD_DETAIL"));
				vo.setManuDate(rs.getString("MANUFACTURE_DAY"));
				vo.setPrice(rs.getInt("PRICE"));
				vo.setFileName(rs.getString("IMAGE_FILE"));
				vo.setRegDate(rs.getDate("REG_DATE"));
				vo.setProTranCode("0");
				
				list.add(vo);
				if(!rs.next()) {
					break;
				}		
			}
		}
		System.out.println("list.size() : "+list.size());
		map.put("list", list);
		System.out.println("map.size() : "+map.size());
		
		con.close();
		
		return map;
	}//end of getProductList()
	
	public ProductVO findProduct(int productNo) throws Exception {
		
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM product WHERE prod_no=? ";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, productNo);
		
		ResultSet rs = pStmt.executeQuery();
		
		ProductVO productVO = null;
		while(rs.next()) {
			productVO = new ProductVO();
			productVO. setProdNo(rs.getInt("prod_No"));
			productVO. setProdName(rs.getString("prod_Name"));
			productVO. setProdDetail(rs.getString("prod_Detail"));
			productVO. setManuDate(rs.getString("Manufacture_day"));
			productVO. setPrice(rs.getInt("price"));
			productVO. setFileName(rs.getString("IMAGE_FILE"));
			productVO. setRegDate(rs.getDate("REG_DATE"));
		}
		con.close();
		
		return productVO;
	}
	
	public void updateProduct(ProductVO productVO) throws Exception{
		
		Connection con = DBUtil.getConnection();
		
		String sql = "UPDATE product SET prod_name=?, prod_detail=?, manufacture_day=?, price=?, image_file=? WHERE prod_no = ? ";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, productVO.getProdName());
		pStmt.setString(2, productVO.getProdDetail());
		pStmt.setString(3, productVO.getManuDate());
		pStmt.setInt(4, productVO.getPrice());
		pStmt.setString(5, productVO.getFileName());
		pStmt.setInt(6, productVO.getProdNo());
		
		pStmt.executeUpdate();
		
		con.close();
	}
}
