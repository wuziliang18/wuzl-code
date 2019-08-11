package org.family.tools.email;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.cache.ClassTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class ApacheCommonsEmail implements IEmailService{
	private FreeMarkerConfigurer freeMarkerConfigurer;
	private String host;
	private String fromEmail;
	private String fromEmailPassword;
	private String defaultEncoding;
	private int smtpPort;
	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		this.freeMarkerConfigurer = freeMarkerConfigurer;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public void setFromEmail(String fromEmail) {
		this.fromEmail = fromEmail;
	}
	public void setFromEmailPassword(String fromEmailPassword) {
		this.fromEmailPassword = fromEmailPassword;
	}
	public void setDefaultEncoding(String defaultEncoding) {
		this.defaultEncoding = defaultEncoding;
	}
	public void setSmtpPort(int smtpPort) {
		this.smtpPort = smtpPort;
	}
	@Override
	public void sendMail(String toMail, String sub, String text,String authName) {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName(host);
			email.setAuthentication(fromEmail, fromEmailPassword);
			email.setCharset(defaultEncoding);
			email.setSmtpPort(smtpPort);
			email.addTo(toMail);
			email.setFrom(fromEmail, authName);
			email.setSubject(sub);
			email.setHtmlMsg(text);
			email.send();
		} catch (EmailException e) {
			e.printStackTrace();
			throw new RuntimeException("发送邮件失败:"+e.getMessage());
		}
		
	}
	@Override
	public void sendMail(String toMail, String sub, String templatePath,String authName,
			Map<String, Object> dto){
		try {
			HtmlEmail email = new HtmlEmail();
			email.setHostName(host);
			email.setAuthentication(fromEmail, fromEmailPassword);
			email.setCharset(defaultEncoding);
			email.setSmtpPort(smtpPort);
			email.addTo(toMail);
			email.setFrom(fromEmail, authName);
			email.setSubject(sub);
			email.setDebug(true);
			TemplateLoader tl = freeMarkerConfigurer.getConfiguration()
					.getTemplateLoader();
			Configuration conf = freeMarkerConfigurer
					.getConfiguration();
			conf.setTemplateLoader(new ClassTemplateLoader(this
					.getClass(), "/"));
			Template tpl = freeMarkerConfigurer.getConfiguration()
					.getTemplate(templatePath);
			String htmlText = FreeMarkerTemplateUtils
					.processTemplateIntoString(tpl, dto);

			email.setHtmlMsg(htmlText);
			email.send();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("发送邮件失败:"+e.getMessage());
		}
	}
	public static void main(String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] { "app-mail.xml" });
		context.start();
		IEmailService emailService=context.getBean("apacheCommonsEmail",IEmailService.class);
//		emailService.sendMail("wuziliang18@126.com", "ceshiyoujian", "<a  href='http://wpa.qq.com/msgrd?v=3&uin=1795537238&site=qq&menu=yes' target=\"_blank\">	");
		Map<String, Object> dto = new HashMap<String, Object>();
		dto.put("validateMailUrl", "tafautlsadfasfdsafsa2w3e4234xcf");
		dto.put("nick", "admin");
		emailService.sendMail("wuziliang18@126.com", "【大姨吗】邮箱绑定验证", "validateUserMail.ftl","吴子良", dto);
	}
}
