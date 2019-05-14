package com.lin.springboot.user.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lin.springboot.user.bean.UserBean;
import com.lin.springboot.user.server.UserServer;
import com.lin.springboot.util.Excel_Export_File;
@Controller
@RestController
@RequestMapping("User")
@SuppressWarnings("unchecked")
public class UserController {
	@Autowired
	private UserServer userServer;

	
	
	@RequestMapping("excelImp")
	public void ExcelImpl(HttpServletRequest request,HttpServletResponse response,UserBean user){
		@SuppressWarnings("rawtypes")
		List userBean = userServer.getList(user);
		String[] titles = {"id","用户名","密码"};
		Excel_Export_File.exportExcel2007("用户导出", "用户", "用户", titles, userBean, response);
	}
	@RequestMapping("test")
	public String test() {
		return "hello!";
	}
}