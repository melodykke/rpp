package com.wrd.rpp.shiro.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 这是shiro配置类
 * ----------------------
 * 1. 需要配置ShiroFilterFactory : ShiroFilterFactoryBean
 * 2. 配置SecurityManager
 * @author melodykke
 *
 */
@Configuration  //这是shiro配置类
public class ShiroConfiguration {
	
	@Bean //注入： shiroFilter
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){

		/**
		 * 定义shiro filter 工厂类
		 * 1. 定义ShiroFilterFactoryBean
		 * 2. 设置securityManager
		 * 3. 配置拦截器
		 * 4. 返回ShiroFilterFactoryBean
		 **/
		//1. 定义ShiroFilterFactoryBean
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		//2. 设置securityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//3. 配置拦截器: 使用map进行配置,LinkedHashMap是有序的,shiro会根据添加的顺序进行拦截
		//拦截器.
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<String,String>();
		// 配置不会被拦截的链接 顺序判断
		filterChainDefinitionMap.put("/assets/**","anon");
		filterChainDefinitionMap.put("/pages/**","anon");
        filterChainDefinitionMap.put("/loginUI", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/register", "anon");
        filterChainDefinitionMap.put("/user/index", "anon");
		//配置退出 过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		//<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		//<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/**", "authc");
		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl("/loginUI");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/user/index");
/*

		filterChainDefinitionMap.put("/assets/**","anon");
		filterChainDefinitionMap.put("/pages/**","anon");
		filterChainDefinitionMap.put("/user/**","authc");*/

		/*	filterChainMap.put("/userbean/exportTe", "authc,perms[userbean:exportTe]");
		filterChainMap.put("/userbean/exportSt", "authc,perms[userbean:exportSt]");
		filterChainMap.put("/userbean/**", "authc");*/
		//6. 设置默认登陆的url
		shiroFilterFactoryBean.setLoginUrl("/loginUI");
		//7. 设置成功之后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/user/index");
		//8. 设置未授权界面
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");
		//9. 把配置的filterChainMap配置到shiroFilterFactoryBean里
		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		//10. 返回一个shiroFilterFactoryBean
		return shiroFilterFactoryBean;
	}
/****************************************shiro核心 SecurityManager ***********************************************************/
	@Bean //注入： securityManager
	public SecurityManager securityManager(){
		/**
		 * 定义shiro的安全管理器
		 * 用shiro自带Web安全管理器
		 */
		DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
		//设置自定义realm
		securityManager.setRealm(myShiroRealm());
		//设置ehcache缓存管理器
		//securityManager.setCacheManager(ehCacheManager());
		//设置rememberMe cookie
		//securityManager.setRememberMeManager(cookieRememberMeManager());
		return securityManager;
	}
/****************************************加密算法注入自定义realm***********************************************************/
	@Bean //注入自定义realm
	public MyShiroRealm myShiroRealm(){
		System.out.println("ShiroConfiguration.myShiroRealm() initiating...");
		MyShiroRealm myShiroRealm = new MyShiroRealm();
		myShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return myShiroRealm;
	}

	@Bean //注入加密算法
	public HashedCredentialsMatcher hashedCredentialsMatcher(){
		HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName("md5");//加密算法
		hashedCredentialsMatcher.setHashIterations(2);//散列次数
		return hashedCredentialsMatcher;
	}
/****************************************配置controller里面访问url权限的permission检验，用 aop ***********************************************************/
	@Bean //开启shiro aop注解（检查访问链接者的permission）
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
		return authorizationAttributeSourceAdvisor;
	}
/****************************************ehcache***********************************************************/	
	@Bean //注入ehcache
	public EhCacheManager ehCacheManager(){
		EhCacheManager ehCacheManager = new EhCacheManager();
		//配置缓存文件
		ehCacheManager.setCacheManagerConfigFile("classpath:shiro-config/ehcache-shiro.xml");
		
		return ehCacheManager;
	}
 /****************************************cookie***********************************************************/	
	@Bean //配置cookie对象
	public SimpleCookie rememberMeCookie(){
		//new一个SimpleCookie，名称为前端checkbox的name属性值（='rememberMe'）
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		//可选；配置cookie的生效时间。单位时秒，下面时1天；
		simpleCookie.setMaxAge(24*60*60);
		return simpleCookie;
	}
	
	@Bean //cookie管理器
	public CookieRememberMeManager cookieRememberMeManager(){
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		return cookieRememberMeManager;
	}
}
