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
		//1.����SecurityManager����
		IniSecurityManagerFactory securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");
		//2.ͨ��SccurityManager��������SecurityManagerʵ��
		SecurityManager securityManager = securityManagerFactory.createInstance();
		//3.��SecurityManager���õ����л�����
		SecurityUtils.setSecurityManager(securityManager);
		//4.����һ��Subjectʵ��(����л��ȡ֮ǰ��,���û�л���㴴��һ������)
		Subject subject = SecurityUtils.getSubject();
		//��������
		AuthenticationToken token = new UsernamePasswordToken("zhangsan", "666");
		//5.ִ�е�¼����
		try {
			subject.login(token);
		} catch (Exception e) {
			e.printStackTrace();
		}

		//��ӡ��֤״̬
		boolean authenticated = subject.isAuthenticated();
		System.out.println(authenticated);
		//ע��
		subject.logout();
		authenticated = subject.isAuthenticated();
		System.out.println(authenticated);
	}
	/**
	 �û�������ȷ���쳣��Ϣ:
	 org.apache.shiro.authc.UnknownAccountException: Realm [org.apache.shiro.realm.text.IniRealm@78186a70] was unable to find
	���벻��ȷ���쳣��Ϣ:
	 org.apache.shiro.authc.IncorrectCredentialsException: Submitted credentials for token [org.apache.shiro.authc.UsernamePasswordToken - zhangsan, rememberMe=false] did not match the expected credentials.
	 */

}
