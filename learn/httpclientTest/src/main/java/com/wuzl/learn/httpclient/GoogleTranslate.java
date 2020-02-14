package com.wuzl.learn.httpclient;

import java.io.FileReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import com.alibaba.fastjson.JSONArray;

public class GoogleTranslate {
	protected static final PooledHttpClientAdaptor httpClient = new PooledHttpClientAdaptor();

	public static String translatet(String transalteContente, String fromLanguage, String toLanguage) {
		StringBuilder result = new StringBuilder();
		StringBuilder url = new StringBuilder();
		try {
			url.append("https://translate.google.cn/translate_a/single?").append("client=t&sl=").append(fromLanguage)
					.append("&tl=").append(toLanguage).append("&hl=zh-CN")
					.append("&dt=at&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw")
					.append("&dt=rm&dt=ss&dt=t&ie=UTF-8&oe=UTF-8&otf=2&ssel=0&tsel=0&kc=1&tk=")
					.append(googleToken(transalteContente)).append("&q=")
					.append(URLEncoder.encode(transalteContente, "utf-8"));
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// 获取请求连接
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String resp;
		try {
			resp = httpClient.doGet(url.toString());
			if ("Error Response:caused by-->connect timed out".equals(resp)
					|| "Error Response:caused by-->Read timed out".equals(resp)
					|| "Error Response: HTTP/1.1 503 Service Unavailable".equals(resp)) {
				return "error";
			}
//			resp = resp.replaceAll("\\s+", "");
			JSONArray jsonObject = JSONArray.parseArray(resp);
//			System.out.println(resp);
//			System.out.println(jsonObject.getJSONArray(0).getJSONArray(0).get(0));
			return jsonObject.getJSONArray(0).getJSONArray(0).getString(0);
//			String string = resp.split("]],")[0];
//			String[] split = string.split(",null,null,3],");
//			for (String string2 : split) {
//				if (!string2.contains("[null,null,")) {
//					String string3 = string2.split("\",\"")[0];
//					int start = string3.indexOf("\"") + 1;
//					result.append(string3.substring(start, string3.length()));
//				}
//			}
//			/*
//			 * int start = resp.indexOf("\"")+1; int end = resp.indexOf("\"", start+1);
//			 */
//			/*
//			 * String[] split = resp.split("]],")[0].replace("[[[", "[").split("],"); for
//			 * (String string : split) { String replace = string.split(",")[0].replace("[",
//			 * "").replace("\"", ""); if(replace!=null && !"null".equals(replace)){
//			 * result.append(replace); } }
//			 */
//			return result.toString();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 获取谷歌翻译的tk值
	 * 
	 * @param text
	 * @return
	 */
	private static String googleToken(String text) {
		String tk = "";
		ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");
		try {
//			URL resource = GoogleTranslate.class.getResource("");
			String file = "E:\\wuzlWorkspace\\wuzl-code\\learn\\httpclientTest\\src\\main\\resources\\Google.js";
			FileReader reader = new FileReader(file);
			engine.eval(reader);
			if (engine instanceof Invocable) {
				Invocable invoke = (Invocable) engine;
				tk = String.valueOf(invoke.invokeFunction("token", text));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tk;
	}
}
