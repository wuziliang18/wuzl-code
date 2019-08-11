package org.wuzl.test.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.wuzl.test.base.bean.Dish;

public class DishListUtil {
	public static List<Dish> getDishList(int count) {
		List<Dish> list = new ArrayList<>();
		Random random = new Random();
		for (int i = 0; i < count; i++) {
			Dish dish = new Dish();
			dish.setId(i);
			dish.setName("菜肴" + i);
			dish.setCalories(random.nextInt(1000));
			dish.setPrice(random.nextInt(500));
			list.add(dish);
		}
		return list;
	}
}
