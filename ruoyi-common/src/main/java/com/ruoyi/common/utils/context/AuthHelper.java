package com.ruoyi.common.utils.context;

import org.apache.commons.codec.binary.Hex;

import java.util.Random;

public class AuthHelper {
    public static String randomSalt() {
        // 一个Byte占两个字节，此处生成的3字节，字符串长度为6
        Random secureRandom = new Random();
        byte[] buffer = new byte[3];
        secureRandom.nextBytes(buffer);
        String hex = Hex.encodeHexString(buffer);
        return hex;
    }
}
