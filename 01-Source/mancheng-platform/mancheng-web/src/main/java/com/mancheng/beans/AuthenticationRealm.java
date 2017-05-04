package com.mancheng.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.mancheng.entity.User;
import com.mancheng.service.UserService;

/**
 * authentication and authorization
 * 
 */
public class AuthenticationRealm extends AuthorizingRealm {

	@Resource(name = "userServiceImpl")
	private UserService userService;

	/**
	 * authentication
	 * 
	 * @param token
	 * @return
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(org.apache.shiro.authc.AuthenticationToken token) {
		AuthenticationToken authenticationToken = (AuthenticationToken) token;
		String username = authenticationToken.getUsername();
		String password = new String(authenticationToken.getPassword());
		if (username != null && password != null) {
			User user = userService.findUserByUserName(username);
			if (user == null) {
				throw new UnknownAccountException();
			}
			if (user.getStatus() == 1) {
				throw new DisabledAccountException();
			}
			if (!DigestUtils.md5Hex(password).equals(user.getPwd())) {
				userService.doCheckFailureCount(user);
				throw new AuthenticationException();
			}
			if (user.getFailureCount() > 0) {
				user.setFailureCount(0);
				userService.update(user);
			}
			return new SimpleAuthenticationInfo(new Principal(user.getId(), username), password, getName());
		}
		throw new UnknownAccountException();
	}

	/**
	 * authorizationInfo
	 * 
	 * @param principals
	 *            principals
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Principal principal = (Principal) principals.fromRealm(getName()).iterator().next();
		if (principal != null) {
			List<String> authorities = new ArrayList<String>();
			if (authorities != null) {
				SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
				authorizationInfo.addStringPermissions(authorities);
				return authorizationInfo;
			}
		}
		return null;
	}

}
