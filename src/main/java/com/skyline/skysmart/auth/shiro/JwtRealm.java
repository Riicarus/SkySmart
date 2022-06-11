package com.skyline.skysmart.auth.shiro;

import com.skyline.skysmart.auth.util.JwtUtils;
import com.skyline.skysmart.core.exception.Asserts;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.util.StringUtils;

/**
 * [FEATURE INFO]<br/>
 * JWT Authorization module
 *
 * @author Skyline
 * @create 2022/6/10 16:06
 * @since 1.0.0
 */
public class JwtRealm extends AuthorizingRealm {

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String token = principals.toString();
        // account is uid
        String account = JwtUtils.getInfo(token, "account");

        if (!StringUtils.hasLength(account)) {
            return null;
        }

        return authUser(account);
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        // account is uid
        String account = JwtUtils.getInfo(token, "account");

        if (!StringUtils.hasLength(account)) {
            Asserts.fail("authenticate failed: No account value in token!");
        }

        verifyUser(account);

        return new SimpleAuthenticationInfo(token, token, getName());
    }

    private void verifyUser(String account) {

    }

    private AuthorizationInfo authUser(String account) {
        return null;
    }
}
