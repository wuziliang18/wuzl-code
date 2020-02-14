package org.wuzl.test.algorithm;

/**
 * 背包动态规划
 * 
 * @author ziliang.wu
 *
 */
public class TestPackage {
	public static void main(String[] args) {
		for(int i=10;i>=0;i--) {
			System.out.println(i);
		}
		for(int i=10;i>=0;--i) {
			System.out.println(i);
		}
		int[] weight = new int[] { 2, 2, 4, 6, 3 };
		int maxWeight = 16;
		boolean[] memArray = new boolean[maxWeight + 1];
		memArray[0] = true;
		for (int i = 0; i < weight.length; i++) {
			System.out.println( maxWeight - weight[i]); 
			for (int j = maxWeight - weight[i]; j >= 0; --j) {
				System.out.println(j);
				if (memArray[j]) {
					memArray[j + weight[i]] = true;
				}
			}
		}
		for (int i = memArray.length - 1; i >= 0; i--) {
			if (memArray[i]) {
				System.out.println(i);
				return;
			}
		}
	}

}
