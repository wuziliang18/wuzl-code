package com.yoloho.server.queue.module.email.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.yoloho.server.queue.core.GeneralWorker;
import com.yoloho.server.queue.module.email.bean.EmailBean;
import com.yoloho.server.queue.module.email.constants.MailConfigConstants;
import com.yoloho.server.queue.redis.queue.RedisQueueTemplate;
import com.yoloho.server.queue.redis.queue.constants.RedisQueueConstants;
import com.yoloho.server.queue.redis.queue.listener.QueueListener;
import com.yoloho.server.queue.utils.IniEditor;
import com.yoloho.server.queue.utils.StringUtil;

public class SendEmailFromRedisQueueService implements GeneralWorker{
	private static Log log = LogFactory
			.getLog(SendEmailFromRedisQueueService.class);
	private EmailService emailService;
	private RedisQueueTemplate sendEmailFromRedisQueue;

	public void initConfig() {
		this.initRedisQueue();// 必须在前边因为要给email准备配置数据
		this.initEmail();
	}

	/**
	 * 初始化redis队列
	 */
	private void initRedisQueue() {
		sendEmailFromRedisQueue = new RedisQueueTemplate();
		sendEmailFromRedisQueue.setClazz(EmailBean.class);
		sendEmailFromRedisQueue.setConfFilePath("config/email.conf");
		sendEmailFromRedisQueue
				.setQueueListener(new QueueListener<EmailBean>() {
					@Override
					public void onMessage(EmailBean message, String queueName) {
						try {
							emailService.sendMail(message);
							log.info("发送给【" + message.getTo() + "】邮件成功");
						} catch (Exception e) {
							log.error("发送给【" + message.getTo() + "】邮件失败");
						}
					}

				});
		sendEmailFromRedisQueue.initConfig();
	}

	/**
	 * 初始化邮件
	 */
	private void initEmail() {
		emailService = new EmailService();
		IniEditor ini = sendEmailFromRedisQueue.getIni();
		if (!ini.hasSection(MailConfigConstants.MAIL)) {
			throw new RuntimeException("配置文件中没有mail的配置");
		}
		String host = ini.get(MailConfigConstants.MAIL,
				MailConfigConstants.MAIL_HOST);
		if (StringUtil.isEmpty(host)) {
			throw new RuntimeException("配置文件中没有host的配置");
		}
		emailService.setEmailHost(host);
		String port_str = ini.get(MailConfigConstants.MAIL,
				MailConfigConstants.MAIL_POST);
		if (StringUtil.isEmpty(port_str)) {
			throw new RuntimeException("配置文件中没有port的配置");
		}
		try {
			emailService.setEmailPort(Integer.parseInt(port_str));
		} catch (NumberFormatException e) {
			throw new RuntimeException("配置文件中port的配置不是正确的数字");
		}
		String username = ini.get(MailConfigConstants.MAIL,
				MailConfigConstants.MAIL_USERNAME);
		if (StringUtil.isEmpty(username)) {
			throw new RuntimeException("配置文件中没有username的配置");
		}
		emailService.setUsername(username);
		String password = ini.get(MailConfigConstants.MAIL,
				MailConfigConstants.MAIL_PASSWORD);
		if (StringUtil.isEmpty(password)) {
			throw new RuntimeException("配置文件中没有password的配置");
		}
		emailService.setPassword(password);
	}

	/**
	 * 启动服务
	 */
	public void startSendMailFromRedisQueueService() {
		sendEmailFromRedisQueue.startRedisQueue();
	}

	@Override
	public void hup() {
		if(sendEmailFromRedisQueue!=null){
			sendEmailFromRedisQueue.hup();
		}
	}
}
