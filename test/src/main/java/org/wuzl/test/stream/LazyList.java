package org.wuzl.test.stream;

import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * 延迟队列
 * 
 * @author ziliang.wu
 *
 */
public class LazyList<T> {
	private final T head;
	private final Supplier<LazyList<T>> tail;

	public LazyList(T head, Supplier<LazyList<T>> tail) {
		super();
		this.head = head;
		this.tail = tail;
	}

	public T getHead() {
		return head;
	}

	public LazyList<T> tail() {
		return tail.get();
	}

	public boolean isEmpty() {
		return false;
	}

	public LazyList<T> filter(Predicate<T> p) {
		System.out.println(p.test(head) + ">---" + head);
		return isEmpty() ? this : p.test(head) ? new LazyList<>(head, () -> {
			System.out.println("filter1:" + head);
			return tail().filter(p);
		}) : tail().filter(p);
	}
}
