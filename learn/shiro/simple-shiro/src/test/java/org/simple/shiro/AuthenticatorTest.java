package org.simple.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;

/**
 * 验证使用AuthenticationStrategy
 * @author wuzl
 *
 */
public class AuthenticatorTest {
	public void login(String iniFilePath){
		Factory<org.apache.shiro.mgt.SecurityManager> factory =
	            new IniSecurityManagerFactory(iniFilePath);
		org.apache.shiro.mgt.SecurityManager  securityManager=factory.getInstance();
		SecurityUtils.setSecurityManager(securityManager);  
		Subject subject=SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken("wu", "123");  
		subject.login(token);
	}
	@Test
	public void testAllSuccessfulStrategyWithSuccess(){
		this.login("classpath:shiro-authenticator-all-success.ini");
		Subject subject=SecurityUtils.getSubject();//绑定到了当前线程所以可以获取到
		PrincipalCollection principalCollection=subject.getPrincipals();//保存的是用户名
		Assert.assertEquals(2, principalCollection.asList().size());  
	}
}
