package org.wuzl.test.unicode;

public class UnicodeToUtf8 {
	public static Long unicodeToUtf8(long unicode){
		String binarys_unicode=Long.toBinaryString(unicode);
		char[] binarys_unicode_array=binarys_unicode.toCharArray();
		String binarys_utf_8_template=getUtf_8BinaryRange(unicode);
		char[] binarys_utf_8_template_array=binarys_utf_8_template.toCharArray();
		StringBuilder result=new StringBuilder();
		int btyeSeat=binarys_unicode.length()-1;//从后开始插入
		for(int i=binarys_utf_8_template_array.length-1;i>=0;i--){
			if(binarys_utf_8_template_array[i]=='x'){
				if(btyeSeat>=0){//还有数据
					result.insert(0, binarys_unicode_array[btyeSeat--]);
				}else{
					result.insert(0, '0');
				}
			}else{
				result.insert(0, binarys_utf_8_template_array[i]);
			}
		}
		return BinaryToHex.binaryToInt(result.toString()); 
	}
	public static String unicodeToUtf8Hex(long unicode){
		return Long.toHexString(unicodeToUtf8(unicode));
	}
	private static String getUtf_8BinaryRange(long unicode){
		if(unicode<0x7F){
			return "0xxxxxxx";
		}else if(unicode<0x07FF){
			return "110xxxxx10xxxxxx";
		}else if(unicode<0xFFFF){
			return "1110xxxx10xxxxxx10xxxxxx";
		}else if(unicode<0x10FFFF){
			return "11110xxx10xxxxxx10xxxxxx10xxxxxx";
		}
		return "";
	}
	public static void main(String[] args) {
		long unicode=0x4E25;
		System.out.println(unicodeToUtf8Hex(unicode));
	}
}
