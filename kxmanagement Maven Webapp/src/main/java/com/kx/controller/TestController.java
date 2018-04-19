package com.kx.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.kx.api.GoogleTranslateApi;
import com.kx.enums.UserConstant;
import com.kx.service.LoginService;

@Controller
public class TestController {
@Autowired
LoginService loginService;
@Autowired
GoogleTranslateApi googleApi;
	@RequestMapping("test.json")
	@ResponseBody
	public String login(String username,String password,HttpSession session){
		JSONObject json=new JSONObject();
		String s="神说: 要有光 ";

		if(loginService.login(username, password)){
			Map<String,String> map=googleApi.translate(GoogleTranslateApi.CHINESE, GoogleTranslateApi.ENGLISH, s);
			json.put("原文", map.get("from"));
			json.put("翻译后", map.get("to"));
		}
		
		return json.toJSONString();
	}
}
