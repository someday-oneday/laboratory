package com.codewhy.common.configuration;

import com.codewhy.service.ShiroRelam;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.Map;
@Configuration
public class ShiroConfig {
    @Bean
    @ConditionalOnMissingBean //保证bean对象只有一个
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator defaultAAP = new DefaultAdvisorAutoProxyCreator();
        defaultAAP.setProxyTargetClass(true);
        return defaultAAP;
    }

    //将自己的验证方式加入容器
    @Bean
    public ShiroRelam myShiroRealm() {
        ShiroRelam customRealm = new ShiroRelam();
        return customRealm;
    }

    //权限管理，配置主要是Realm的管理认证
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(myShiroRealm());
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    //cookie管理对象
    @Bean("sessionManager")
    public SessionManager sessionManager(){
        //将我们继承后重写的shiro session 注册
        MySessionManager shiroSession = new MySessionManager();
        //如果后续考虑多tomcat部署应用，可以使用shiro-redis开源插件来做session 的控制，或者nginx 的负载均衡
        shiroSession.setSessionDAO(new EnterpriseCacheSessionDAO());
        //单位为毫秒，600000毫秒为1个小时
        shiroSession.setSessionValidationInterval(600000);
        //3600000 milliseconds = 1 hour
        shiroSession.setGlobalSessionTimeout(600000);
        //是否删除无效的，默认也是开启
        shiroSession.setDeleteInvalidSessions(true);
        return shiroSession;
    }

    //ShiroFilterFactoryBean 处理拦截资源文件问题,设置对应的过滤条件和跳转条件
    //注意：初始化ShiroFilterFactoryBean的时候需要注入：SecurityManager
    //Web应用中,Shiro可控制的Web请求必须经过Shiro主过滤器的拦截
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager) {
        //定义返回对象
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        //必须设置 SecurityManager,Shiro的核心安全接口
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        Map<String, Filter> filters = shiroFilterFactoryBean.getFilters();
        filters.put("user",new MyFilter());
        shiroFilterFactoryBean.setFilters(filters);
        Map<String, String> map = new HashMap<>();
        //配置不登录可以访问的资源，anon 表示资源都可以匿名访问
        map.put("/login", "anon");
        map.put("/swagger-ui.html", "anon");
        map.put("/swagger-resources", "anon");
        map.put("/swagger-resources/configuration/security", "anon");
        map.put("/swagger-resources/configuration/ui", "anon");
        map.put("/v2/api-docs", "anon");
        map.put("/webjars/springfox-swagger-ui/**", "anon");
        //对所有用户认证
        map.put("/**", "user");
        //登录
        shiroFilterFactoryBean.setLoginUrl("/unauth");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }


    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;
    }

}
