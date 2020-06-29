package com.yuntongxun.ytx.fast.config.shiro;

import com.google.common.collect.Lists;
import org.apache.shiro.authc.pam.FirstSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    /**
     * shiro过滤器链
     * @param manager
     * @return
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager") SecurityManager manager) {


        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();

        // 添加自己的过滤器并且取名为jwt
        Map<String, Filter> filterMap = new HashMap<>();
        filterMap.put("jwt", new JWTFilter());
        factoryBean.setFilters(filterMap);

        factoryBean.setSecurityManager(manager);
        // 配置登录的url和登录成功的url
        factoryBean.setLoginUrl("/user/login");

        /*
         * 自定义url规则
         *
         * http://shiro.apache.org/web.html#urls-
         */
        final Map<String, String> filterRuleMap = new LinkedHashMap<>();

       
        // test
        filterRuleMap.put("/test/**", "anon");
        // druid
        filterRuleMap.put("/druid/**", "anon");
        // swagger
        filterRuleMap.put("/swagger-ui.html", "anon");
        filterRuleMap.put("/doc.html", "anon");
        filterRuleMap.put("/webjars/**", "anon");
        filterRuleMap.put("/swagger-resources/**", "anon");
        // user 登录、登出
        filterRuleMap.put("/user/login", "anon");
        filterRuleMap.put("/user/logout", "anon");
        // swagger-ui
        filterRuleMap.put("/v2/api-docs","anon");
        //第三方登录
        filterRuleMap.put("/user/third/**","anon");

        // 所有请求通过我们自己的JWT Filter
        filterRuleMap.put("/**", "jwt");



        factoryBean.setFilterChainDefinitionMap(filterRuleMap);
        return factoryBean;

    }

    /**
     * 配置核心安全事务管理器
     * @param authRealm
     * @return
     */
    @Bean(name = "securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm") AuthRealm authRealm,@Qualifier("clientAuthRealm") ClientAuthRealm clientAuthRealm) {
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        // 设置多realm配置
        manager.setAuthenticator(modularRealmAuthenticator());

        /*
         * 关闭shiro自带的session，详情见文档
         * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
         */
        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        manager.setSubjectDAO(subjectDAO);
        List<Realm> realms = Lists.newArrayList();
        realms.add(authRealm);
        realms.add(clientAuthRealm);
        manager.setRealms(realms);
        manager.setCacheManager(redisShiroCacheManage());

        return manager;
    }

    @Bean
    public ModularRealmAuthenticator modularRealmAuthenticator(){
        UserModularRealmAuthenticator modularRealmAuthenticator = new UserModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new FirstSuccessfulStrategy());
        return modularRealmAuthenticator;
    }

    @Bean
    public RedisShiroCacheManager redisShiroCacheManage(){
        return new RedisShiroCacheManager();
    }

    /**
     * 配置自定义的权限登录器
     * @param matcher
     * @return
     */
    @Bean(name = "authRealm")
    public AuthRealm authRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher) {
        AuthRealm authRealm = new AuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        authRealm.setCachingEnabled(true);
        //只缓存 登陆的token
        authRealm.setAuthenticationCachingEnabled(true);
        //不缓存 权限信息 换成在service 层控制缓存
        authRealm.setAuthorizationCachingEnabled(false);
        // 自定义缓存实现 使用redis
        authRealm.setAuthenticationCacheName("authenticationCache");
        authRealm.setAuthorizationCacheName("authorizationCache");
        authRealm.setName("admin");
        return authRealm;
    }

    /**
     * 客户端realm
     * @param matcher
     * @return
     */
    @Bean(name = "clientAuthRealm")
    public ClientAuthRealm clientAuthRealm(@Qualifier("credentialsMatcher") CredentialsMatcher matcher){
        ClientAuthRealm authRealm = new ClientAuthRealm();
        authRealm.setCredentialsMatcher(matcher);
        authRealm.setCachingEnabled(true);
        //只缓存 登陆的token
        authRealm.setAuthenticationCachingEnabled(true);
        //不缓存 权限信息 换成在service 层控制缓存
        authRealm.setAuthorizationCachingEnabled(false);
        // 自定义缓存实现 使用redis
        authRealm.setAuthenticationCacheName("authenticationCache");
        authRealm.setAuthorizationCacheName("authorizationCache");
        authRealm.setName("client");
        return authRealm;
    }

    /**
     * 配置自定义的密码比较器
     * @return
     */
    @Bean(name = "credentialsMatcher")
    public CredentialsMatcher credentialsMatcher() {
        return new CredentialsMatcher();
    }

    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager") SecurityManager manager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(manager);
        return advisor;
    }

    /**
     * 下面的代码是添加注解支持
     */
    @Bean
    @DependsOn("lifecycleBeanPostProcessor")
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        // 强制使用cglib，防止重复代理和可能引起代理出错的问题    https://zhuanlan.zhihu.com/p/29161098
        defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
        return defaultAdvisorAutoProxyCreator;
    }


}