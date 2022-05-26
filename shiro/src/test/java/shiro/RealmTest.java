package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

public class RealmTest {

	@Test
	public void test() throws Exception {
		//1.构建SecurityManager工厂
		IniSecurityManagerFactory securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		//2.通过SccurityManager工厂创建SecurityManager实例
		SecurityManager securityManager = securityManagerFactory.createInstance();
		//3.将SecurityManager设置到运行环境中
		SecurityUtils.setSecurityManager(securityManager);
		//4.创建一个Subject实例(如果有会获取之前的,如果没有会帮你创建一个主题)
		Subject subject = SecurityUtils.getSubject();
		//创建令牌
		AuthenticationToken token = new UsernamePasswordToken("will", "666");
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

}
