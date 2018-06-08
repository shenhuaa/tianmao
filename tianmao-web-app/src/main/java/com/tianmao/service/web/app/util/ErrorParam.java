package com.tianmao.service.web.app.util;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;
import java.util.TreeMap;

public class ErrorParam {

	public static final Map<String, String> errorCode = new TreeMap<String, String>();

	public static void putCode(String code, String message) {
		errorCode.put(code, message);
	}

	public static String getMessage(Integer code) {
		return errorCode.get(code+"");
	}

	public static JSONObject buildJSON(Integer code) {
		JSONObject json = new JSONObject();
		json.put("code", code);
		json.put("message", errorCode.get(code+""));
//		json.put("version", CommonCache.getVersion());
		json.put("data", "");
		return json;
	}
	
	public static JSONObject buildJSON(Integer code,String msg) {
		JSONObject json = new JSONObject();
		json.put("code", code+"");
		json.put("message", errorCode.get(code+"")+ msg);
//		json.put("version", CommonCache.getVersion());
		json.put("data", "");
		return json;
	}
}
