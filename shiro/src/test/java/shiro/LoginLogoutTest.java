package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class LoginLogoutTest {

	//
	@Test
	public void loginLogoutTest() throws Exception {
		//1.构建SecurityManager工厂
		IniSecurityManagerFactory securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//2.通过SccurityManager工厂创建SecurityManager实例
		SecurityManager securityManager = securityManagerFactory.createInstance();
		//3.将SecurityManager设置到运行环境中
		SecurityUtils.setSecurityManager(securityManager);
		//4.创建一个Subject实例(如果有会获取之前的,如果没有会帮你创建一个主题)
		Subject subject = SecurityUtils.getSubject();
		//创建令牌
		AuthenticationToken token = new UsernamePasswordToken("zhangsan", "666");
		//5.执行登录方法
		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//打印认证状态
		boolean authenticated = subject.isAuthenticated();
		System.out.println(authenticated);
		//注销
		subject.logout();
		authenticated = subject.isAuthenticated();
		System.out.println(authenticated);
	}
	/**
	 用户名不正确的异常信息:
	 org.apache.shiro.authc.UnknownAccountException: Realm [org.apache.shiro.realm.text.IniRealm@78186a70] was unable to find
	密码不正确的异常信息:
	 org.apache.shiro.authc.IncorrectCredentialsException: Submitted credentials for token [org.apache.shiro.authc.UsernamePasswordToken - zhangsan, rememberMe=false] did not match the expected credentials.
	 */

}
