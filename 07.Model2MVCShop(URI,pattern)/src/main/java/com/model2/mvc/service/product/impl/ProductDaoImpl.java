package com.model2.mvc.service.product.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductDao;

@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao {
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	public ProductDaoImpl() {
		System.out.println(this.getClass());
	}
	
	public void insertProduct(Product product) throws Exception{
		System.out.println("=== ProductDaoImpl insertProduct(Product product) 시작====>");
		sqlSession.insert("ProductMapper.addProduct", product);
	}
	
	public Product findProduct(int prodNo) throws Exception {
		System.out.println("=== ProductDaoImpl findProduct(int prodNo) 시작====>");
		return sqlSession.selectOne("ProductMapper.getProduct", prodNo);
	}
	
	public void updateProduct(Product product) throws Exception {
		System.out.println("=== ProductDaoImpl updateProduct(Product product) 시작 ==>");
		sqlSession.update("ProductMapper.updateProduct", product);
	}

	
	public List<Product> getProductList(Search search) throws Exception {
		System.out.println("===ProductDaoImpl getProductList(Search search) 시작==>");
		return sqlSession.selectList("ProductMapper.getProductList", search);
	}
	
	public int getTotalCount(Search search) throws Exception{
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}
	
	

}
