package com.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

//�Զ���Realm
public class CustomRealm extends AuthorizingRealm {//AuthorizingRealm:�߱�����,��֤,��Ȩ�Ĺ���

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
		String password = "666";

		//���ж��û��˺��Ƿ����
		if (!username.equals(principal)) {
			return null;
		}

		//���ص��������,��������û���,����ȷ��ƾ֤(ֻ�ṩ����,������֤ƥ��)
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(//
				username, password, this.getName());
		return info;
	}

	//��Ȩ
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

}
