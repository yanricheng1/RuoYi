package com.ruoyi.auth.web.helper;


import org.apache.commons.lang3.StringUtils;
import org.springframework.util.AntPathMatcher;

import java.util.List;

public class PathMatcherHelper {

    private static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    public static boolean isMatchedUrl(String urlMatcher, String url) {
        if (StringUtils.isNotBlank(urlMatcher)) {
            String[] patterns = urlMatcher.split("[, ;\n]");
            for (String pattern : patterns) {
                if (StringUtils.isBlank(pattern)) {
                    continue;
                }
                if (antPathMatcher.match(pattern, url)) {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }

    public static boolean isMatchedUrl(List<String> urlMatchers, String url) {
        if (urlMatchers == null) {
            return Boolean.FALSE;
        }

        for (String pattern : urlMatchers) {
            if (StringUtils.isBlank(pattern)) {
                continue;
            }
            if (antPathMatcher.match(pattern, url)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
