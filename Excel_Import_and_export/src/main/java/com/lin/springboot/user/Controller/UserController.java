package com.lin.springboot.user.Controller;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.lin.springboot.user.bean.UserBean;
import com.lin.springboot.user.server.UserServer;
import com.lin.springboot.util.Excel_Export_File;
import com.lin.springboot.util.QRCodeUtil;
@Controller
@RestController
@RequestMapping("User")
@SuppressWarnings("unchecked")
public class UserController {
	@Autowired
	private UserServer userServer;

	@RequestMapping("/")
	public ModelAndView login(){
		
		return new ModelAndView ("login");
	}
	
	@RequestMapping("getimg")
	public String  getimg(){
		 try {
			URL url = new URL("http://127.0.0.1/User/toimg");
			  BufferedImage  image = ImageIO.read(url);
			  
			return "扫码出来的数据是："+QRCodeUtil.parseQRCode(QRCodeUtil.bufferedImageToInputStream(image)).getText();
		} catch (Exception e) {
			e.printStackTrace();	
			return "图片错误";
		}
	}
		
 
	@RequestMapping("toimg")
	public void  toimg(HttpServletRequest request, HttpServletResponse response){
		
		String longUrl;
        try {
            longUrl = "https://www.jianshu.com/u/c0aa31157ba5";
            // 转换成短url
           // String shortUrl = ShortNetAddressUtil.generateShortUrl(longUrl);
            // 生成二维码
            BitMatrix qRcodeImg = QRCodeUtil.generateQRCodeStream(longUrl, response);
            // 将二维码输出到页面中
            MatrixToImageWriter.writeToStream(qRcodeImg, "png", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
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