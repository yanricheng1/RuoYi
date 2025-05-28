package com.ruoyi.auth.web.sso.strategy;

import com.ruoyi.auth.web.sso.PasswordEncryptStrategy;
import com.ruoyi.common.utils.security.SimpleMd5Util;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class SimpleMD5PasswordEncryptor implements PasswordEncryptor, InitializingBean {

    @Resource
    private PasswordEncryptStrategy passwordEncryptStrategy;
    @Override
    public String encrypt(String password) {
        return SimpleMd5Util.encode(password);
    }

    @Override
    public String getName() {
        return PasswordEncryptorType.jdk_md5.name();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        passwordEncryptStrategy.register(getName(),this);
    }
}
