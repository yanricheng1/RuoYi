package com.ruoyi.framework.manager.consumer.login;

import com.ruoyi.common.event.BizEventParser;
import com.ruoyi.common.event.msg.LoginInfo;
import com.ruoyi.framework.manager.AsyncManager;
import com.ruoyi.framework.manager.factory.AsyncFactory;


public class LoginInfoEventParser implements BizEventParser {
    @Override
    public void parse(Object event) {
        if (event != null && event instanceof LoginInfo) {
            LoginInfo loginInfo = (LoginInfo) event;
            AsyncManager.me().execute(AsyncFactory.recordLogininfor(
                    loginInfo.getUsername(),
                    loginInfo.getStatus(),
                    loginInfo.getMessage(),
                    loginInfo.getArgs()));
        }
    }
}
