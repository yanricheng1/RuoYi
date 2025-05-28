package com.ruoyi.framework.manager.consumer.login;

import com.ruoyi.common.event.BizEventDispatcher;
import com.ruoyi.common.event.BizTopicType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class AllLoginInfoEventParser extends LoginInfoEventParser implements InitializingBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        BizEventDispatcher.register(BizTopicType.USER_LOGIN.getTopic(), this);
        BizEventDispatcher.register(BizTopicType.PASSWORD_ERROR.getTopic(), this);
        BizEventDispatcher.register(BizTopicType.USER_REGISTER.getTopic(), this);
    }
}
