package com.sup2is.allaboutmise.model;

import java.util.Map;

import lombok.Getter;

@Getter
public class JsonResult {
	
	private Results result;
	private Map<String, Object> param;
	private String message;
	
	public JsonResult() {
		this(Results.SUCCESS,null,null);
	}
	
	public JsonResult(Exception e) {
		this(Results.FAILED,e.getMessage(),null);
	}
	
	public JsonResult(Map<String, Object> param) {
		this(Results.SUCCESS,null,param);
	}
	
	public JsonResult(Results result, String message, Map<String, Object> param) {
		this.result = result;
		this.param = param;
		this.message = message;
	}

}


enum Results {
	SUCCESS,FAILED;
}

