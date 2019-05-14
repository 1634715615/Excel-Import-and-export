/*
 * 标签              ： [[poi_excel 导出]]
 */
package com.lin.springboot.util;

/**
 * @author 许富林
 * @date  2018年4月4日15:48:38
 */
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
 

public class Excel_Export_File {
	
	 public class exportExcel{  }
	
	/**
	 * 导出excel 2007
	 */
		/**
		 * @param fileName  文件名	
		 * @param sheetName	Sheet名
		 * @param titleName  一级标题名
		 * @param titles	二级标题名
		 * @param listContent  数据
		 * @param response     response 响应
		 */
		public static void exportExcel2007(String fileName,String sheetName,String titleName,String[] titles,List<Object> listContent, HttpServletResponse response) {
			try {
			OutputStream outputStream = response.getOutputStream();// 取得输出流        
			response.reset();// 清空输出流  
			response.setCharacterEncoding("UTF-8");
			response.setHeader(  "Content-disposition", "attachment;filename="+ new String(fileName.getBytes("GB2312"),"ISO8859-1")+".xlsx");  
			// 设定输出文件头        
//			  response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			  response.setContentType("application/msexcel");
			//1、创建工作簿07
			XSSFWorkbook workbook = new XSSFWorkbook();
			
			//1.1、创建合并单元格对象
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, titles.length-1);//起始行号，结束行号，起始列号，结束列号
			
			//1.2、头标题样式
			XSSFCellStyle style1 = createCellStyle2007(workbook, (short)16);
			
			//1.3、列标题样式
			XSSFCellStyle style2 = createCellStyle2007(workbook, (short)13);
			//2、创建工作表07
			//sheet名
//			XSSFSheet sheet = workbook.createSheet(sheetName);
			Sheet sheet = workbook.createSheet(sheetName);
			 workbook.createSheet();
		//  WritableSheet sheet = workbook.createSheet(Sheetname, 0); 
				workbook.setSheetName(1, "sheet2");
				workbook.setSheetName(1, "sheet3");
			//2.1、加载合并单元格对象07
			sheet.addMergedRegion(cellRangeAddress);
			
			//设置默认列宽
			sheet.setDefaultColumnWidth(25);
			
			//3、创建行
			//3.1、创建头标题行；并且设置头标题
			Row row1 = sheet.createRow(0);
			Cell cell1 = row1.createCell(0);
			//加载单元格样式
			cell1.setCellStyle(style1);
			//合并之后的标题名
			cell1.setCellValue(titleName);
			
			//3.2、创建列标题行；并且设置列标题
			Row  row2 = sheet.createRow(1);
			for(int i = 0; i < titles.length; i++){
				//XSSFCell cell2 = row2.createCell(i);
				Cell cell2 = row2.createCell(i);
				//加载单元格样式
				cell2.setCellStyle(style2);
				cell2.setCellValue(titles[i]);
			}
			/********************************开始******************************************/
			 Field[] fields=null;  
			   int i=0;  
			   //int j=0;  
			   for(Object obj:listContent){  
			       fields=obj.getClass().getDeclaredFields();  
			       Row row = sheet.createRow(i+2);
			      // j++;
			       for(int aa = 0 ;aa<fields.length;aa++) {
			       }
			       int j=0;  
			       for(Field v:fields){  
			           v.setAccessible(true);  
			           Object va=v.get(obj);  
			           if(va==null){  
			               va="";  
			           } else if( v.toString().substring(v.toString().lastIndexOf(".")+1).equalsIgnoreCase("serialVersionUID") ) {
			        	   va ="";
			           }
						if(va==null||("").equalsIgnoreCase(va.toString())){
							row.createCell(j).setCellValue(" ");
						}else{
							row.createCell(j).setCellValue(va.toString());	
						}
			           j++;  
			       }      
			       i++;  
			   }  
			  /********************************结束******************************************/
				//5、输出
				workbook.write(outputStream);
				workbook.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * 创建单元格样式
		 * @param workbook 工作簿
		 * @param fontSize 字体大小
		 * @return 单元格样式
		 */
		private static XSSFCellStyle createCellStyle2007(XSSFWorkbook workbook, short fontSize) {
			XSSFCellStyle style = workbook.createCellStyle();
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER);//水平居中
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直居中
			//创建字体
			XSSFFont font = workbook.createFont();
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//加粗字体
			font.setFontHeightInPoints(fontSize);
			//加载字体
			style.setFont(font);
			return style; 
		}
		
