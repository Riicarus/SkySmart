package com.skyline.skysmart.user.config;

import com.skyline.skysmart.user.filter.JwtFilter;
import com.skyline.skysmart.user.mapper.UserMapper;
import com.skyline.skysmart.user.shiro.JwtRealm;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * [FEATURE INFO]<br/>
 * configuration of shiro
 *
 * @author Skyline
 * @create 2022/6/12 14:43
 * @since 1.0.0
 */
public class ShiroConfig {


    /**
     * create ShiroFilter, filter all request
     * @param defaultWebSecurityManager DefaultWebSecurityManager
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Lazy DefaultWebSecurityManager defaultWebSecurityManager) {
        // create ShiroFilter
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("jwt", new JwtFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        // set DefaultWebSecurityManager for shiroFilter
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        // config restricted resources
        Map<String, String> map = new LinkedHashMap<>();
        map.put("/**", "jwt");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);

        return shiroFilterFactoryBean;
    }

    /**
     * create WebSecurityManager
     * @param realm Realm
     */
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm) {
        // create DefaultWebSecurityManager
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        // set realm for DefaultWebSecurityManager
        defaultWebSecurityManager.setRealm(realm);

        // unable shiro's default session storage
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        defaultWebSecurityManager.setSubjectDAO(subjectDAO);

        return defaultWebSecurityManager;
    }

    /**
     * add annotation support
     */
    @Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // force to user cglib to avoid repeated proxy or some proxy problem
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }

    @Bean
    public Realm getRealm(UserMapper userMapper) {
        JwtRealm jwtRealm = new JwtRealm();

        jwtRealm.setUserMapper(userMapper);

        return jwtRealm;
    }

}
