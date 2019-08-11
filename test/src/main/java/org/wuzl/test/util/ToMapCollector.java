package org.wuzl.test.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * 结果是map的收集器 保证每个输入的key是唯一的 正确用例对用户信息tomap 错误用例对可重复的key数据tomap(使用自带的groupby)
 * 非线程安全
 * 
 * @author ziliang.wu
 *
 * @param <T>
 * @param <A>
 * @param <R>
 * @param <K>
 */
public class ToMapCollector<T, K> implements Collector<T, Map<K, T>, Map<K, T>> {
	Function<? super T, ? extends K> classifier;

	public ToMapCollector(Function<? super T, ? extends K> classifier) {
		this.classifier = classifier;
	}

	@Override
	public Supplier<Map<K, T>> supplier() {
		return HashMap::new;
	}

	@Override
	public BiConsumer<Map<K, T>, T> accumulator() {
		return (map, item) -> {
			K key = Objects.requireNonNull(classifier.apply(item), "节点不能获取到key");
			map.put(key, item);
		};
	}

	@Override
	public BinaryOperator<Map<K, T>> combiner() {
		return (map1, map2) -> {
			map1.putAll(map2);
			return map1;
		};
	}

	@Override
	public Function<Map<K, T>, Map<K, T>> finisher() {
		return Function.identity();
	}

	@Override
	public Set<java.util.stream.Collector.Characteristics> characteristics() {
		return Collections.emptySet();
	}

}
