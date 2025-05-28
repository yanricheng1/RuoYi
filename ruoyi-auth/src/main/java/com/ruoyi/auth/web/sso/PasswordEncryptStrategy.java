package com.ruoyi.auth.web.sso;

import com.ruoyi.auth.web.sso.strategy.PasswordEncryptor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class PasswordEncryptStrategy {
    private final Map<String, PasswordEncryptor> strategies = new HashMap<>();

    public String encrypt(String strategy, String password) {
        return strategies.get(strategy).encrypt(password);
    }

    public void register(String strategy, PasswordEncryptor passwordEncryptor) {
        strategies.put(strategy, passwordEncryptor);
    }

}
