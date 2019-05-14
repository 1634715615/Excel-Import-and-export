package com.lin.springboot.user.server;

import java.util.List;

import com.lin.springboot.user.bean.UserBean;

public interface UserServer {

	List<UserBean> getList(UserBean user);

}
