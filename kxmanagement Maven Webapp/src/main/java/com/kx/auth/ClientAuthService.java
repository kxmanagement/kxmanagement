package com.kx.auth;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.kx.util.CommonUtil;



/**
 * 客户端/WEB端授权服务类，主要用于用户在使用客户端或者Web端类型的app时进行授权
 * 注意：在应用市场售卖的app请勿使用这种方式，请使用托管式授权
 */
public class ClientAuthService extends AuthService{
    
    /**
     * 返回客户端和Web端授权时获取临时令牌code的url
     * @param host 请求的主机名，包括域名和端口
     * @param params 请求参数map，包括client_id,site,redirect_uri以及可选的state、scope和view
     * @param appSecretKey app签名密钥
     * @return 请求的完整url，用户在浏览器中打开此url然后输入自己的用户名密码进行授权，之后就会得到code
     */
    public static String getClientAuthUrl(String host, Map<String, String> params, String appSecretKey){
        String url = "http://" + host + "/auth/authorize.htm";
        if(params == null){
            return null;
        }
        if(params.get("client_id") == null || params.get("site") == null
                || params.get("redirect_uri") == null){
            System.out.println("params is invalid, lack neccessary key!");
            return null;
        }
        String signature = CommonUtil.signatureWithParamsOnly(params, appSecretKey);
        params.put("_aop_signature", signature);
        return CommonUtil.getWholeUrl(url, params);
    }
    
  
}