		/**
		 * 导出excel 2003
		 */
		/**
		 * @param fileName  文件名	
		 * @param sheetName	Sheet名
		 * @param titleName  一级标题名
		 * @param titles	二级标题名
		 * @param listContent  数据
		 * @param response     response 响应
		 */
		public static void exportExcel2003(String fileName,String sheetName,String titleName,String[] titles,List<Object> listContent, HttpServletResponse response) {
			try {
				OutputStream outputStream = response.getOutputStream();// 取得输出流        
				response.reset();// 清空输出流  
				response.setCharacterEncoding("UTF-8");
				response.setHeader(  "Content-disposition", "attachment;filename="+ new String(fileName.getBytes("GB2312"),"ISO8859-1")+".xls");  
				// 设定输出文件头        
			    response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");// 定义输出类型      
//				response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");// 定义输出类型      
				//1、创建工作簿07
				HSSFWorkbook workbook = new HSSFWorkbook();
				
				//1.1、创建合并单元格对象
				CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, titles.length-1);//起始行号，结束行号，起始列号，结束列号
				
				//1.2、头标题样式
				HSSFCellStyle style1 = createCellStyle2003(workbook, (short)16);
				
				//1.3、列标题样式
				HSSFCellStyle style2 = createCellStyle2003(workbook, (short)13);
				//2、创建工作表07
				//sheet名
//			XSSFSheet sheet = workbook.createSheet(sheetName);
				HSSFSheet sheet = workbook.createSheet(sheetName);
				//  WritableSheet sheet = workbook.createSheet(Sheetname, 0); 
				
				//2.1、加载合并单元格对象07
				sheet.addMergedRegion(cellRangeAddress);
				
				//设置默认列宽
				sheet.setDefaultColumnWidth(25);
				
				//3、创建行
				//3.1、创建头标题行；并且设置头标题
				HSSFRow row1 = sheet.createRow(0);
				HSSFCell cell1 = row1.createCell(0);
				//加载单元格样式
				cell1.setCellStyle(style1);
				//合并之后的标题名
				cell1.setCellValue(titleName);
				
				//3.2、创建列标题行；并且设置列标题
				HSSFRow  row2 = sheet.createRow(1);
				for(int i = 0; i < titles.length; i++){
					//XSSFCell cell2 = row2.createCell(i);
					HSSFCell cell2 = row2.createCell(i);
					//加载单元格样式
					cell2.setCellStyle(style2);
					cell2.setCellValue(titles[i]);
				}
				/********************************开始******************************************/
				Field[] fields=null;  
				int i=0;  
				//int j=0;  
				for(Object obj:listContent){  
					fields=obj.getClass().getDeclaredFields();  
					HSSFRow row = sheet.createRow(i+2);
					// j++;
					for(int aa = 0 ;aa<fields.length;aa++) {
					}
					int j=0;  
					for(Field v:fields){  
						v.setAccessible(true);  
						Object va=v.get(obj);  
						if(va==null){  
							va="";  
						} else if( v.toString().substring(v.toString().lastIndexOf(".")+1).equalsIgnoreCase("serialVersionUID") ) {
							va ="";
						}
						if(va==null||("").equalsIgnoreCase(va.toString())){
							row.createCell(j).setCellValue(" ");
						}else{
							row.createCell(j).setCellValue(va.toString());	
						}
						j++;  
					}      
					i++;  
				}  
				/********************************结束******************************************/
				//5、输出
				workbook.write(outputStream);
				workbook.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * 创建单元格样式
		 * @param workbook 工作簿
		 * @param fontSize 字体大小
		 * @return 单元格样式
		 */
		private static HSSFCellStyle createCellStyle2003(HSSFWorkbook workbook, short fontSize) {
			HSSFCellStyle style = workbook.createCellStyle();
			style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
			style.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直居中
			//创建字体
			HSSFFont font = workbook.createFont();
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);//加粗字体
			font.setFontHeightInPoints(fontSize);
			//加载字体
			style.setFont(font);
			return style; 
		}

}
