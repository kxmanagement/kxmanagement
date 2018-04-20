package com.kx.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.kx.model.RefreshToken;
import com.kx.service.AlibabaApiService;
import com.kx.service.GoogleTranslateService;
/**
 * 测试TestController
 * @author yuanhaohe
 *
 */
@Controller
public class TestController {

@Autowired
GoogleTranslateService googleApi;

@Autowired
AlibabaApiService alibabaApiService;

	@RequestMapping("test.json")
	@ResponseBody
	public String login(String code,HttpSession session){
		System.out.println(code);
		RefreshToken token=alibabaApiService.getTokenByCode(code);
		alibabaApiService.updateProperties(token);
		JSONObject json=new JSONObject();
		return json.toJSONString();
		
	}
	
	@RequestMapping("token.json")
	public String token(@RequestBody RefreshToken refreshToken){
		System.out.println(refreshToken);

		JSONObject json=new JSONObject();
		return json.toJSONString();
	}
}
