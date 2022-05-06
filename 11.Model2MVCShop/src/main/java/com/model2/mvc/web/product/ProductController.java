package com.model2.mvc.web.product;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.Product;
import com.model2.mvc.service.product.ProductService;

@Controller
@RequestMapping("/product/*")
public class ProductController {
	
	///Field
	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;
	
	
	public ProductController() {
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties, classpath:config/commonservice.xml 참조할 것
	//==> 아래의 두개를 주석을 풀어 의미를 확인할 것
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	//@RequestMapping("/addProduct.do")
	@RequestMapping(value="addProduct", method=RequestMethod.POST)
	public String addProduct(@ModelAttribute("product") Product product) throws Exception {
		
		System.out.println("/product/addProduct : POST");
		//Business Logic
		String manuDate = product.getManuDate().replace("-","");
		product.setManuDate(manuDate);
		productService.addProduct(product);
		
		return "redirect:/product/addProductView.jsp";
	}
	
	//@RequestMapping("/getProduct.do")
	@RequestMapping(value="getProduct", method=RequestMethod.GET)
	public String getProduct(@RequestParam("prodNo") int prodNo, Model model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		System.out.println("/product/getProduct : GET");
		//Business Logic
		Product product = productService.getProduct(prodNo);
		
		// Model과 View 연결
		model.addAttribute("product", product);
		
//		String history;
//	      
//	      Cookie cookie = null;
//	      
//	      Cookie[] cookies = request.getCookies();
//	      if (cookies!=null && cookies.length > 0) {
//	         for (int i = 0; i < cookies.length; i++) {
//	            cookie = cookies[i];
//	            if (cookie.getName().equals("history")) {
//	               
//	               history = URLDecoder.decode(cookie.getValue(),"euc-kr");
//	               history +=","+product.getProdNo();
//	               System.out.println(history);
//	               cookie = new Cookie("history",URLEncoder.encode(history,"euc-kr"));
//	            }else {
//	               cookie = new Cookie("history", Integer.toString(prodNo));
//	            }
//	            
//	         }
//	      }
//	      cookie.setMaxAge(-1);
//	      response.addCookie(cookie);
		
		return "forward:/product/getProduct.jsp";
		
	}
	
	//@RequestMapping("/updateProductView.do")
	//public String updateProductView(@RequestParam("prodNo") int prodNo, Model model) throws Exception {
	@RequestMapping(value="updateProduct", method=RequestMethod.GET)
	public String updateProduct(@RequestParam("prodNo") int prodNo, Model model) throws Exception {
		
		System.out.println("/product/updateProduct : GET");
		
		
		// Business Logic
		Product product = productService.getProduct(prodNo);
		
		// Model과 View 연결	
		model.addAttribute("product", product);
		
		return "forward:/product/updateProductView.jsp";
	}
	
	//@RequestMapping("/updateProduct.do")
	@RequestMapping(value="updateProduct", method=RequestMethod.POST)
	public String updateProduct(@ModelAttribute("product") Product product, Model model  ) throws Exception {
		
		System.out.println("/product/updateProduct : POST");
		// Business Logic
		String manuDate = product.getManuDate().replace("-","");
		product.setManuDate(manuDate);
		
		productService.updateProduct(product);
		
		return "redirect:/product/getProduct?prodNo="+product.getProdNo();
						
	}
	
	//@RequestMapping("/listProduct.do")
	@RequestMapping("/listProduct")
	public String listProduct(@ModelAttribute("search") Search search, @RequestParam("menu") String menu, Model model, HttpServletRequest request) throws Exception {
		
		System.out.println("/product/listProduct : GET / POST");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String, Object> map = productService.getProductList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		model.addAttribute("menu", menu);
		
		return "forward:/product/listProduct.jsp";
	}
	
	
	
	
}
