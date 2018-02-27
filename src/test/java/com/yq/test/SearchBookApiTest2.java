package com.yq.test;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yq.entity.Authorization;
import com.yq.entity.SearchBookResult;
import com.yq.entity.SearchError;
import com.yq.utils.HttpPostUtil;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * 加参数化
 */
public class SearchBookApiTest2 {
	
	private HttpPostUtil postUtil;
	private Authorization auth;

	@DataProvider(name="searchBookData")
	public Object[][] getData() {
		Object[][] objects = null;
		try {
			objects = new Object[][]{
					{"yq", null, null, null, null, 5},
					{"yq", "3", null, null, null, 1},
					{"yq", null, URLEncoder.encode("思想", "utf-8"), null, null, 1},
					{"yq", null, null, null, "100.00", 2}
			};
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return objects;

	}
	
	@BeforeClass
	public void doLogin() {
		Gson gson = new GsonBuilder().create();
		postUtil = new HttpPostUtil();
		/*
		 * 1、进行登录的操作，获取cookie，获取token
		 */
		String url = "http://localhost:8080/pink_api/login";
		List<NameValuePair> params = new ArrayList<>(); // 创建准备传送的post请求的body参数集合
		params.add(new BasicNameValuePair("user", "liudao"));
		params.add(new BasicNameValuePair("pwd", "123456"));
		
		String responseStr = postUtil.doPost(url, params, null);
		auth = gson.fromJson(responseStr, Authorization.class);
	}
	
	@Test(dataProvider="searchBookData")
	public void testSearchBook(String user, String id, String name, String author, String price, int count) {
		
		Gson gson = new GsonBuilder().create();
		/*
		 * 2、进行查询操作，需要提供Authorization头，cookie，以及传送参数user
		 */
		SearchBookResult sbr = null;
		String url = "http://localhost:8080/pink_api/sbook"; // 创建post请求对象
		List<NameValuePair> params = new ArrayList<>(); // 创建准备传送的post请求的body参数集合
		params.add(new BasicNameValuePair("user", user));
		params.add(new BasicNameValuePair("id", id));
		params.add(new BasicNameValuePair("name", name));
		params.add(new BasicNameValuePair("author", author));
		params.add(new BasicNameValuePair("price", price));
		Header header = new BasicHeader("Authorization", "Bearer "+auth.getToken()); // 准备添加用于token认证的头信息
		List<Header> headers = new ArrayList<>();
		headers.add(header);
		String responseStr = postUtil.doPost(url, params, headers);
		sbr = gson.fromJson(responseStr, SearchBookResult.class);
		assertEquals(sbr.getCount(), count);
	}
	@Test
	public void testSearchBookWithoutUser(){
		Gson gson = new GsonBuilder().create();
		SearchError error = null;
		String url = "http://localhost:8080/pink_api/sbook";
		Header header = new BasicHeader("Authorization", "Bearer "+auth.getToken());
		List<Header> headers = new ArrayList<>();
		headers.add(header);
		String responseStr = postUtil.doPost(url, null, headers);
		error = gson.fromJson(responseStr, SearchError.class);
		assertEquals(error.getErrorCode(),103);
		assertEquals(error.getMessage(),"认证失败，请确定token是否有效");
	}
}
