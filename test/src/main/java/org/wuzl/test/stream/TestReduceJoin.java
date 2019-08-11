package org.wuzl.test.stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.reducing;

import java.util.List;

import org.wuzl.test.base.DishListUtil;
import org.wuzl.test.base.bean.Dish;

public class TestReduceJoin {
	public static void main(String[] args) {
		List<Dish> dishList = DishListUtil.getDishList(5);
		dishList.stream().forEach(System.out::println);
		System.out.println(dishList.stream().map(Dish::getName).collect(reducing((s1, s2) -> s1 + s2)).get());
		System.out.println(dishList.stream().map(Dish::getName).collect(joining()));
	}

}
