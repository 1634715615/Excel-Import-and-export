package com.lin.springboot.user.server.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lin.springboot.user.bean.UserBean;
import com.lin.springboot.user.dao.UserDao;
import com.lin.springboot.user.server.UserServer;
@Service
public class ImplServer implements UserServer  {
	 @Autowired
	    private UserDao userDao;

	public List<UserBean> getList(UserBean user) {
		return  userDao.getlist(user)	;	
	}
}
