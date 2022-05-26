package shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

@SuppressWarnings("all")
public class MD5Test {

	@Test
	public void MD5Test() throws Exception {
		Md5Hash md5Hash = new Md5Hash("666");
		System.out.println(md5Hash);
		//���ε��㷨
		Md5Hash md5Hash_salt = new Md5Hash("666", "xmg");
		System.out.println(md5Hash_salt);
		//ɢ��
		Md5Hash md5Hash_1salt = new Md5Hash("neld", "xmg", 1);
		System.out.println(md5Hash_1salt);
		Md5Hash md5Hash_2salt = new Md5Hash("neld", "xmg", 2);
		System.out.println(md5Hash_2salt);

		SimpleHash hash = new SimpleHash("md5", "neld");
		//�������������Ǳ�ʾ�κ�ɢ�д���
		hash = new SimpleHash("md5", "neld", "xmg", 2);
		System.out.println(hash);
	}

	//�������ƾ֤ƥ�����Ĳ���
	@Test
	public void test() throws Exception {
		//1.����SecurityManager����
		IniSecurityManagerFactory securityManagerFactory = new IniSecurityManagerFactory(
				"classpath:shiro-md5-realm.ini");
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
