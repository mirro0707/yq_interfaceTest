package com.yq.basicjk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yq.entity.GithubUser;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
/***
 * toJson:    将java对象转换成Json字符串
 * fromJson:  从json字符流/字符串/对象======>java实体(bean)
 */
public class HttpsJk2 {
    public static void main(String[] args) {
        URL url = null;
        HttpURLConnection https = null;
        try {
            url = new URL("https://api.github.com/users/mirro0707");//定义要访问的API的URL地址
            https = (HttpsURLConnection) url.openConnection();//根据url地址打开链接，返回https对象
            https.setDoInput(true);//打开输入流
            InputStream is = https.getInputStream();//获得当前https链接的输入流
            Reader reader = new InputStreamReader(is, "utf-8");
            /**
             * 逐个打印太挫了
             * fromJson 的第二个参数，则是要将 json字符串转换为对应的实体类（bean)
             */
            Gson gson = new GsonBuilder().create();
            GithubUser user = gson.fromJson(reader, GithubUser.class);//返回对象的类型
            System.out.println(user);
            is.close();
            https.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
