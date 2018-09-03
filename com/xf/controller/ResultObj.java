package com.xf.controller;

import java.util.HashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope("prototype")
@Component
public class ResultObj {

	private HashMap<String, Object> result = new HashMap<String,Object>();

	public ResultObj() {
		result.put("status", 0);
	}
	
	public ResultObj(int status, String msg) {
		result.put("status", status);
		result.put("msg", msg);
	}
	
	public HashMap<String, Object> getResult() {
		return result;
	}

	public HashMap<String, Object> put(String key, Object val) {
		result.put(key, val);
		return result;
	}
	
	public HashMap<String, Object> setStatus(int status, String msg) {
		result.put("status", status);
		result.put("msg", msg);
		return result;
	}
	
}
