/*
 * 标签    ： [[]]
 */
package com.lin.springboot.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 创建时间：2018年9月21日上午10:18:11 
 * 创建人：A 项目名:AAAAAAAAAAAAAAAA 
 * 包名 :com.cn.xfl.util.excel 
 * email：xufl521@163.com
 * 
 */
public class Csv {
	class csv_upload{}

	public static List csv_upload(String className, String filePatn, int Ignore_Line) throws NoSuchFieldException,SecurityException, InstantiationException, IllegalAccessException, ClassNotFoundException, Exception {

		File csv = new File(filePatn); // CSV文件路径
		BufferedReader br = null;
		String encoding = "GBK";
		InputStreamReader read = null;

		read = new InputStreamReader(new FileInputStream(csv), encoding);
		br = new BufferedReader(read);
		ArrayList<Object> colList ;
		String line = "";
		String everyLine = "";
		 
		int hang =0;
			final ArrayList<ArrayList<Object>> rowList = new ArrayList<ArrayList<Object>>();
			while ((line = br.readLine()) != null) // 读取到的内容给line变量
			{
				hang++;
				if (hang > Ignore_Line) {
					 colList = new ArrayList<Object>();
					everyLine = line;
					everyLine = everyLine.replace("(", "");
					everyLine = everyLine.replace(")", "");
					everyLine = everyLine.replace("\"", "");
					everyLine = everyLine.replace("'", "");
					everyLine = everyLine.replace("[", "");
					everyLine = everyLine.replace("]", "");
					everyLine = everyLine.replace("<", "");
					everyLine = everyLine.replace(">", "");
//					everyLine = everyLine.substring(0, everyLine.length() - 1);
//					String a[] = new String[500];

					String[] split = everyLine.split(",");
					for (int i = 0; i < split.length; i++) {
						colList.add(split[i]);
						// a[i]=split[i].toString();
					}
					rowList.add(colList);
				}
			}
			return Util.reflex(className, rowList);
	}
}
