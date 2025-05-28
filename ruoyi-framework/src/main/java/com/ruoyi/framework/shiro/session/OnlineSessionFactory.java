package com.ruoyi.framework.shiro.session;

import com.ruoyi.auth.web.session.Session;
import org.springframework.stereotype.Component;

/**
 * 自定义sessionFactory会话
 *
 * @author ruoyi
 */
@Component
public class OnlineSessionFactory //implements SessionFactory
{
    public Session createSession(Object initData) {
        return new OnlineSession();
    }
}
