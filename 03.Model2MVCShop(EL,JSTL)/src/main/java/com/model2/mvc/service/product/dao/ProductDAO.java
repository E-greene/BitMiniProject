package com.model2.mvc.service.product.dao;

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

public class ProductDAO {
	
	public ProductDAO() {
		
	}
	
	public void insertProduct(Product product) throws Exception{
		System.out.println("ProductDAO insertProduct(Product product) 시작==================>");
		Connection con = DBUtil.getConnection();
		
		String sql = "insert into product(PROD_NO, PROD_NAME, PROD_DETAIL, MANUFACTURE_DAY, PRICE, IMAGE_FILE, REG_DATE) \r\n"
				+ "values (seq_product_prod_no.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE)";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, product.getProdName());
		pStmt.setString(2, product.getProdDetail());
		pStmt.setString(3, product.getManuDate().replace("-",""));
		pStmt.setInt(4, product.getPrice());
		pStmt.setString(5, product.getFileName());
		
		pStmt.executeUpdate();
		
		con.close();
		
	}
	
	public Map<String, Object> getProductList(Search search) throws Exception{
		System.out.println("ProductDAO Map<String,Object> getProductList(Search search) 시작==================>");
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		Connection con = DBUtil.getConnection();
		
		//Original Query 구성
		String sql = "select * from product ";
		
		if(search.getSearchCondition() != null) {
			if(search.getSearchCondition().equals("0") &&  !search.getSearchKeyword().equals("")) {
				sql += " WHERE prod_no =  '"+search.getSearchKeyword()+"' ";
			} else if (search.getSearchCondition().equals("1") &&  !search.getSearchKeyword().equals("")) {
				//sql += " WHERE prod_name = '"+search.getSearchKeyword()+"' ";
				sql += " WHERE prod_name LIKE '%"+search.getSearchKeyword()+"%' ";
			} else if(search.getSearchCondition().equals("2") &&  !search.getSearchKeyword().equals("")) {
				sql += " WHERE price = ' "+search.getSearchKeyword()+" ' ";
			}
			
		}
		
		sql += " order by PROD_NO";
		
		System.out.println("ProductDAO :: Original SQL ::"+ sql);
		
		//==> TotalCount Get
		int totalCount = this.getTotalCount(sql);
		
		System.out.println("ProductDAO :: totalCount ::"+ totalCount);
		
		// CurrentPage 게시물만 받도록 Query 다시 구성
		sql = makeCurrentPageSql(sql, search);
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		
		ResultSet rs = pStmt.executeQuery();
		
		System.out.println(search);
			
		List<Product> list = new ArrayList<Product>();

		while(rs.next()) {
			Product product = new Product();
			product.setProdNo(rs.getInt("Prod_NO"));
			product.setProdName(rs.getString("PROD_NAME"));
			product.setProdDetail(rs.getString("PROD_DETAIL"));
			product.setManuDate(rs.getString("MANUFACTURE_DAY"));
			product.setPrice(rs.getInt("PRICE"));
			product.setFileName(rs.getString("IMAGE_FILE"));
			product.setRegDate(rs.getDate("REG_DATE"));
			
			list.add(product);
		}
		
		//==> totalCount 정보 저장
		map.put("totalCount", new Integer(totalCount));
		//==> currentPage의 게시물 정보를 갖는 List 저장
		map.put("list", list);
			
		rs.close();
		pStmt.close();
		con.close();
		
		return map;
	}//end of getProductList()
	
	public Product findProduct(int productNo) throws Exception {
		System.out.println("ProductDAO Product findProduct(int productNo) 시작==================>");
		Connection con = DBUtil.getConnection();
		
		String sql = "SELECT * FROM product WHERE prod_no= ? ";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setInt(1, productNo);
		
		ResultSet rs = pStmt.executeQuery();
		
		Product product = null;
		while(rs.next()) {
			product = new Product();
			product. setProdNo(rs.getInt("prod_No"));
			product. setProdName(rs.getString("prod_Name"));
			product. setProdDetail(rs.getString("prod_Detail"));
			product. setManuDate(rs.getString("Manufacture_day"));
			product. setPrice(rs.getInt("price"));
			product. setFileName(rs.getString("IMAGE_FILE"));
			product. setRegDate(rs.getDate("REG_DATE"));
		}
		con.close();
		
		return product;
	}
	
	public void updateProduct(Product product) throws Exception{
		System.out.println("ProductDAO void updateProduct(Product product) 시작==================>");
		
		Connection con = DBUtil.getConnection();
		
		String sql = "UPDATE product SET prod_name=?, prod_detail=?, manufacture_day=?, price=?, image_file=? WHERE prod_no = ? ";
		
		PreparedStatement pStmt = con.prepareStatement(sql);
		pStmt.setString(1, product.getProdName());
		pStmt.setString(2, product.getProdDetail());
		pStmt.setString(3, product.getManuDate());
		pStmt.setInt(4, product.getPrice());
		pStmt.setString(5, product.getFileName());
		pStmt.setInt(6, product.getProdNo());
		
		pStmt.executeUpdate();
		
		con.close();
	}
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount) return ==>totalCount : 총 게시물 수
	private int getTotalCount(String sql) throws Exception{
		System.out.println("ProductDAO int getTotalCount(String sql) 시작==================>");
		
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
		System.out.println("ProductDAO String makeCurrentPageSql(String sql, Search search) 시작==================>");
		
		sql = "SELECT * "+
				"FROM( SELECT inner_table.*, ROWNUM AS row_seq "+
				" FROM("+sql+") inner_table "+
				" WHERE ROWNUM <="+search.getCurrentPage() * search.getPageSize()+") "+
				"WHERE row_seq BETWEEN "+((search.getCurrentPage()-1)*search.getPageSize()+1)+" AND "+search.getCurrentPage()*search.getPageSize();
		
		System.out.println("ProductDAO :: make SQL :: " + sql);
		
		return sql;
	}
	
	
	
	
}//end of ProductDAO
