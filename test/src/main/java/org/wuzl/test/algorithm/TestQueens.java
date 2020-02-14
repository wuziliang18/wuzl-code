package org.wuzl.test.algorithm;

/**
 * 8皇后
 * 
 * @author ziliang.wu
 *
 */
public class TestQueens {
	int[] result = new int[8];

	public void cal8Queens(int row) {
		if (row == 8) {
			// 打印
			print();
			try {
				Thread.sleep(200l);
			} catch (InterruptedException e) {
			}
			return;
		}
		for (int column = 0; column < 8; column++) {
			if (isOk(row, column)) {
				result[row] = column;
				cal8Queens(row + 1);
			}
		}
	}

	private boolean isOk(int row, int column) {
		if (row == 0) {
			return true;
		}
		int leftup = column - 1, rightup = column + 1;
		for (int i = row - 1; i >= 0; i--) {
			if (result[i] == column) {
				return false;
			}
			if (leftup >= 0 && result[i] == leftup) {
				return false;
			}
			if (rightup <= 8 && result[i] == rightup) {
				return false;
			}
			leftup--;
			rightup++;
		}
		return true;
	}

	private void print() {
		System.out.println("###############");
		for (int row = 0; row < 8; row++) {
			for (int column = 0; column < 8; column++) {
				if (result[row] == column) {
					System.out.print("Q");
				} else {
					System.out.print("*");
				}
			}
			System.out.println("");
		}
		System.out.println("***********");

	}

	public static void main(String[] args) {
		new TestQueens().cal8Queens(0);
	}
}
