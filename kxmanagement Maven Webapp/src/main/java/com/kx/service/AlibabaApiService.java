package com.kx.service;

import com.alibaba.product.param.AlibabaProductGetListResult;
import com.kx.model.RefreshToken;

public interface AlibabaApiService {

	
	public AlibabaProductGetListResult apiExecutor(String token) ;
	
	public RefreshToken getTokenByCode(String code);
	
	public RefreshToken getAccessTokenByRefreshToken(String refreshToken);
	
	public void updateProperties(RefreshToken refreshToken);
}
