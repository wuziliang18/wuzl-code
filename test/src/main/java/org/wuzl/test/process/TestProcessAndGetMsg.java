package org.wuzl.test.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestProcessAndGetMsg {
	public static void main(String[] args) {
		String command="ls";
		try {
			Process process=new ProcessBuilder(command.split(" ")).start();
			BufferedReader results=new BufferedReader(new InputStreamReader(process.getInputStream()));
			BufferedReader errors=new BufferedReader(new InputStreamReader(process.getErrorStream()));
			String msg;
			while((msg=results.readLine())!=null){
				System.out.println(msg);
			}
			while((msg=errors.readLine())!=null){
				System.out.println(msg);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
