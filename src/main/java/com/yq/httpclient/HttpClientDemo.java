package com.yq.httpclient;


import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by YQ on 2018/2/25.
 * 没有cookie存储器
 */
public class HttpClientDemo {
    public static void main(String[] args) {
        CloseableHttpClient httpclient = HttpClients.createDefault();//创建httpclient对象
        HttpPost httpPost = new HttpPost("http://localhost:8080/pink_api/login");//创建post请求对象
        List<NameValuePair> params = new ArrayList<NameValuePair>();//创建准备传送的post请求的body参数集合
        params.add(new BasicNameValuePair("user", "yq"));
        params.add(new BasicNameValuePair("pwd", "123456"));
        CloseableHttpResponse response = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));//讲准备好的参数集合放入post请求中
            response = httpclient.execute(httpPost);//使用httpclient发送post请求
            HttpEntity entity = response.getEntity();//获取响应实例
            String responseStr = EntityUtils.toString(entity);//将实例转换为字符串（返回值）
            System.out.println(responseStr);

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
