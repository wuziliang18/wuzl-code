package org.wuzl.test.comm.shop;

import java.util.Random;

public class Shop {
	private Random random = new Random();
	private final String name;

	public Shop(String name) {
		super();
		this.name = name;
	}

	/**
	 * 获取价格的时候带着折扣码
	 * 
	 * @param product
	 * @return
	 */
	public String getPrice(String product) {
		double price = calculatePrice(product);
		Discount.Code code = Discount.Code.values()[random.nextInt(Discount.Code.values().length)];
		return String.format("%s:%.2f:%s", name, price, code);
	}

	/**
	 * 模拟一个价格
	 * 
	 * @param product
	 * @return
	 */
	private double calculatePrice(String product) {
		delay();
		return random.nextDouble() * product.charAt(0) + product.charAt(1);
	}

	public static void delay() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}
