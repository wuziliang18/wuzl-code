package org.wuzl.test.concurrent;

import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.wuzl.test.comm.shop.ShopsFindProduct;

/**
 * 利用CompletableFuture执行并发 重写
 * 
 * @author ziliang.wu
 *
 */
public class TestCompletableFutureFindShop {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ShopsFindProduct completableFutureVersion = new ShopsFindProduct();
        // completableFutureVersion.test();
        String name = "myPhone27S";
        CompletableFuture<Double> future = CompletableFuture.supplyAsync(() -> completableFutureVersion.getPrice(name))
                .thenApply(Integer::parseInt).thenApply(i -> i * 10.0);
        System.out.println(LocalDateTime.now());
        System.out.println(LocalDateTime.now() + "返回结果:" + future.get());
        CompletableFuture.supplyAsync(() -> completableFutureVersion.getPrice(name)).thenApply(Integer::parseInt)
                .thenApply(i -> i * 10.0)
                // 执行成功
                .thenAccept(price -> System.out.println(LocalDateTime.now() + "返回结果:" + price));
        System.out.println(LocalDateTime.now());
        Thread.sleep(5000);
        // List<String> list = IntStream.range(0, 100).mapToObj(Integer::toString).collect(toList());
        // System.out.println(list);
        // Executor executor = Executors.newFixedThreadPool(list.size());

    }

    // static class ShopsFindProductComoleatableFuture extends ShopsFindProduct {
    // private Executor executor;
    //
    // public ShopsFindProductComoleatableFuture() {
    // executor = Executors.newFixedThreadPool(shops.size());
    // }
    //
    // @Override
    // public List<String> findPrices(String product) {
    // //应该是jdk或者eclipse版本bug 需要升级
    // List<CompletableFuture<String>> priceFutures = shops.stream().map(shop -> CompletableFuture.supplyAsync(() ->
    // shop.getPrice(product), executor))
    // .map(futrue -> futrue.thenApply(Quote::parse))
    // .map(future -> future.thenCompose(quote ->
    // CompletableFuture.supplyAsync(
    // () -> Discount.applyDiscount(quote), executor)))
    // .collect(toList());
    // return super.findPrices(product);
    // }
    // }
}
