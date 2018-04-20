package com.kx.service.impl;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.ocean.rawsdk.ApiExecutor;
import com.alibaba.product.param.AlibabaProductGetListParam;
import com.alibaba.product.param.AlibabaProductGetListResult;
import com.kx.auth.AuthService;
import com.kx.model.RefreshToken;
import com.kx.service.AlibabaApiService;
@Component
/**
 * AlibabaApi接口封装服务
 * @author yuanhaohe
 */
public class AlibabaApiServiceImpl implements AlibabaApiService{
	@Value("${alibaba.client_id}")
private String client_id;
	@Value("${alibaba.client_secret}")
	private String client_secret;
	@Value("${alibaba.redirect_uri}")
	private String redirect_uri;
	/**
	 * @param token 得到的token 
	 * @author yuanhaohe
	 */
	@Override
	public AlibabaProductGetListResult apiExecutor(String token) {
				ApiExecutor apiExecutor = new ApiExecutor(client_id,client_secret); 
				//构造API入参和出参
				//API出入参类命名规则：API名称每个单词首字母大写，并去掉分隔符（“.”），末尾加上Param（或Result），其中Param为入参、Result为出参
				//以创建订单API为例，API名称：alibaba.trade.fastCreateOrder
				AlibabaProductGetListParam param=new AlibabaProductGetListParam();
		param.setWebSite("1688");
		param.setPageNo(1);
		param.setCategoryID((long) -1);
	param.setPageSize(30);
				//调用API并获取返回结果
				 AlibabaProductGetListResult result = apiExecutor.execute(param,token); 
				//对返回结果进行操作
//				if (result.getSuccess()) 
//				{ 
//				    String orderId = result.getResult().getOrderId();
//				    System.out.println(orderId);     
//				}
		// TODO 自动生成的方法存根
		return result;
	}
/**
 * @param 传入回调的code
 * @author yuanhaohe
 * 
 * @return code有效 则返回得到的RefreshToken 否则将返回null
 */
	@Override
	public RefreshToken getTokenByCode(String code) {
Map<String,String> param=new HashMap<String, String>();
param.put("client_id", client_id);
param.put("client_secret", client_secret);
param.put("redirect_uri", redirect_uri);
param.put("code", code);
String text=null;
try{
 text=AuthService.getToken("gw.api.alibaba.com",param, true);
}catch(RuntimeException e){
	
}
if(text!=null){
RefreshToken token=JSONObject.parseObject(text, RefreshToken.class);
updateProperties(token);
return token;
}
return 	null;
	}

	@Override
	public RefreshToken getAccessTokenByRefreshToken(String refreshToken) {
		Map<String,String> param=new HashMap<String, String>();
		param.put("client_id", client_id);
		param.put("client_secret", client_secret);
		param.put("refresh_token", refreshToken);
		param.put("redirect_uri", redirect_uri);
		String text=AuthService.refreshToken("gw.api.alibaba.com", param);
		return 	JSONObject.parseObject(text, RefreshToken.class);
	}
	
	public void updateProperties(RefreshToken refreshToken){
		Properties p=new Properties();
		String file=AlibabaApiService.class.getResource("/").getPath().toString()+"alibaba.properties";

		FileOutputStream out=null;
		try {
			p.setProperty("alibaba.aliId", refreshToken.getAliId());
			p.setProperty("alibaba.resource_owner", refreshToken.getResource_owner());
			p.setProperty("alibaba.memberId", refreshToken.getMemberId());
			p.setProperty("alibaba.expires_in", refreshToken.getExpires_in());
			p.setProperty("alibaba.refresh_token", refreshToken.getRefresh_token());
			p.setProperty("alibaba.refresh_token_timeout", refreshToken.getRefresh_token_timeout());
			p.setProperty("alibaba.access_token", refreshToken.getAccess_token());
			p.setProperty("alibaba.createdate",String.valueOf(System.currentTimeMillis()));
			 out=new FileOutputStream(file);
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd- hh:mm:ss");
	p.store(out, format.format(new Date()));
		} catch (FileNotFoundException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}finally{
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}
	}
	
}
