package com.ruoyi.auth.web.sso.strategy;

public interface PasswordEncryptor {
    String encrypt(String password);

    String getName();
}
