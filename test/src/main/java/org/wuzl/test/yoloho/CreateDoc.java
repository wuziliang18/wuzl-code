package org.wuzl.test.yoloho;

import java.util.Map;
import java.util.Set;

public class CreateDoc {
	private final static String FILE_SEPARATOR =System.getProperty("line.separator");  
	public static void main(String[] args) {
		String fields="Long replyId,Long topicId,String content,int isCollect,String emotion";
		String texts="";
		String[] fieldArray=fields.split(",");
		String[] textArray=texts.split(",");
		StringBuilder result=new StringBuilder();
		if(!(textArray.length<=1)){
			
		}else{
			for(String field:fieldArray){
				String[] fieldMsg=field.split("\\s+");
				result.append("<tr>").append(FILE_SEPARATOR);
				result.append("\t<td>"+fieldMsg[1].replaceAll("([A-Z])", "_$1").toLowerCase()+"<br></td>").append(FILE_SEPARATOR);
				result.append("\t<td>"+getType(fieldMsg[0])+"<br></td>").append(FILE_SEPARATOR);
				result.append("\t<td>否<br></td>").append(FILE_SEPARATOR);
				result.append("\t<td><br></td>").append(FILE_SEPARATOR);
				result.append("<tr>").append(FILE_SEPARATOR);
			}
		}
		System.out.println(result);
	}
	private static String getType(String javaType){
		if(javaType.equals("Integer")||javaType.equals("int")||javaType.equals("Long")||javaType.equals("long")){
			return "long";
		}
		return "string";
	}
}
