package com.yoloho.spring.util;

import org.springframework.expression.spel.standard.SpelExpressionParser;

/**
 * 操作SPEL
 * 
 * @author wuzl
 * 
 */
public class SpElUtil {
	private static final SpelExpressionParser EL_PARSER = new SpelExpressionParser();

	/**
	 * 简单计算
	 * 
	 * @param spEl
	 * @return
	 */
	public static final Object compute(String spEl) {
		return EL_PARSER.parseExpression(spEl).getValue();
	}

	/**
	 * 简单计算
	 * 
	 * @param spEl
	 * @return
	 */
	public static final Object compute(String spEl, boolean throwException) {
		try {
			return EL_PARSER.parseExpression(spEl).getValue();
		} catch (Exception e) {
			if (throwException) {
				throw new RuntimeException("spEL解析错误:" + e.getMessage());
			}
			return null;
		}
	}

	public static void main(String[] args) {
		System.out.println(compute("'Hello World!'"));
		System.out.println(compute("1+2-3*4/2"));
		System.out.println(compute("'dataSource'+(0/256)"));
		System.out.println(compute("'dataSource'+(255/256)"));
		System.out.println(compute("'dataSource'+(256/256)"));
		System.out.println(compute("'dataSource'+(512/256)"));
		System.out.println(compute("'dataSource'+(5120000000l/2560)"));
	}
}
