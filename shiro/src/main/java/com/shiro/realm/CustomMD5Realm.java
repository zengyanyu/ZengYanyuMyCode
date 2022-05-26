package com.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

//自定义Realm
public class CustomMD5Realm extends AuthorizingRealm {//AuthorizingRealm:具备缓存,认证,授权的功能

	//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) //
			throws AuthenticationException {
		//获取用户输入的凭证(不一定正确的)
		Object credentials = token.getCredentials();
		//获取用户输入的名称(主体)
		String principal = (String) token.getPrincipal();

		//读取数据库中的账号
		String username = "will";
		String password = "77e146b558e3cc9d4ab3862a977ec14b";//666

		//先判断用户账号是否存在
		if (!username.equals(principal)) {
			return null;
		}

		//返回的这个对象,必须包含用户名,和正确的凭证(只提供数据,不做验证匹配)
		//ByteSource.Util.bytes("xmg")加的盐是xmg
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(//
				//ByteSource.Util.bytes("xmg");xmg表示加入的盐
				username, password, ByteSource.Util.bytes("xmg"), this.getName());
		return info;
	}

	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		return null;
	}

}
