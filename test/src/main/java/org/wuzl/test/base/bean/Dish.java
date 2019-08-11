package org.wuzl.test.base.bean;

import static java.util.stream.Collectors.toList;

/**
 * 菜肴
 * 
 * @author ziliang.wu
 *
 */
public class Dish {
	private int id;
	private String name;;
	private double price;// 价格
	private int calories;// 卡路里

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getCalories() {
		return calories;
	}

	public void setCalories(int calories) {
		this.calories = calories;
	}

	@Override
	public String toString() {
		return "Dish [id=" + id + ", name=" + name + ", price=" + price + ", calories=" + calories + "]";
	}

}
