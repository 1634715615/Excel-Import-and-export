/*
 * 标签    ： [[]]
 */
package com.lin.springboot.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 创建时间：2018年9月21日下午2:28:58
 * 创建人：A
 * 项目名:AAAAAAAAAAAAAAAA
 * 包名 :com.cn.xfl.util.excel
 * email：xufl521@163.com
 *
 *
 */
public class Util {

	  /**
	   * 用反射进行封装
	   * @param ClassName   类名
	   * @param list      ArrayList<ArrayList<Object>>集合
	 * @return 
	   * @throws NoSuchFieldException
	   * @throws SecurityException
	   * @throws InstantiationException
	   * @throws IllegalAccessException
	   * @throws ClassNotFoundException
	   */
	  @SuppressWarnings({ "rawtypes", "unchecked" })
	public static List  reflex(String ClassName,ArrayList<ArrayList<Object>> list) throws NoSuchFieldException, SecurityException, InstantiationException, IllegalAccessException, ClassNotFoundException,Exception {
		String aa[] = new String[500];
		//定义返回的类型
		List  lista= new  ArrayList();
		for (ArrayList<Object> list1 : list) {
//			HashMap<String, Class> fieldHashMap = new HashMap<String, Class>();
			Class cls = Class.forName(ClassName); // 传入类名
			Field[] fieldlist = cls.getDeclaredFields();
			Object obj = cls.newInstance();// 实例化对象
			for (int i = 0; i < fieldlist.length; i++) {
				String fieldKeyName = fieldlist[i].getName();
				Field field = cls.getDeclaredField(fieldKeyName);// 获取字段
				field.setAccessible(true);
				for (int j = 0; j < list1.size(); j++) {
					aa[j] = list1.get(j).toString();
					field.set(obj, aa[i]);
				}
			}
			lista.add(obj);
		}
		//返回封装的数据
		return lista;
	}
	  
	  class reflex{}
}
