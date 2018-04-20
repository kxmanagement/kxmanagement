package com.kx.model;

public class RefreshToken {
private String aliId;
private String resource_owner;
private String memberId;
private String expires_in;
private String refresh_token;
private String refresh_token_timeout;
private String access_token;
public String getAliId() {
	return aliId;
}
public void setAliId(String aliId) {
	this.aliId = aliId;
}
public String getResource_owner() {
	return resource_owner;
}
public void setResource_owner(String resource_owner) {
	this.resource_owner = resource_owner;
}
public String getMemberId() {
	return memberId;
}
public void setMemberId(String memberId) {
	this.memberId = memberId;
}
public String getExpires_in() {
	return expires_in;
}
public void setExpires_in(String expires_in) {
	this.expires_in = expires_in;
}
public String getRefresh_token() {
	return refresh_token;
}
public void setRefresh_token(String refresh_token) {
	this.refresh_token = refresh_token;
}
public String getRefresh_token_timeout() {
	return refresh_token_timeout;
}
public void setRefresh_token_timeout(String refresh_token_timeout) {
	this.refresh_token_timeout = refresh_token_timeout;
}
public String getAccess_token() {
	return access_token;
}
public void setAccess_token(String access_token) {
	this.access_token = access_token;
}
@Override
public String toString() {
	return "RefreshToken [aliId=" + aliId + ", resource_owner=" + resource_owner + ", memberId=" + memberId
			+ ", expires_in=" + expires_in + ", refresh_token=" + refresh_token + ", refresh_token_timeout="
			+ refresh_token_timeout + ", access_token=" + access_token + "]";
}

}
