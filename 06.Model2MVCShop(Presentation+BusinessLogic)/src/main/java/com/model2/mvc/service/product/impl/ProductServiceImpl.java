package com.model2.mvc.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.product.ProductDao;
import com.model2.mvc.service.product.ProductService;
import com.model2.mvc.service.domain.Product;

@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService {
	
	///Field
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;
	
	
	public ProductServiceImpl() {
		System.out.println(this.getClass());
	}
	
	
	public void addProduct(Product product) throws Exception {
		System.out.println(" ProductServiceImpl void addProduct(Product product) 시작=====================>");
		productDao.insertProduct(product);
	}

	
	public Product getProduct(int prodNo) throws Exception {
		System.out.println(" ProductServiceImpl Product getProduct(int prodNo) 시작=====================>");
		return productDao.findProduct(prodNo);
	}

	
	public Map<String, Object> getProductList(Search search) throws Exception {
		System.out.println(" ProductServiceImpl Map<String, Object> getProductList(Search search) 시작=====================>");
		List<Product> list = productDao.getProductList(search);
		int totalCount = productDao.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	
	public void updateProduct(Product product) throws Exception {
		System.out.println(" ProductServiceImpl void updateProduct(Product product) 시작=====================>");
		productDao.updateProduct(product);
	}
	
	
}
