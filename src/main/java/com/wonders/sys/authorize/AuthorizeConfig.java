package com.wonders.sys.authorize;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : zhouyu
 * @date : 2019/9/9
 * 权限拦截配置
 */
@Slf4j
@Configuration
public class AuthorizeConfig {

    @Resource
    private RedisProperties redisProperties;
    @Value("${spring.shiro.database}")
    private int authorizeDatabase;
    @Value("${spring.shiro.session.timeout}")
    private Long timeout;

    private static final String NAME = "Auth-Token";
    private static final String VALUE = "/";

    /**
     * 权限过滤
     * @param securityManager securityManager
     * @return ShiroFilterFactoryBean
     */
    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        log.info("AuthorizeConfig.shirFilter()");
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        // 设置拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //游客，开放权限-swagger
        filterChainDefinitionMap.put("/swagger-ui.html","anon");
        filterChainDefinitionMap.put("/v2/api-docs","anon");
        filterChainDefinitionMap.put("/webjars/springfox-swagger-ui/**","anon");
        filterChainDefinitionMap.put("/swagger-resources/**","anon");

        //游客，开放权限
        filterChainDefinitionMap.put("/baseUser/login", "anon");
        filterChainDefinitionMap.put("/userInfo/wxLogin", "anon");
        filterChainDefinitionMap.put("/static/**", "anon");
        filterChainDefinitionMap.put("/guest/**", "anon");
        filterChainDefinitionMap.put("/userInfo/getAuth", "anon");

        //用户，需要角色权限 “user”
        filterChainDefinitionMap.put("/user/**", "authc");
        //二级管理员，需要角色权限 “manage”
        filterChainDefinitionMap.put("/manage/**", "roles[manage,admin]");
        //管理员，需要角色权限 “admin”
        filterChainDefinitionMap.put("/admin/**", "roles[admin]");

        //其他,登录权限
        filterChainDefinitionMap.put("/**", "anon");

        //配置默认登录界面地址，前后端分离中登录界面跳转应由前端路由控制，后台仅返回json数据
        shiroFilterFactoryBean.setLoginUrl("/baseUser/unAuth");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("/baseUser/success");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/guest/unAuth");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    /**
     * 安全管理中心
     * @return SecurityManager SecurityManager
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        //配置记住我
        securityManager.setRememberMeManager(rememberMeManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(shiroCacheManager());
        return securityManager;
    }

    /**
     * 身份权限认证
     * @return UserRealm UserRealm
     */
    @Bean
    public UserRealm myShiroRealm() {
        UserRealm myShiroRealm = new UserRealm();
        myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        return myShiroRealm;
    }

    /**
    * 凭证匹配器
    * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
    * ）
    * @return HashedCredentialsMatcher
    */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(2);
        return hashedCredentialsMatcher;
    }

    /**
     * cookie对象;会话Cookie模板 ,默认为: JSESSIONID 问题: 与SERVLET容器名冲突,重新定义为sid或rememberMe，自定义
     * @return
     */
    @Bean
    public SimpleCookie rememberMeCookie(){
        //这个参数是cookie的名称，对应前端的checkbox的name = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        simpleCookie.setHttpOnly(false);
        simpleCookie.setPath("/");
        //<!-- 记住我cookie生效时间30天 ,单位秒;-->
        simpleCookie.setMaxAge(2592000);
        return simpleCookie;
    }

    /**
     * cookie管理对象;记住我功能,rememberMe管理器
     * @return
     */
    @Bean
    public CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        //rememberMe cookie加密的密钥 建议每个项目都不一样 默认AES算法 密钥长度(128 256 512 位)
        cookieRememberMeManager.setCipherKey(Base64.decode("4AvVhmFLUs0KTA3Kprsdbg=="));
        return cookieRememberMeManager;
    }

    /**
     * FormAuthenticationFilter 过滤器 过滤记住我
     * @return
     */
    @Bean
    public FormAuthenticationFilter formAuthenticationFilter(){
        FormAuthenticationFilter formAuthenticationFilter = new FormAuthenticationFilter();
        //对应前端的checkbox的name = rememberMe
        formAuthenticationFilter.setRememberMeParam("rememberMe");
        return formAuthenticationFilter;
    }

    /**
     * sessionManager
     * @date 2019/4/8
     * 
     * @return org.apache.shiro.session.mgt.SessionManager
     **/
    @Bean
    public SessionManager sessionManager() {
        SessionManage mySessionManager = new SessionManage();
        mySessionManager.setSessionDAO(redisSessionDAO());
        mySessionManager.setDeleteInvalidSessions(true);
        //全局会话超时时间 默认半小时
        mySessionManager.setGlobalSessionTimeout(timeout>0?timeout:1800000L);
        mySessionManager.setSessionValidationSchedulerEnabled(true);
        mySessionManager.setSessionValidationInterval(300000L);
        mySessionManager.setSessionIdCookieEnabled(true);
        mySessionManager.setSessionIdCookie(simpleCookie());
        //取消URL后面的JSESSIONID
        mySessionManager.setSessionIdUrlRewritingEnabled(false);
        return mySessionManager;
    }

    /**
     * simpleCookie
     * @date 2019/4/8
     *
     * @return org.apache.shiro.web.servlet.SimpleCookie
     **/
    @Bean
    public SimpleCookie simpleCookie() {
        SimpleCookie simpleCookie = new SimpleCookie();
        simpleCookie.setName(NAME);
        simpleCookie.setValue(VALUE);
        //浏览器关闭失效
        simpleCookie.setMaxAge(-1);
        simpleCookie.setHttpOnly(true);
        return simpleCookie;
    }

    /**
     * shiroCacheManager 缓存 redis实现
     * <p>
     * 使用的是shiro-redis开源插件
     *
     * @return RedisCacheManager
     */
    @Bean
    public RedisCacheManager shiroCacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * <p>
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * 配置shiro redisManager
     * <p>
     * 使用的是shiro-redis开源插件
     *
     * @return RedisManager
     */
    private RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setJedisPool(new JedisPool(new JedisPoolConfig(),redisProperties.getHost(),redisProperties.getPort(),redisProperties.getTimeout().getNano(),redisProperties.getPassword(),authorizeDatabase));
        return redisManager;
    }

    /**
     * 开启shiro aop注解支持.
     * 使用代理方式;所以需要开启代码支持;
     *
     * @param securityManager securityManager
     * @return AuthorizationAttributeSourceAdvisor
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 注册全局异常处理
     * @return HandlerExceptionResolver
     */
    @Bean(name = "exceptionHandler")
    public HandlerExceptionResolver handlerExceptionResolver() {
        return new AuthorizeExceptionHandler();
    }
}