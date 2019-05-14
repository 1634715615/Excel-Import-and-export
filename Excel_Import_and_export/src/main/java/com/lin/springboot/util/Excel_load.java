/*
 * 标签    ： [[]]
 */
package com.lin.springboot.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * 
 * 创建时间：2018年9月19日下午3:19:50
 * 创建人：A
 * 项目名:AAAAAAAAAAAAAAAA
 * 包名 :com.cn.xfl
 * email：xufl521@163.com
 *
 *
 */
public class Excel_load {
	//默认单元格内容为数字时格式
    private static DecimalFormat df = new DecimalFormat("0");
    // 默认单元格格式化日期字符串
    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    // 默认单元格格式化日期字符串
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    // 默认单元格格式化日期字符串
    private static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH");
    // 默认单元格格式化日期字符串
    private static SimpleDateFormat sdf4 = new SimpleDateFormat("yyyy-MM-dd");
    // 默认单元格格式化日期字符串
    private static SimpleDateFormat sdf5 = new SimpleDateFormat("HH:mm:ss");
    // 默认单元格格式化日期字符串
    private static SimpleDateFormat sdf7 = new SimpleDateFormat("HH:mm:");
    // 默认单元格格式化日期字符串
    private static SimpleDateFormat sdf6 = new SimpleDateFormat("mm:ss");
    // 格式化数字
    private static DecimalFormat nf = new DecimalFormat("0");

    
    /**
     * 
     * @param filePath   文件 路径
     * @param onesheet   第几个sheet(sheet从0开始)
     * @param ClassName  类名(全路径包含包名)
     * @param Ignore_Line  忽略的标题列
     * @return 
     * @return
     * @throws ClassNotFoundException 
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws SecurityException 
     * @throws NoSuchFieldException 
     * @throws IOException 
     * @throws FileNotFoundException 
     */
	 @SuppressWarnings({ "rawtypes", "resource" })
	public static List readExcel2007(final String filePath, final Integer onesheet,String ClassName,int Ignore_Line ) throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, ClassNotFoundException, FileNotFoundException, IOException,Exception
	    {
	        	//新建文件
	        	File file = new  File(filePath);
	        	//新建arrayList数组
	            final ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();
	            //新建arrayList数组
	            ArrayList<Object> colList;
	            //新建excel工作表
	            final XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
	            //新建sheet
	            final XSSFSheet sheet = wb.getSheetAt(onesheet);
	            XSSFRow row;
	            XSSFCell cell;
	            Object value;
	            //循环
	            for (int i = sheet.getFirstRowNum(), rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows(); i++)
	            {
	                row = sheet.getRow(i);
	                colList = new ArrayList<Object>();
	                if (row == null)
	                {
	                    //当读取行为空时
	                    if (i != sheet.getPhysicalNumberOfRows())
	                    {//判断是否是最后一行
	                        rowList.add(colList);
	                    }
	                    continue;
	                    
	                    
	                } else  {
	                    rowCount++;
	                } 
	                
	              if  (row.getRowNum() < Ignore_Line) {
	                     continue;
	                 }
	                for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++)
	                {
	                    cell = row.getCell(j);
	                    if (cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK)
	                    {
	                        //当该单元格为空
	                        if (j != row.getLastCellNum())
	                        {//判断是否是该行中最后一个单元格
	                            colList.add("");
	                        }
	                        continue;
	                    }
//	                    System.err.println(cell.getCellType());
//	                    cell.setCellType(Cell.CELL_TYPE_STRING); 
	                    switch (cell.getCellType())
	                    {
	                        case XSSFCell.CELL_TYPE_STRING:
	                            //System.out.println(i + "行" + j + " 列 is String type");
	                            value = cell.getStringCellValue();
	                            break;
	                        case XSSFCell.CELL_TYPE_NUMERIC:
	                            if ("@".equals(cell.getCellStyle().getDataFormatString()))
	                            { 
	                                value = df.format(cell.getNumericCellValue());
	                            }
	                            else if ("General".equals(cell.getCellStyle().getDataFormatString()))
	                            {
	                                value = nf.format(cell.getNumericCellValue());
	                            }
	                            //判断时间类型开始
	                            else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString()))
	                            { 
	                            	value = sdf4.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
	                            }
	                            else if("m/d/yy h".equals(cell.getCellStyle().getDataFormatString()))
	                            { 
	                            	value = sdf3.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
	                            }
	                            else if("m/d/yy h:mm".equals(cell.getCellStyle().getDataFormatString()))
	                            { 
	                            	value = sdf2.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
	                            }
	                            else if("m/d/yy h:mm:ss".equals(cell.getCellStyle().getDataFormatString()))
	                            { 
	                            	value = sdf1.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
	                            }
	                            
	                            else if("h:mm".equals(cell.getCellStyle().getDataFormatString()))
	                            { 
	                            	value = sdf7.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
	                            }
	                            else if("h:mm:ss".equals(cell.getCellStyle().getDataFormatString()))
	                            { 
	                            	value = sdf5.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
	                            }
	                            else if("mm:ss".equals(cell.getCellStyle().getDataFormatString()))
	                            { 
	                            	value = sdf6.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
	                            }
	                            /////////////////////////////结束
	                            //判断公式
	                            else if("org.apache.poi.xssf.usermodel.XSSFCellStyle@625e388d".equals(cell.getCellStyle()))
	                            { 
	                            	cell.setCellType(Cell.CELL_TYPE_STRING);
	                            	value= cell.getStringCellValue(); 
	                            }
	                            else
	                            {  
	                            	cell.setCellType(Cell.CELL_TYPE_STRING);
	                            	value= cell.getStringCellValue(); 
//	                                value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
	                            }
	                            //System.out.println(i + "行" + j + " 列 is Number type ; DateFormt:" + value.toString());
	                            break;
	                        case XSSFCell.CELL_TYPE_BOOLEAN:
	                            //System.out.println(i + "行" + j + " 列 is Boolean type");
	                            value = Boolean.valueOf(cell.getBooleanCellValue());
	                            break;
	                        case XSSFCell.CELL_TYPE_BLANK:
	                            //  System.out.println(i + "行" + j + " 列 is Blank type");
	                            value = "";
	                            break;
	                        default:
	                            //System.out.println(i + "行" + j + " 列 is default type");
	                        	cell.setCellType(Cell.CELL_TYPE_STRING);
	                            value = cell.toString();
//	                            System.err.println(cell.getCellStyle());
	                    }// end switch
	                    colList.add(value);
	                } //end for j
	                rowList.add(colList);
	            } //end for i
	          return  Util.reflex(ClassName,rowList);
	         
	    }
	 
	 @SuppressWarnings({ "rawtypes", "resource" })
	public static List readExcel2003(final String filePath, final Integer onesheet,String ClassName,int Ignore_Line ) throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, ClassNotFoundException, FileNotFoundException, IOException,Exception
	    {
	        	File file = new File(filePath);
	            final ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();
	            ArrayList<Object> colList;
	            final HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(file));
	            final HSSFSheet sheet = wb.getSheetAt(onesheet);
	            HSSFRow row;
	            HSSFCell cell  ;
	            Object value;
	            for (int i = sheet.getFirstRowNum(), rowCount = 0; rowCount < sheet.getPhysicalNumberOfRows(); i++)
	            {
	                row = sheet.getRow(i);
	                colList = new ArrayList<Object>();
	                if (row == null)
	                {
	                    //当读取行为空时
	                    if (i != sheet.getPhysicalNumberOfRows())
	                    {//判断是否是最后一行
	                        rowList.add(colList);
	                    }
	                    continue;
	                }
	                else
	                {
	                    rowCount++;
	                }
	                //去掉标题
	                if  (row.getRowNum() < Ignore_Line) {
	                     continue;
	                 }
	                for (int j = row.getFirstCellNum(); j <= row.getLastCellNum(); j++)
	                {
	                    cell = row.getCell(j);
	                    if (cell == null || cell.getCellType() == HSSFCell.CELL_TYPE_BLANK)
	                    {
	                        //当该单元格为空
	                        if (j != row.getLastCellNum())
	                        {//判断是否是该行中最后一个单元格
	                            colList.add("");
	                        }
	                        continue;
	                    }
	                    cell.setCellType(Cell.CELL_TYPE_STRING); 
	                    switch (cell.getCellType())
	                    {
                        case XSSFCell.CELL_TYPE_STRING:
                            //System.out.println(i + "行" + j + " 列 is String type");
                            value = cell.getStringCellValue();
                            break;
                        case XSSFCell.CELL_TYPE_NUMERIC:
                            if ("@".equals(cell.getCellStyle().getDataFormatString()))
                            { 
                                value = df.format(cell.getNumericCellValue());
                            }
                            else if ("General".equals(cell.getCellStyle().getDataFormatString()))
                            {
                                value = nf.format(cell.getNumericCellValue());
                            }
                            //判断时间类型开始
                            else if("m/d/yy".equals(cell.getCellStyle().getDataFormatString()))
                            { 
                            	value = sdf4.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                            }
                            else if("m/d/yy h".equals(cell.getCellStyle().getDataFormatString()))
                            { 
                            	value = sdf3.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                            }
                            else if("m/d/yy h:mm".equals(cell.getCellStyle().getDataFormatString()))
                            { 
                            	value = sdf2.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                            }
                            else if("m/d/yy h:mm:ss".equals(cell.getCellStyle().getDataFormatString()))
                            { 
                            	value = sdf1.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                            }
                            
                            else if("h:mm".equals(cell.getCellStyle().getDataFormatString()))
                            { 
                            	value = sdf7.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                            }
                            else if("h:mm:ss".equals(cell.getCellStyle().getDataFormatString()))
                            { 
                            	value = sdf5.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                            }
                            else if("mm:ss".equals(cell.getCellStyle().getDataFormatString()))
                            { 
                            	value = sdf6.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                            }
                            /////////////////////////////结束
                            //判断公式
                            else if("org.apache.poi.xssf.usermodel.XSSFCellStyle@625e388d".equals(cell.getCellStyle()))
                            { 
                            	cell.setCellType(Cell.CELL_TYPE_STRING);
                            	value= cell.getStringCellValue(); 
                            }
                            else
                            {  
                            	cell.setCellType(Cell.CELL_TYPE_STRING);
                            	value= cell.getStringCellValue(); 
//                                value = sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
                            }
                            //System.out.println(i + "行" + j + " 列 is Number type ; DateFormt:" + value.toString());
                            break;
                        case XSSFCell.CELL_TYPE_BOOLEAN:
                            //System.out.println(i + "行" + j + " 列 is Boolean type");
                            value = Boolean.valueOf(cell.getBooleanCellValue());
                            break;
                        case XSSFCell.CELL_TYPE_BLANK:
                            //  System.out.println(i + "行" + j + " 列 is Blank type");
                            value = "";
                            break;
                        default:
                            //System.out.println(i + "行" + j + " 列 is default type");
                        	cell.setCellType(Cell.CELL_TYPE_STRING);
                            value = cell.toString();
//                            System.err.println(cell.getCellStyle());
                    }// end switch
	                    colList.add(value);
	                } //end for j
	                rowList.add(colList);
	            } //end for i
	 
	           return Util.reflex(ClassName,rowList);
	    }
	 
	 
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
	     * @throws IllegalAccessException 抛出异常
	     * @throws ClassNotFoundException 抛出异常
	     * @throws FileNotFoundException 抛出异常
	     * @throws IOException 抛出异常
	     */
	    @SuppressWarnings("rawtypes")
		public static List readExcel(final String filePath, final Integer onesheet,String ClassName,int Ignore_Line) throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, ClassNotFoundException, FileNotFoundException, IOException,Exception
	    {
	    	File file= new File(filePath);
	        if (file.getName().endsWith("xlsx"))
	        {
	            //处理ecxel2007
	           return  readExcel2007(filePath, onesheet, ClassName, Ignore_Line);
	        }
	        else
	        {
	            //处理ecxel2003
	            return readExcel2003(filePath, onesheet, ClassName, Ignore_Line);
	        }
	    }
	  //添加内部类为了封装的时候不让人能反编译
	  class readExcel2003 {}
}
