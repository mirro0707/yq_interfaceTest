package com.yq.github;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yq.entity.GithubCommitsResult;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;

/**
 * 统计一年里所发生的所有的提交，统计语言排名次数
 * 但是我们拿不到那么多值，因为它有限制，最多只能返回1000个
 * 那么我们取1000个样本出来判断一下，哪种语言在这个星期里面最受欢迎！！！
 */
public class GithubSpider {
    public static void main(String[] args) {
        try {
            URL url = new URL("https://api.github.com/search/commits?q=committer-date:2017-11-27..2017-12-01&page=1&per_page=100");
            HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
            //设置请求属性
            https.addRequestProperty("Accept","application/vnd.github.cloak-preview");
            //basicjk authorization 对它进行base64的加密
//            String up = MyPassword.username+":"+ MyPassword.password;
            String up = "mirro0707:yq870707";
            up = Base64.getEncoder().encodeToString(up.getBytes());
            https.addRequestProperty("Authorization","Basic "+up);

            https.setDoInput(true);
            InputStream is = https.getInputStream();//因为返回值就是一个大大的json格式
            Reader reader = new InputStreamReader(is);
            //我们为最大的json定义一个类
            Gson gson = new GsonBuilder().create();
            GithubCommitsResult gcr = gson.fromJson(reader, GithubCommitsResult.class);
//            System.out.println(gcr);
            System.out.println("id:"+gcr.getItems()[2].getRepository().getId());
            System.out.println(https.getHeaderField("X-RateLimit-Limit"));

            is.close();
            https.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}
