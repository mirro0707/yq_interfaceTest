package com.yq.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yq.entity.Cat;

/***
 * toJson:将对象转换成Json字符串
 * fromJson:从Json相关对象到java实体
 */
public class GsonDemo {
    public static void main(String[] args) {
        Gson gson = new GsonBuilder().create();//
        Cat cat = new Cat();
        cat.setName("Tom");
        cat.setAge(6);
        String jsonStr = gson.toJson(cat);//把对象转换为json格式，它会返回一个String类型字符串
        System.out.println(jsonStr);
    }
}
