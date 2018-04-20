package com.kx.email;

import java.security.GeneralSecurityException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.kx.auth.ClientAuthService;
import com.sun.mail.util.MailSSLSocketFactory;
@Component
/**
 * 邮箱服务
 * @author yuanhaohe
 *
 */
public class EmailService {
@Value("${alibaba.host}")
private String alibaba_host	;//国际交易请用"gw.api.alibaba.com"
@Value("${alibaba.site}")
private String site;//国际交易请用"aliexpress"
@Value("${alibaba.client_id}")
private String client_id;
@Value("${alibaba.client_secret}")
private String appSecret;
@Value("${alibaba.auth.callbackuri}")
private String redirect_uri;
@Value("${email.to}")
private String to;
@Value("${email.from}")
private String from;
@Value("${email.host}")
private String email_host;
@Value("${email.password}")
private String password;

/**
 * @author yuanhaohe
 * @return 返回拼接后的URL
 */
	public String getCodeForClientResult(){
        //若为客户端授权，那么回调地址可以是三种形式，具体可参考文档：
        //(1)urn:ietf:wg:oauth:2.0:oob
        //(2)http://localhost:port
        //(3)http://gw.open.1688.com/auth/authCode.htm（国际交易请用"gw.api.alibaba.com"）
        //若为WEB端授权，那么回调地址应该是app的入口地址
    
//        String state = "test";//用户自定义参数，建议填写
        //测试获取客户端授权的临时令牌code
        Map<String, String> params2 = new HashMap<String, String>();
        params2.put("site", site);
        params2.put("client_id", client_id);
        params2.put("redirect_uri", redirect_uri);
//        params2.put("state", state);
        String getCodeForClientResult = ClientAuthService.getClientAuthUrl(alibaba_host, params2, appSecret);
        System.out.println("请在浏览器中访问如下地址并输入网站用户名密码进行授权: " + getCodeForClientResult);
        return getCodeForClientResult;
	}
	
	/**
	 * 发送邮件
	 * @author yuanhaohe
	 */
	public void doSend(){
        // 获取系统属性
        Properties properties = System.getProperties();

        // 设置邮件服务器
        properties.setProperty("mail.smtp.host", email_host);

        properties.put("mail.smtp.auth", "true");
        MailSSLSocketFactory sf=null; 
		try {
			sf = new MailSSLSocketFactory();
		} catch (GeneralSecurityException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        // 获取默认session对象
        Session session = Session.getDefaultInstance(properties,new Authenticator(){
            public PasswordAuthentication getPasswordAuthentication()
            {
                return new PasswordAuthentication("727869774@qq.com", password); //发件人邮件用户名、密码
            }
        });

        try{
            // 创建默认的 MimeMessage 对象
            MimeMessage message = new MimeMessage(session);

            // Set From: 头部头字段
            message.setFrom(new InternetAddress(from));

            // Set To: 头部头字段
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: 头部头字段
            message.setSubject("科绚电商管理系统-授权请求");

            // 设置消息体
            message.setText("尊敬的用户您好:\n阿里巴巴API权限将在30天内到期，如继续使用请及时授权。若未在规定期限内授权，将影响系统的使用（若遇到问题请及时联系管理员）"
            		+ "。\n请在浏览器中访问如下地址并输入网站用户名密码进行授权:"+this.getCodeForClientResult());

            // 发送消息
            Transport.send(message);
        }catch (MessagingException mex) {
            mex.printStackTrace();
        }
	}
}
