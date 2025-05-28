package com.ruoyi.framework.shiro.web.filter.captcha;

import com.google.code.kaptcha.Constants;
import com.ruoyi.auth.web.filter.FilterContants;
import com.ruoyi.auth.web.helper.PathMatcherHelper;
import com.ruoyi.auth.web.helper.ResponseHelper;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.RequestContextFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码过滤器
 *
 * @author ruoyi
 */

@Component
@Slf4j
public class CaptchaValidateFilter extends RequestContextFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CaptchaValidateFilter.class);

    /**
     * 验证码开关
     */
    @Value("${shiro.user.captchaEnabled:true}")
    private boolean captchaEnabled;

    /**
     * 验证码类型
     */
    @Value("${shiro.user.captchaType:math}")
    private String captchaType;

    public void setCaptchaEnabled(boolean captchaEnabled) {
        this.captchaEnabled = captchaEnabled;
    }

    public void setCaptchaType(String captchaType) {
        this.captchaType = captchaType;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String uri = request.getRequestURI();
        String method = request.getMethod();
//
//        boolean anonymous = request.getAttribute(FilterContants.ANONYMOUS_URL) != null;
//        boolean isLogin = !PathMatcherHelper.isMatchedUrl("/relogin", uri)
//                || !PathMatcherHelper.isMatchedUrl("/login", uri);
//        if (anonymous || isLogin) {
//            filterChain.doFilter(request, response);
//            return;
//        }

        boolean isLoginPost = StringUtils.equalsIgnoreCase(method, "post")
                && PathMatcherHelper.isMatchedUrl("/login", uri);
        if (!isLoginPost) {
            filterChain.doFilter(request, response);
            return;
        }

        // 验证码禁用 或不是表单提交 允许访问
        if (!captchaEnabled || !"post".equalsIgnoreCase(request.getMethod())) {
            filterChain.doFilter(request, response);
            return;
        }

        boolean isValidate = validateResponse(request, request.getParameter(FilterContants.CURRENT_VALIDATECODE));
        if (!isValidate) {
            request.setAttribute(FilterContants.CAPTCHA_ERROR, "验证码错误");
            AjaxResult result = AjaxResult.error("验证码错误");
            ResponseHelper.flushBizError(response, result);
            return;
        }

        filterChain.doFilter(request, response);

//        try {
//            Object obj = request.getSession().getAttribute(ContextConstants.AUTH_ACCOUNT_KEY);
//            boolean isAuth = (obj != null);
//            if (!isAuth) {
//                request.getRequestDispatcher("/relogin").forward(request, response);
//                return;
//            }
//
//            Account account = (Account) obj;
//            AppThreadContext.bind(account);
//            filterChain.doFilter(request, response);
//        } finally {
//            AppThreadContext.unbindAccount();
//        }
    }

//    @Override
//    public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception
//    {
//        request.setAttribute(ShiroConstants.CURRENT_ENABLED, captchaEnabled);
//        request.setAttribute(ShiroConstants.CURRENT_TYPE, captchaType);
//        return super.onPreHandle(request, response, mappedValue);
//    }

//    @Override
//    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
//            throws Exception
//    {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        // 验证码禁用 或不是表单提交 允许访问
//        if (captchaEnabled == false || !"post".equals(httpServletRequest.getMethod().toLowerCase()))
//        {
//            return true;
//        }
//        return validateResponse(httpServletRequest, httpServletRequest.getParameter(ShiroConstants.CURRENT_VALIDATECODE));
//    }

    public boolean validateResponse(HttpServletRequest request, String validateCode) {
        Object obj = request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String code = String.valueOf(obj != null ? obj : "");
        // 验证码清除，防止多次使用。
        request.getSession().removeAttribute(Constants.KAPTCHA_SESSION_KEY);
        return !StringUtils.isEmpty(validateCode) && validateCode.equalsIgnoreCase(code);
    }

//    @Override
//    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception
//    {
//        request.setAttribute(ShiroConstants.CURRENT_CAPTCHA, ShiroConstants.CAPTCHA_ERROR);
//        return true;
//    }
}
