package com.kx.api;

import org.apache.log4j.chainsaw.Main;

import com.alibaba.ocean.rawsdk.ApiExecutor;
import com.alibaba.product.param.AlibabaProductGetListParam;
import com.alibaba.product.param.AlibabaProductGetListResult;

public class AlibabaApi {
	
	public static void main(String[] args) {
		//设置appkey和密钥(seckey)
		ApiExecutor apiExecutor = new ApiExecutor("2255442","J5nHFq3xNR"); 
		//构造API入参和出参
		//API出入参类命名规则：API名称每个单词首字母大写，并去掉分隔符（“.”），末尾加上Param（或Result），其中Param为入参、Result为出参
		//以创建订单API为例，API名称：alibaba.trade.fastCreateOrder
		AlibabaProductGetListParam param=new AlibabaProductGetListParam();
param.setWebSite("1688");
		param.setPageSize(30);
		//调用API并获取返回结果
		 AlibabaProductGetListResult result = apiExecutor.execute(param,"accessToken"); 
System.out.println(result.getCount());
		//对返回结果进行操作
//		if (result.getSuccess()) 
//		{ 
//		    String orderId = result.getResult().getOrderId();
//		    System.out.println(orderId);     
//		}
	}
	
}
