package com.togo.accounting.config;

import java.util.LinkedHashMap;
import java.util.Map;

import com.togo.accounting.shiro.CustomFormAuthenticationFilter;
import com.togo.accounting.shiro.CustomHttpFilter;
import com.togo.accounting.shiro.CustomShiroFilterFactoryBean;
import lombok.val;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;

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
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager,
                                                         CustomHttpFilter customHttpFilter,
                                                         CustomFormAuthenticationFilter customFormAuthenticationFilter) {
        val shiroFilterFactoryBean = new CustomShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("custom",customHttpFilter);
        filters.put("authc",customFormAuthenticationFilter);
        val shiroFactoryDefinitionHashMap = new LinkedHashMap<String,String>();

        //TODO: Consider different HTTP method may need different filter.
        shiroFactoryDefinitionHashMap.put("/v1.0/users/**::POST","custom");
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
