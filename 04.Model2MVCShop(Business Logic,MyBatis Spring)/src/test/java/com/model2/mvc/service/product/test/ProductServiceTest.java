package com.model2.mvc.service.product.test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

// JUnit4 (Test Framework) 과 Spring Framework 통합 Test (Unit Test)
// Spring은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성할 수 있다.
// @RunWith : Meta-data를 통한 wiring(생성,DI) 할 객체 구현체 지정
// @ContextConfiguration : Meta-dtata location 지정
// @Test : 테스트 실행 소스 지정

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:config/commonservice.xml" })
public class ProductServiceTest {

	//==> @RunWith, @ContextConfiguration 이용 Wiring, Test할 instance DI
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	//@Test
	public void testAddProduct() throws Exception {
		
		Product product = new Product();
		
		product.setProdName("testProdName");
		product.setProdDetail("testProdDetail");
		product.setManuDate("20220202");
		product.setPrice(2002);
		product.setFileName("testFileName");
		
		productService.addProduct(product);

//		product = productService.getProduct();
		
		Assert.assertEquals("testProdName", product.getProdName());
		Assert.assertEquals("testProdDetail", product.getProdDetail());
		Assert.assertEquals("20220202", product.getManuDate());
		Assert.assertEquals(2002, product.getPrice());	
		Assert.assertEquals("testFileName", product.getFileName());
		
	}
	
	//@Test
	public void testGetProduct() throws Exception {
		
		Product product = new Product();
		
		product = productService.getProduct(10022);
		
		System.out.println(product);
		
		Assert.assertEquals("testProdName", product.getProdName());
		Assert.assertEquals("testProdDetail", product.getProdDetail());
		Assert.assertEquals("20220202", product.getManuDate());
		Assert.assertEquals(2002, product.getPrice());	
		Assert.assertEquals("testFileName", product.getFileName());
		
		//Assert.assertNotNull(productService.getProduct(10022));
	}
	
	//@Test
	public void testUpdateProduct() throws Exception {
		
		Product product = new Product();
		
		product.setProdName("testProdName2");
		product.setProdDetail("testProdDetail2");
		product.setManuDate("20230303");
		product.setPrice(30000);
		product.setFileName("testFileName2");
		product.setProdNo(10020);
		
		System.out.println(product);
		productService.updateProduct(product);
		
	}
	
	//@Test
	public void testGetProductListAll() throws Exception {
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		Map<String, Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
		Assert.assertEquals(3,list.size());
		
		//System.out.println(list);
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		System.out.println("===========================================");
		
		search.setSearchCondition("0");
		search.setSearchKeyword("");
		map = productService.getProductList(search);
		
		list = (List<Object>)map.get("list");
		System.out.println(totalCount);
	}
	
	//@Test
	public void testGetProductListByProdNo() throws Exception{
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("0");
		search.setSearchKeyword("10001");
		Map<String,Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
		
		System.out.println(list);
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		System.out.println("========================================");
			
	}
	
	//@Test
	public void testGetProductListByProdName() throws Exception {
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		search.setSearchCondition("1");
		search.setSearchKeyword("자전거");
		Map<String,Object> map = productService.getProductList(search);
		
		List<Object> list = (List<Object>)map.get("list");
		
		System.out.println(list);
		
		Integer totalCount = (Integer)map.get("totalCount");
		System.out.println(totalCount);
		
		System.out.println("========================================");
	}
	
	
}
