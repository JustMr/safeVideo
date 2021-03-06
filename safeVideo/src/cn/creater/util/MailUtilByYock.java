package cn.creater.util;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

public class MailUtilByYock {
	
	//这里是SMTP发送服务器的协议
	public static final String HOST_NAME = "smtp.163.com";
	//登陆邮件服务器的用户名、密码、昵称
	public static final String USERNAME = "justmrliu@163.com";
	public static final String PASSWORD = "Family171213";//lanqiao123
	public static final String NICKNAME = "SafeStudy";

	
	/**
	 * 发送邮件（成功true、失败false）
	 * @param receiveEmail	收件人邮箱
	 * @param receiveNick	收件人昵称（随便取）
	 * @param subject		邮件主题
	 * @param contents		邮件内容
	 * @return
	 */
	public static final boolean sendMail(String receiveEmail,String subject,String contents){
		HtmlEmail email = new HtmlEmail ();
		try {
			//smtp host 
			email.setHostName(HOST_NAME);
			//登陆邮件服务器的用户名和密码
			email.setAuthentication(USERNAME,PASSWORD);
			//接收人
			email.addTo(receiveEmail);
			//发送人
			email.setFrom(USERNAME, NICKNAME);
			//标题
			email.setSubject(subject);
			//邮件内容
			email.setCharset("utf-8");
			email.setHtmlMsg(contents);
			//发送
			email.send();
			return true;
		} catch (EmailException e) {
			e.printStackTrace();
			return false;
		}
		
		
//		MultiPartEmail email = new MultiPartEmail();
//		try {
//			//smtp host 
//			email.setHostName(HOST_NAME);
//			//登陆邮件服务器的用户名和密码
//			email.setAuthentication(USERNAME,PASSWORD);
//			//接收人
//			email.addTo(receiveEmail, receiveNick);
//			//发送人
//			email.setFrom(USERNAME, NICKNAME);
//			//标题
//			email.setSubject(subject);
//			//邮件内容
//			email.setMsg(contents);
//			//发送
//			email.send();
//			return true;
//			
//		} catch (EmailException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return false;
//		}
		
	}
	
	
}
