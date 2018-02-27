package com.yq.authentication;

import java.util.Base64;

/**
 * Created by YQ on 2018/2/19.
 */
public class Base64Demo {
    public static void main(String[] args) {
        //base64编码
        String encodeStr = Base64.getEncoder().encodeToString("abcd".getBytes());
        System.out.println(encodeStr);
        //解码
        byte[] bs = Base64.getDecoder().decode(encodeStr);
        String oriStr = new String(bs);
        System.out.println(oriStr);


    }


}
