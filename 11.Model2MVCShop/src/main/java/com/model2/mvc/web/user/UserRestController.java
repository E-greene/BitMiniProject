package com.model2.mvc.web.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.model2.mvc.common.Page;
import com.model2.mvc.common.Search;
import com.model2.mvc.service.domain.User;
import com.model2.mvc.service.user.UserService;


//==> 회원관리 RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	//setter Method 구현 않음
		
	public UserRestController(){
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	@RequestMapping( value="json/getUser/{userId}", method=RequestMethod.GET )
	public User getUser( @PathVariable String userId ) throws Exception{
		
		System.out.println("/user/json/getUser : GET");
		
		//Business Logic
		return userService.getUser(userId);
	}

	@RequestMapping( value="json/login", method=RequestMethod.POST )
	public User login(	@RequestBody User user,
									HttpSession session ) throws Exception{
	
		System.out.println("/user/json/login : POST");
		//Business Logic
		System.out.println("::"+user);
		User dbUser=userService.getUser(user.getUserId());
		
		if( user.getPassword().equals(dbUser.getPassword())){
			session.setAttribute("user", dbUser);
		}
		
		return dbUser;
	}
	
	@RequestMapping( value="json/addUser", method=RequestMethod.POST)
	public User addUser(@RequestBody User user) throws Exception {
		
		System.out.println("/user/json/addUser : POST");
		
		//Business Logic
		System.out.println("=="+user);
		
		userService.addUser(user);
		
		return user;
	}
	
	@RequestMapping( value="json/updateUser", method=RequestMethod.POST)
	public User updateUser(@RequestBody User user, HttpSession session) throws Exception{
		
		System.out.println("/user/json/updateUser : POST");
		
		//Business Logic
		System.out.println(user);
		userService.updateUser(user);
		
		System.out.println(session.getAttribute("user"));
		if(session.getAttribute("user") != null) {
			String sessionId=((User)session.getAttribute("user")).getUserId();
			if(sessionId.equals(user.getUserId())) {
				session.setAttribute("user", user);
			}
		}
		
		
		return user;		
	}
	
	@RequestMapping( value="json/checkDuplication", method=RequestMethod.POST)
	public Map checkDuplication(@RequestBody User user) throws Exception {
		
		System.out.println("/user/json/checkDuplication : POST");
		
		System.out.println("userId"+user.getUserId());
		boolean result = userService.checkDuplication(user.getUserId());
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("result", result);
		map.put("userId", user.getUserId());
		
		return map;
	}
	
	@RequestMapping ( value="json/listUser", method=RequestMethod.POST)
	public Map listUser(@RequestBody Search search) throws Exception {
		
		System.out.println("/user/json/listUser : POST");
		
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		//Business Logic
		System.out.println("search"+search);
		
		Map<String, Object> map = userService.getUserList(search);
		
		Page resultPage = new Page(search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		map.put("list", map.get("list"));
		map.put("resultPage", resultPage);
		map.put("search", search);
		
		return map;
	}
	
	@RequestMapping (value="json/checkId")
	public int idCheck(@RequestParam("id") String userId ) {
		
        System.out.println("==userIdCheck start==");
        System.out.println("전달받은 id:"+userId);
        int cnt = userService.idCheck(userId);
        System.out.println("확인 결과:"+cnt);
        return cnt;
	}
}