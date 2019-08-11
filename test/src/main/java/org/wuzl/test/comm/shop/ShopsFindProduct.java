package org.wuzl.test.comm.shop;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.List;

public class ShopsFindProduct {
	protected List<Shop> shops = Arrays.asList(new Shop("BestPrice"), new Shop("LetsSaveBig"), new Shop("MyFavoriteShop"),
			new Shop("BuyItAll"));

	public List<String> findPrices(String product) {
		return shops.stream().map(shop -> shop.getPrice(product)).map(Quote::parse).map(Discount::applyDiscount)
				.collect(toList());
	}

	public void test() {
		// 单线程输出
		long start = System.nanoTime();
		System.out.println(findPrices("myPhone27S"));
		long duration = (System.nanoTime() - start) / 1_000_000;
		System.out.println("Done in " + duration + " msecs");
	}

	public static void main(String[] args) {
		// 输出应该在8s以上
		new ShopsFindProduct().test();
	}
}
