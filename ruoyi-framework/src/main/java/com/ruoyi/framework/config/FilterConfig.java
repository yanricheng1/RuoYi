package com.ruoyi.framework.config;

import com.ruoyi.auth.web.filter.AppAnonymousFilter;
import com.ruoyi.auth.web.filter.AppSsoFilter;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.xss.XssFilter;
import com.ruoyi.framework.shiro.web.filter.captcha.CaptchaValidateFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.DispatcherType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Filter配置
 *
 * @author ruoyi
 */
@Configuration
@ConditionalOnProperty(value = "xss.enabled", havingValue = "true")
public class FilterConfig {
    @Value("${xss.excludes}")
    private String excludes;

    @Value("${xss.urlPatterns}")
    private String urlPatterns;

    @Autowired
    private AppSsoFilter appSsoFilter;

    @Autowired
    private AppAnonymousFilter appAnonymousFilter;

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Bean
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean xssFilterRegister = new FilterRegistrationBean();
        xssFilterRegister.setDispatcherTypes(DispatcherType.REQUEST);
        xssFilterRegister.setFilter(new XssFilter());
        xssFilterRegister.addUrlPatterns(StringUtils.split(urlPatterns, ","));
        xssFilterRegister.setName("xssFilter");
        xssFilterRegister.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE);
        Map<String, String> initParameters = new HashMap<String, String>();
        initParameters.put("excludes", excludes);
        xssFilterRegister.setInitParameters(initParameters);
        return xssFilterRegister;
    }

    @Bean
    public FilterRegistrationBean appAnonymousFilterRegistration() {
        FilterRegistrationBean appSsoFilterRegister = new FilterRegistrationBean();
        appSsoFilterRegister.setDispatcherTypes(DispatcherType.REQUEST);
        appSsoFilterRegister.setFilter(appAnonymousFilter);
        appSsoFilterRegister.addUrlPatterns("/*");
        ArrayList<String> anonymousUrlPatterns = new ArrayList<>();
        anonymousUrlPatterns.add("/favicon.ico**");
        anonymousUrlPatterns.add("/favicon.ico**");
        anonymousUrlPatterns.add("/ruoyi.png**");
        anonymousUrlPatterns.add("/html/**");
        anonymousUrlPatterns.add("/css/**");
        anonymousUrlPatterns.add("/docs/**");
        anonymousUrlPatterns.add("/fonts/**");
        anonymousUrlPatterns.add("/img/**");
        anonymousUrlPatterns.add("/ajax/**");
        anonymousUrlPatterns.add("/js/**");
        anonymousUrlPatterns.add("/ruoyi/**");
        anonymousUrlPatterns.add("/captcha/captchaImage**");
        // 退出 logout地址，shiro去清除session
        anonymousUrlPatterns.add("/logout");
        // 不需要拦截的访问
        anonymousUrlPatterns.add("/login");
        anonymousUrlPatterns.add("/relogin");
        // 注册相关
        anonymousUrlPatterns.add("/register");
        appAnonymousFilter.getAnonymousUrlPatterns().addAll(anonymousUrlPatterns);
        appSsoFilterRegister.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE + 100);
        return appSsoFilterRegister;
    }

    @Bean
    public FilterRegistrationBean appSsoFilterRegistration() {
        FilterRegistrationBean appSsoFilterRegister = new FilterRegistrationBean();
        appSsoFilterRegister.setDispatcherTypes(DispatcherType.REQUEST);
        appSsoFilterRegister.setFilter(appSsoFilter);
        appSsoFilterRegister.addUrlPatterns("/*");
        appSsoFilterRegister.setName("appSsoFilter");
        appSsoFilterRegister.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE + 200);
        return appSsoFilterRegister;
    }


    @Autowired
    private CaptchaValidateFilter captchaValidateFilter;

    @Bean
    public FilterRegistrationBean captchaValidateFilterRegistration() {
        FilterRegistrationBean appSsoFilterRegister = new FilterRegistrationBean();
        appSsoFilterRegister.setDispatcherTypes(DispatcherType.REQUEST);
        appSsoFilterRegister.setFilter(captchaValidateFilter);
        appSsoFilterRegister.addUrlPatterns("/*");
        appSsoFilterRegister.setName("captchaValidateFilter");
        appSsoFilterRegister.setOrder(FilterRegistrationBean.HIGHEST_PRECEDENCE + 200);
        return appSsoFilterRegister;
    }

}
