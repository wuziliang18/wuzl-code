package org.simple.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.Realm;

public class MyRealm2 implements Realm {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "myrealm2";
	}

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof UsernamePasswordToken;
	}

	@Override
	public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token)
			throws AuthenticationException {
		String userName=(String)token.getPrincipal();
		String password = new String((char[])token.getCredentials()); //得到密码
		if(!"zhang".equals(userName)){
			throw new UnknownAccountException(); //如果用户名错误  
		}
		if(!"123".equals(password)) {  
            throw new IncorrectCredentialsException(); //如果密码错误  
        } 
		return new SimpleAuthenticationInfo(userName, password, getName());  
	}

}
