package org.wuzl.test.wuzl;

public class TestBuyHouses {
	public static void main(String[] args) {
		double total = 155.0;
		double have = total / 2;
		double taxRate = 0.02;
		double creditPoundage = 0.01;
		double buyPoundage = 0.01;

		double creditMoney = total - have;
		double result = have + total * buyPoundage + creditMoney * creditPoundage + total * taxRate;
		System.out.println("首付金额:" + result + "贷款金额:" + creditMoney);
	}
}
