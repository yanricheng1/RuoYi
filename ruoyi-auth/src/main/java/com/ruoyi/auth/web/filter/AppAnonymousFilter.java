package com.ruoyi.auth.web.filter;

import com.ruoyi.auth.web.helper.PathMatcherHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.RequestContextFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@Component
public class AppAnonymousFilter extends RequestContextFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppAnonymousFilter.class);


    ArrayList<String> anonymousUrlPatterns = new ArrayList<>();

    public AppAnonymousFilter() {
        LOGGER.info("{} init....", AppAnonymousFilter.class.getSimpleName());
    }

    public ArrayList<String> getAnonymousUrlPatterns() {
        return anonymousUrlPatterns;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String uri = request.getRequestURI();

        if (PathMatcherHelper.isMatchedUrl(anonymousUrlPatterns, uri)) {
            request.setAttribute(FilterContants.ANONYMOUS_URL, true);
        }

        filterChain.doFilter(request, response);
    }
}