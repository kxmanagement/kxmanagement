package email;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.ocean.rawsdk.ApiExecutor;
import com.alibaba.product.param.AlibabaProductGetParam;
import com.alibaba.product.param.AlibabaProductGetResult;

public class Test {
	@org.junit.Test
public void doTest(){
	String token="d9109397-a901-43de-ae47-ae13f5b9d6cf";
		ApiExecutor apiExecutor = new ApiExecutor("2255442","J5nHFq3xNR"); 
AlibabaProductGetParam param=new AlibabaProductGetParam();
param.setProductID(565066093501l);
param.setScene("1688");
param.setWebSite("1688");
AlibabaProductGetResult result=apiExecutor.execute(param, token);
				 JSONObject json=new JSONObject();
			
				
				 System.out.println( json.toJSON(result).toString());
			 
	
}
}
