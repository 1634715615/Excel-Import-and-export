package com.lin.springboot.user.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lin.springboot.user.bean.UserBean;
@Mapper
public interface UserDao {

	List<UserBean> getlist(UserBean user);

	 

	 
}
