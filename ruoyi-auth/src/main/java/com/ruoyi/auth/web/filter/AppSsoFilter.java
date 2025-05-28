package com.ruoyi.auth.web.filter;

import com.ruoyi.common.utils.context.Account;
import com.ruoyi.common.utils.context.AppThreadContext;
import com.ruoyi.common.utils.context.ContextConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.RequestContextFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class AppSsoFilter extends RequestContextFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppSsoFilter.class);

    public AppSsoFilter() {
        LOGGER.info("{} init....", AppSsoFilter.class.getSimpleName());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        boolean anonymous = request.getAttribute(FilterContants.ANONYMOUS_URL) != null;

        String uri = request.getRequestURI();
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("=======>>>> receive app request,anonymous:{} uri：{}", anonymous, uri);
        }

        if (anonymous) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            Object obj = request.getSession().getAttribute(ContextConstants.AUTH_ACCOUNT_KEY);
            boolean isAuth = (obj != null);
            if (!isAuth) {
                request.getRequestDispatcher("/relogin").forward(request, response);
                return;
            }

            Account account = (Account) obj;
            AppThreadContext.bind(account);
            filterChain.doFilter(request, response);
        } finally {
            AppThreadContext.unbindAccount();
        }
    }
}