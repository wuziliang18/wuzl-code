package org.wuzl.test.concurrent;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import org.wuzl.test.comm.shop.Quote;
import org.wuzl.test.comm.shop.ShopsFindProduct;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

/**
 * 利用CompletableFuture执行并发 重写
 * 
 * @author ziliang.wu
 *
 */
public class TestCompletableFutureFindShop {
	public static void main(String[] args) {
		// ShopsFindProduct completableFutureVersion = new
		// ShopsFindProductComoleatableFuture();
		// completableFutureVersion.test();
		// CompletableFuture<Double> future = CompletableFuture.supplyAsync(()
		// -> "10") .thenApply(Integer::parseInt) .thenApply(i->i*10.0);
		List<String> list = IntStream.range(0, 100).mapToObj(Integer::toString).collect(toList());
		System.out.println(list);
		Executor executor = Executors.newFixedThreadPool(list.size());

	}

	static class ShopsFindProductComoleatableFuture extends ShopsFindProduct {
		private Executor executor;

		public ShopsFindProductComoleatableFuture() {
			executor = Executors.newFixedThreadPool(shops.size());
		}

		@Override
		public List<String> findPrices(String product) {
			//应该是jdk或者eclipse版本bug 需要升级
			List<CompletableFuture<String>> priceFutures = shops.stream().map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice(product), executor))
					.map(futrue -> futrue.thenApply(Quote::parse))
					.map(future -> future.thenCompose(quote ->
					CompletableFuture.supplyAsync(
							() -> Discount.applyDiscount(quote), executor)))
							.collect(toList());
			return super.findPrices(product);
		}
	}
}
