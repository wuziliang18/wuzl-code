package com.wuzl.learn.httpclient;


import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class FirstDemo {
	public static void main(String[] args) {
		CloseableHttpClient httpclient=HttpClients.createDefault();
		HttpGet get=new HttpGet("http://uicapi.yoloho.com/user/getinfo?token=37911854%239cf595508a5f731446af2447df452754");
		try {
			CloseableHttpResponse reponse=httpclient.execute(get);
			System.out.println(reponse);
			reponse.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
