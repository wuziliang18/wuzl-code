package com.family.tools.conveniently_tools.create_code.support;
/**
 * 输入输出bean
 * @author wuzl
 *
 */
public class PrintBean {
	//保存输出信息
	private  StringBuffer sb=new StringBuffer();
	//换行符号
	private final static String separator =System.getProperty("line.separator");  
	/**
	 * 存入字符串 无任何添加格式
	 * @param input
	 */
	public void in(String input){
		sb.append(input);
	}
	/**
	 * 存入字符串并且换行
	 * @param input
	 */
	public void inAndNewLine(String input){
		sb.append(input+separator);
	}
	/**
	 * 输出结果
	 * @return
	 */
	public String out(){
		return sb.toString();
	}
	/**
	 * 输出结果并清空
	 * @return
	 */
	public String outAndClear(){
		String text =sb.toString();
		sb=new StringBuffer();
		return text;
	}
}

