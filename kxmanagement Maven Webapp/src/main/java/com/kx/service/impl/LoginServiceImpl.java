package com.kx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kx.dao.UserDao;
import com.kx.service.LoginService;
@Service
public class LoginServiceImpl implements LoginService{
	@Autowired
UserDao userDao;
	@Override
	public boolean login(String username, String password) {
		int i=userDao.getCountByUserNameAndPassword(username, password);
		if(i==1){
			return true;
		}else 
		return false;
	}

}
