package com.skyline.skysmart.user.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * [FEATURE INFO]<br/>
 * JwtToken
 *
 * @author Skyline
 * @create 2022/6/10 16:06
 * @since 1.0.0
 */
public class JwtToken implements AuthenticationToken {

    private final String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}