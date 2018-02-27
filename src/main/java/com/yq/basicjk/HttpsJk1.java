package com.yq.basicjk;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpsJk1 {
    public static void main(String[] args) {
        URL url = null;
        HttpURLConnection https = null;
        try {
            url = new URL("https://api.github.com/users/mirro0707");//定义要访问的API的URL地址
            https = (HttpsURLConnection) url.openConnection();//根据url地址打开链接，返回https对象
            https.setDoInput(true);//打开输入流
            InputStream is = https.getInputStream();//获得当前https链接的输入流
            int c = -1;
            while ((c=is.read())!=-1){//通过循环遍历从服务端发来的所有内容，当返回位-1，则表示结束
                System.out.print((char)c);//逐个字节打印
            }
            is.close();
            https.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
