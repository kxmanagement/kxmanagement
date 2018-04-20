package com.kx.service;

import java.util.Map;

public interface GoogleTranslateService {
	 //英语
	public static final String ENGLISH="en";
	//中文
	public static final String CHINESE="zh-CN";
	//俄语
	public static final String RUSSIAN="ru_RU";
	//葡萄牙语
	public static final String PORTUAUESE="pt-PT";
	//日语
	public static final String JAPANESE="ja-JP";
	//意大利语
	public static final String ITALIAA="it-IT";
	//韩语
	public static final String KOREAN="ko_KR";
	//法语
	public static final String FRENCH="fr-FR";
	//德语
	public static final String GERMAN="de-DE";
//源语言
	public static final String FROM_LANGUAGE="from";
	//翻译后语言
	public static final String TO_LANGUAGE="to";
	public  Map<String, String> translate(String from, String to, String content) ;
}
