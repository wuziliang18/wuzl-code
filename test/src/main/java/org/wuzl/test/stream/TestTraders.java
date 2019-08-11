package org.wuzl.test.stream;

import java.util.Arrays;
import java.util.List;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

/**
 * java8实战交易员练习
 * 
 * @author ziliang.wu
 *
 */
public class TestTraders {
	public static void main(String[] args) {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");
		List<Transaction> transactions = Arrays.asList(new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000), new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710), new Transaction(mario, 2012, 700), new Transaction(alan, 2012, 950));
		// 找出2011年的所有交易并按交易额排序（从低到高）
		System.out.println(transactions.stream().filter((transaction) -> transaction.getYear() == 2011)
				.sorted(comparing(Transaction::getValue)).collect(toList()));

		// 交易员都在哪些不同的城市工作过
		System.out.println(transactions.stream().map((trannsation) -> trannsation.getTrader().getCity()).distinct()
				.collect(toList()));
		// 有没有交易员是在米兰工作的
		System.out.println(
				transactions.stream().anyMatch((transaction) -> transaction.getTrader().getCity().equals("Milan")));
		// 打印生活在剑桥的交易员的所有交易额
		System.out.println(
				transactions.stream().filter((transaction) -> transaction.getTrader().getCity().equals("Cambridge"))
						.map(Transaction::getValue).reduce(0, (x, y) -> x + y));
	}
}

class Trader {
	private final String name;
	private final String city;

	public Trader(String n, String c) {
		this.name = n;
		this.city = c;
	}

	public String getName() {
		return name;
	}

	public String getCity() {
		return city;
	}

	public String toString() {
		return "Trader:" + this.name + " in " + this.city;
	}
}

class Transaction {
	private final Trader trader;
	private final int year;
	private final int value;

	public Transaction(Trader trader, int year, int value) {
		this.trader = trader;
		this.year = year;
		this.value = value;
	}

	public Trader getTrader() {
		return this.trader;
	}

	public int getYear() {
		return this.year;
	}

	public int getValue() {
		return this.value;
	}

	public String toString() {
		return "{" + this.trader + ", " + "year: " + this.year + ", " + "value:" + this.value + "}";
	}
}