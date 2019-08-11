package org.family.tools.email;

import java.util.Map;

public interface IEmailService {
	/**
	 * 发送邮件
	 * 
	 * @param toMail
	 * @param sub
	 * @param text
	 */
	public void sendMail(String toMail, String sub, String authName,String text);

	/**
	 * 发送邮件以模板方式
	 * 
	 * @param toMail
	 * @param sub
	 * @param templatePath目录的路径
	 *            （不包含根目录）
	 * @param dto
	 */
	public void sendMail(String toMail, String sub, String templatePath,String authName,
			Map<String, Object> dto);
}
