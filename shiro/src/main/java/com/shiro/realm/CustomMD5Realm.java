package com.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

//�Զ���Realm
public class CustomMD5Realm extends AuthorizingRealm {//AuthorizingRealm:�߱�����,��֤,��Ȩ�Ĺ���

	//��֤
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) //
			throws AuthenticationException {
		//��ȡ�û������ƾ֤(��һ����ȷ��)
		Object credentials = token.getCredentials();
		//��ȡ�û����������(����)
		String principal = (String) token.getPrincipal();

		//��ȡ���ݿ��е��˺�
		String username = "will";
		String password = "77e146b558e3cc9d4ab3862a977ec14b";//666

		//���ж��û��˺��Ƿ����
		if (!username.equals(principal)) {
			return null;
		}

		//���ص��������,��������û���,����ȷ��ƾ֤(ֻ�ṩ����,������֤ƥ��)
		//ByteSource.Util.bytes("xmg")�ӵ�����xmg
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(//
				//ByteSource.Util.bytes("xmg");xmg��ʾ�������
				username, password, ByteSource.Util.bytes("xmg"), this.getName());
		return info;
	}

	//��Ȩ
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

}
