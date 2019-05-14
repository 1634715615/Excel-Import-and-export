/*
 * 标签    ： [[]]
 */
package com.lin.springboot.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

/**
 * 支持excel导入 csv导入 和excel的导出
 * 创建时间：2018年9月21日上午10:06:18
 * 创建人：A
 * 项目名:AAAAAAAAAAAAAAAA
 * 包名 :com.cn.xfl.util.excel
 * email：xufl521@163.com
 */
public class File_util {
    
    /**
     * 
     * @param filePath  文件路径
     * @param onesheet  第几个sheet  (sheet编号从0开始)
     * @param ClassName 类名(全路径 com.cn.xfl.util.Domain.Fy)
     * @param Ignore_Line 忽略的行数(不忽略可以填写0)
     * @return
     * @throws NoSuchFieldException 抛出异常
     * @throws SecurityException    抛出异常
     * @throws InstantiationException 抛出异常
     * @throws IllegalAccessException 抛出异
     * @throws ClassNotFoundException 抛出异常
     * @throws FileNotFoundException 抛出异常
     * @throws IOException 抛出异常
     */
    @SuppressWarnings("rawtypes")
	public static List excel_Upload(final String filePath, final Integer onesheet,String ClassName,int Ignore_Line) throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, ClassNotFoundException, FileNotFoundException, IOException,Exception
    {
    	File file= new File(filePath);
        if (file.getName().endsWith("xlsx"))
        {
            //处理ecxel2007
           return  Excel_load.readExcel2007(filePath, onesheet, ClassName, Ignore_Line);
        }
        else
        {
            //处理ecxel2003
            return Excel_load.readExcel2003(filePath, onesheet, ClassName, Ignore_Line);
        }
    }
    /**
     * 
     * @param fileName  文件名
     * @param sheetName  sheet名
     * @param titleName 大标题名
     * @param titles  标题名
     * @param listContent  数组
     * @param response 响应对象
     */
    public  static  void exportExcel2007(String fileName,String sheetName,String titleName,String[] titles,List<Object> listContent, HttpServletResponse response){
    	Excel_Export_File.exportExcel2007(fileName, sheetName, titleName, titles, listContent, response);
    }
    /**
     * 
     * @param className  类名(全部com.cn.xfl.util.Domain.Fy)
     * @param filePatn   文件路径(绝对路径)
     * @param Ignore_Line	忽略的开始行数(只能从头开始不能中间忽略)
     * @return 
     * @throws NoSuchFieldException
     * @throws SecurityException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws ClassNotFoundException
     * @throws Exception
     */
    public  static  List csv_Upload(String className, String filePatn, int Ignore_Line) throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, ClassNotFoundException, Exception{
    	return	Csv.csv_upload(className, filePatn, Ignore_Line);
    }
    
}
