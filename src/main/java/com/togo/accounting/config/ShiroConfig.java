package com.togo.accounting.config;

import java.util.LinkedHashMap;

import lombok.val;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * create by crashLab on 2020/05/14.
 **/
@Configuration
public class ShiroConfig {
    @Bean
    public SecurityManager securityManager(Realm realm) {
        return new DefaultWebSecurityManager(realm);
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        val shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        val shiroFactoryDefinitionHashMap = new LinkedHashMap<String,String>();

        //TODO: Consider different HTTP method may need different filter.
        shiroFactoryDefinitionHashMap.put("/v1.0/users","anon");
        shiroFactoryDefinitionHashMap.put("/v1.0/session","anon");
        shiroFactoryDefinitionHashMap.put("/**","authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(shiroFactoryDefinitionHashMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public HashedCredentialsMatcher matcher() {
        val matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName("SHA-256");
        matcher.setHashIterations(1000);
        matcher.setHashSalted(true);
        matcher.setStoredCredentialsHexEncoded(false);
        return matcher;
    }

}
