package com.xf.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xf.entity.Trade;
import com.xf.service.ItxService;

@Scope("prototype")
@Controller
@RequestMapping(value = "/test")
public class TestController { 
	@Autowired
	private ResultObj result;
	
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	@ResponseBody
	public Object get(HttpServletRequest request) {

		return result.setStatus(0, "ok");
	}
}
