package com.yq.authentication;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

/**
 * Created by YQ on 2018/2/19.
 * token是怎么来的？
 * 其实是通过json数据来转的
 */
public class BearTokenGen {
    //我用123作为key,实际是要从开发那边获取的
    //开发那边一般来说 会用服务器当前时间作为那个key
    public static SecretKey genKey(){
        return new SecretKeySpec("123".getBytes(),"AES");
    }
    public  static String createToken(){
        SignatureAlgorithm sa = SignatureAlgorithm.HS256;
        //当前毫秒数
        long nM =System.currentTimeMillis();
        //当前日期
        Date now = new Date(nM);
        JwtBuilder builder = Jwts.builder()
                .setIssuer("yq")
                .setSubject("getMoney")
                .setIssuedAt(now)
                .setExpiration(new Date(nM+60000))//一分钟过期
                .signWith(sa,genKey());
        return builder.compact();
    }

    public static void main(String[] args) {
        System.out.println("生成token:"+createToken());
    }
}
