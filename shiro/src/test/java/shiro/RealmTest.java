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
		//1.����SecurityManager����
		IniSecurityManagerFactory securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
		//2.ͨ��SccurityManager��������SecurityManagerʵ��
		SecurityManager securityManager = securityManagerFactory.createInstance();
		//3.��SecurityManager���õ����л�����
		SecurityUtils.setSecurityManager(securityManager);
		//4.����һ��Subjectʵ��(����л��ȡ֮ǰ��,���û�л���㴴��һ������)
		Subject subject = SecurityUtils.getSubject();
		//��������
		AuthenticationToken token = new UsernamePasswordToken("will", "666");
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

}
