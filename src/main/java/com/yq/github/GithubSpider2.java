package com.yq.github;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yq.entity.CommitItem;
import com.yq.entity.GithubCommitsResult;
import com.yq.entity.GithubRepoInfo;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * 统计一年里所发生的所有的提交，统计语言排名次数
 * 但是我们拿不到那么多值，因为它有限制，最多只能返回1000个
 * 那么我们取1000个样本出来判断一下，哪种语言在这个星期里面最受欢迎！！！
 * 仓库名 提交次数
 * 仓库名 语言
 *
 * 语言一样的可以再叠加一次
 * =>仓库名 提交次数 语言
 */
public class GithubSpider2 {
    public static void main(String[] args) {
        Map<String,Integer> repo_count = new HashMap<>();//仓库，提交次数
        Map<String, String> repo_lang = new HashMap<>();

            //1000个数据只能取到第10页
        for (int i = 1; i <= 10; i++) {
            System.out.println(i);
            String url = "https://api.github.com/search/commits" +
                    "?q=committer-date:2017-11-27..2017-12-01&page="+i+"&per_page=100";
            String ret = getGithubApiReply(url);
            //我们为最大的json定义一个类
            Gson gson = new GsonBuilder().create();
            GithubCommitsResult gcr = gson.fromJson(ret, GithubCommitsResult.class);//从json字符串转对象
            CommitItem[] items = gcr.getItems();
            //items.iter 每个仓库的提交次数就知道了
            for (CommitItem item : items) {
                String full_name = item.getRepository().getFull_name();
                if (repo_count.containsKey(full_name)){
                    repo_count.put(full_name,repo_count.get(full_name)+1);
                }else {
                    repo_count.put(full_name,1);
                }
            }
        }

        //遍历仓库名称即可
        for (String repoFullName : repo_count.keySet()) {
            String restr = getGithubApiReply("https://api.github.com/repos/" + repoFullName);
            Gson gson = new GsonBuilder().create();
            GithubRepoInfo githubRepoInfo = gson.fromJson(repoFullName, GithubRepoInfo.class);//拿到对象
            repo_lang.put(repoFullName, githubRepoInfo.getLanguage());

        }

        System.out.println(repo_count);
        System.out.println(repo_lang);
            //System.out.println("id:"+gcr.getItems()[2].getRepository().getId());
        }

    public static String getGithubApiReply(String api_url){
        String restr = null;
        try {
            URL url = new URL(api_url);
            HttpsURLConnection https = (HttpsURLConnection) url.openConnection();
            https.addRequestProperty("Accept","application/vnd.github.cloak-preview");
            String up = "mirro0707:yq870707";
            up = Base64.getEncoder().encodeToString(up.getBytes());
            System.out.println(up);
            https.addRequestProperty("Authorization","Basic "+up);
            https.setDoInput(true);
            InputStream is = https.getInputStream();//字节流
            Reader reader = new InputStreamReader(is,"utf-8");//字符流
            //以前一个字节一个字节取，现在一个字符一个字符的取\
            //为了一个字符一个字符的往里放，
            StringBuffer sb = new StringBuffer();
            int c = -1;
            while ((c=reader.read())!=-1){
                sb.append((char)c);
            }
            //带有buffer的String再转为普通的String
            restr = new String(sb);

            is.close();
            https.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }

        return restr;
    }
}
