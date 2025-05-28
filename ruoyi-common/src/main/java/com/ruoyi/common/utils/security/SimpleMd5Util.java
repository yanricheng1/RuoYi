package com.ruoyi.common.utils.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Md5加解密工具类
 */
public class SimpleMd5Util {

    /**
     * 将字节数组 转换成 十六进制字符串
     *
     * @param arr 要转换的字节数组
     * @return String 返回十六进制字符串
     */
    private static String hex(byte[] arr) {
        StringBuilder sb = new StringBuilder();
        for (byte item : arr) {
            sb.append(Integer.toHexString((item & 0xFF) | 0x100), 1, 3);
        }
        return sb.toString();
    }

    /**
     * 获取十六进制字符串形式的MD5摘要
     * MD5加密,并把结果由字节数组转换成十六进制字符串
     *
     * @param str 要加密的内容
     * @return String 返回加密后的十六进制字符串
     */
    public static String encode(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(str.getBytes());
            return hex(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 校验文本是否正确
     * 验证加盐后是否和原文本一致
     *
     * @param plainText 原文本
     * @param cypherText 加密之后的文本
     * @return boolean true表示和原文本一致   false表示和原文本不一致
     */
    public static boolean verify(String plainText, String cypherText) {
        final String md5Encode = encode(plainText);
        return md5Encode.equals(String.valueOf(cypherText));
    }

    /**
     * 生成含有随机盐的文本
     *
     * @param plainText 要加密的文本
     * @param salt 盐值
     * @return String 含有随机盐的文本
     */
    public static String encode(String plainText, String salt) {
        return encode(plainText + salt);
    }

    /**
     * 校验文本是否正确
     * 验证加盐后是否和原文本一致
     *
     * @param plainText 原文本
     * @param cypherText 加密之后的文本
     * @return boolean true表示和原文本一致   false表示和原文本不一致
     */
    public static boolean verify(String plainText, String salt, String cypherText) {
        final String md5Encode = encode(plainText, salt);
        return md5Encode.equals(String.valueOf(cypherText));
    }


    public static void main(String[] args) {
        // 原文
        String plainText = "admin12345678";
        final String cipherText1 = encode(plainText);
        System.out.println(cipherText1);
        System.out.println(verify(plainText, cipherText1));


        // 获取加盐后的MD5值
        final String salt = "sal中国tsaltsaltsalt五角星；";
        String cipherText = SimpleMd5Util.encode(plainText, salt);
        System.out.println(cipherText);
        System.out.println(SimpleMd5Util.verify(plainText, salt, cipherText));
    }

}


