package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.dao.ProductDAO;

public class ProductServiceImpl implements ProductService {
	
	private ProductDAO productDAO;
	
	public ProductServiceImpl() {
		productDAO = new ProductDAO();
	}
	
	@Override
	public void addProduct(Product product) throws Exception {
		System.out.println(" ProductServiceImpl void addProduct(Product product) 시작=====================>");
		productDAO.insertProduct(product);
	}

	@Override
	public Product getProduct(int productNum) throws Exception {
		System.out.println(" ProductServiceImpl Product getProduct(int productNum) 시작=====================>");
		return productDAO.findProduct(productNum);
	}

	@Override
	public Map<String, Object> getProductList(Search search) throws Exception {
		System.out.println(" ProductServiceImpl Map<String, Object> getProductList(Search search) 시작=====================>");
		return productDAO.getProductList(search);
	}

	@Override
	public void updateProduct(Product product) throws Exception {
		System.out.println(" ProductServiceImpl void updateProduct(Product product) 시작=====================>");
		productDAO.updateProduct(product);
	}
	
	
}
