package com.zen.community.config;

import com.zen.community.interceptor.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @EnableWebMvc 注解 手动接管 Spring MVC
// 所以拦截器会覆盖所有的请求，导致静态资源无法访问
// @EnableWebMvc @Import(DelegatingWebMvcConfiguration.class) 启用注解会引入 DelegatingWebMvcConfiguration (删除WebMVC配置)
// DelegatingWebMvcConfiguration 类中有这样一个方法
// @Autowired(required = false)
//	public void setConfigurers(List<WebMvcConfigurer> configurers) {
//		if (!CollectionUtils.isEmpty(configurers)) {
//			this.configurers.addWebMvcConfigurers(configurers);
//		}
//	}
// 目的是将所有 WebMvcConfigurer 子类自动注入并启用配置
// 然而在 WebMvcConfigurer 接口的子类 WebMvcAutoConfigurer 中实现了 addResourceHandlers 方法
// public void addResourceHandlers(ResourceHandlerRegistry registry) {
//      if (!this.resourceProperties.isAddMappings()) {
//        logger.debug("Default resource handling disabled");
//      } else {
//        Duration cachePeriod = this.resourceProperties.getCache().getPeriod();
//        CacheControl cacheControl = this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl();
//        if (!registry.hasMappingForPattern("/webjars/**")) {
//          this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{"/webjars/**"}).addResourceLocations(new String[]{"classpath:/META-INF/resources/webjars/"}).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
//        }
//
//        String staticPathPattern = this.mvcProperties.getStaticPathPattern();
//        if (!registry.hasMappingForPattern(staticPathPattern)) {
//          this.customizeResourceHandlerRegistration(registry.addResourceHandler(new String[]{staticPathPattern}).addResourceLocations(WebMvcAutoConfiguration.getResourceLocations(this.resourceProperties.getStaticLocations())).setCachePeriod(this.getSeconds(cachePeriod)).setCacheControl(cacheControl));
//        }
//
//      }
//    }
// 方法配置了资源路径映射 但是因为 @ConditionalOnMissingBean(WebMvcConfigurationSupport.class) 打开了 @EnableWebMvc 后
// @Import(DelegatingWebMvcConfiguration.class) 使自动配置类失效 所以这里需要自己配置路径映射 或者是 取消手动接管 WebMvc
// @EnableWebMvc
/**
 * @author Zen
 * @version 1.0
 * @date 2020/6/10 2:40
 */

// 这种方法 则采用追加的方式将配置类交于 spring 容器接管，并追加启用配置
@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Autowired
  SessionInterceptor sessionInterceptor;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // 拦截所有请求，静态资源也被拦截了
    // 交于 spring 接管后自动注入即可
    registry.addInterceptor(sessionInterceptor).addPathPatterns("/**");
  }
}
