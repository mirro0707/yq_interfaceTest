package com.yq.authentication;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by YQ on 2018/2/20.
 */
public class MD5Demo {
    public static void main(String[] args) throws NoSuchAlgorithmException {
        String oriStr = "123456";
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] bs = md.digest(oriStr.getBytes());
        System.out.println(new String(bs));//直接无法转成字符串

    }
}
