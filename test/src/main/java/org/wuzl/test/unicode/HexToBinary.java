package org.wuzl.test.unicode;

public class HexToBinary {
	public static String hexToBinary(String hex){
		hex=hex.replace("0x", "");
		int ten=Integer.parseInt(hex,16);
		System.out.println(ten);
		return Integer.toBinaryString(ten);
	}
	public static void main(String[] args) {
		System.out.println(hexToBinary("0x40"));
	}
}
