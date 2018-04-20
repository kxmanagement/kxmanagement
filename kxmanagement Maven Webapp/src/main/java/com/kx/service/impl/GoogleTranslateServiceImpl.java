package com.kx.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kx.service.GoogleTranslateService;
import com.kx.util.HttpClientUtil;
@Component
public class GoogleTranslateServiceImpl implements GoogleTranslateService{
	
@Value("${google.translate.url}")
	private  String googleApiUrl;
	
	/**
	 * @author yuanhaohe
	 * @param from 原语种缩写
	 * @param to   目的语种缩写
	 * @param content 待翻译内容
	 * @return HashMap<String,String>,key=GoogleTranslateService.FROM_LANGUAGE时，返回的是原文，key=GoogleTranslateService.TO_LANGUAGE时，返回的是翻译后的
	 */
	public  Map<String, String> translate(String from, String to, String content) {
		
		content = content.replace(" ", "%20").replace("\"", "|");
		String param = "client=gtx&dt=t&ie=UTF-8&oe=UTF-8&sl=" + from + "&tl=" + to + "&q=" + content;
		String result = HttpClientUtil.sendPost(googleApiUrl, param);
		StringBuilder source = new StringBuilder();
		StringBuilder objective = new StringBuilder();
		Pattern p = Pattern.compile("\"(.*?)\"");
		// 由于引号内可能存在空格以及其他除了"各种各样的字符，我可以使用排除型字符组[^], 但我首先试一下 .+
		Matcher m = p.matcher(result);
		int i = 0;
		while (m.find()) {
			String find=m.group();
			if(!find.equals("\""+from+"\"")){
			if (i % 2 == 1) {
				source.append(find.replace("\"", "").replace("|", "\""));
				i++;
			} else {
				objective.append(find.replace("\"", "").replace("|", "\""));
				i++;
			}
			}
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("from", source.toString());
		map.put("to", objective.toString());
		return map;

	}

}