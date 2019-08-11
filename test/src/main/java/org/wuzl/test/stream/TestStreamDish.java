package org.wuzl.test.stream;

import java.util.List;

import org.wuzl.test.base.DishListUtil;
import org.wuzl.test.base.bean.Dish;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

public class TestStreamDish {
	public static void main(String[] args) {

		// 筛选热量小于400的菜 按大小排序输出名字
		List<Dish> dishList = DishListUtil.getDishList(10);
		dishList.stream().forEach(System.out::println);
		// dishList.stream().forEach((Dish dish) -> {
		// System.out.println(dish);
		// });
		// dishList.stream().forEach(dish -> System.out.println(dish));
		List<String> result = dishList.stream().filter(d -> d.getCalories() < 400).sorted(comparing(Dish::getCalories))
				.map(Dish::getName).collect(toList());
		System.out.println(result);
	}
}
