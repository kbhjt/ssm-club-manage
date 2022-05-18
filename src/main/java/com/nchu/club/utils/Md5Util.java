package com.nchu.club.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {

    public static String Md5Encode(String key) {
        byte[] keys = DigestUtils.md5(key);
        return md5ToString(keys);
    }

    //将MD5byte数字转换为16进制字符串
    private static String md5ToString(byte[] md5) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < md5.length; i++) {
            int temp = md5[i] & 0Xff;
            String hexString = Integer.toHexString(temp);
            if (hexString.length() < 2) {
                sb.append("0").append(hexString);
            } else {
                sb.append(hexString);
            }
        }
        return sb.toString();
    }
}
