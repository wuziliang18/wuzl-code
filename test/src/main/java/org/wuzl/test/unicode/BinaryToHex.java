package org.wuzl.test.unicode;

public class BinaryToHex {
	public static long binaryToInt(String binary){
		//去前缀0
		binary=binary.replaceAll("^0+", "");
		long hex=0x0l;
		int bite=0;//标记位数
		byte[] bytes=binary.getBytes();
		for(int i=bytes.length-1;i>=0;i--){
			if(((char)bytes[i])!='0'){//0
				hex+=Math.pow(2, bite);
			}
			bite++;
		}
		return hex;
	}
	public static String binaryToHex(String binary){
		return Long.toHexString(binaryToInt(binary));
	}
	public static void main(String[] args) {
		String binary="10000001";
		System.out.println(BinaryToHex.binaryToHex(binary));
		System.out.println(Long.toBinaryString(0x4E25));
	}
}
