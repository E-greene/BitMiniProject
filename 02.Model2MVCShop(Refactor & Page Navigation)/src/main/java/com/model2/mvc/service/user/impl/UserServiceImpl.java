package com.model2.mvc.service.user.impl;

import java.util.Map;

import com.model2.mvc.common.Search;
import com.model2.mvc.service.user.UserService;
import com.model2.mvc.service.user.dao.UserDao;
import com.model2.mvc.service.domain.User;


public class UserServiceImpl implements UserService{
	
	///Field
	private UserDao userDao;
	
	///Constructor
	public UserServiceImpl() {
		userDao=new UserDao();
	}

	///Method
	public void addUser(User user) throws Exception {
		System.out.println(" UserServiceImpl void addUser(User user) 시작=====================>");
		userDao.insertUser(user);
	}

	public User loginUser(User user) throws Exception {
			System.out.println(" UserServiceImpl User loginUser(User user) 시작=====================>");
			
			User dbUser=userDao.findUser(user.getUserId());

			if(! dbUser.getPassword().equals(user.getPassword())){
				throw new Exception("로그인에 실패했습니다.");
			}
			
			return dbUser;
	}

	public User getUser(String userId) throws Exception {
		System.out.println(" UserServiceImpl User getUser(String userId) 시작=====================>");
		return userDao.findUser(userId);
	}

	public Map<String,Object> getUserList(Search search) throws Exception {
		System.out.println(" UserServiceImpl Map<String,Object> getUserList(Search search) 시작=====================>");
		return userDao.getUserList(search);
	}

	public void updateUser(User user) throws Exception {
		System.out.println(" UserServiceImpl void updateUser(User user) 시작=====================>");
		userDao.updateUser(user);
	}

	public boolean checkDuplication(String userId) throws Exception {
		boolean result=true;
		User user=userDao.findUser(userId);
		if(user != null) {
			result=false;
		}
		return result;
	}
}