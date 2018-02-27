package com.yq.entity;

public class GithubUser {
    //我想要哪些我就放哪些
    private String login;
    private int id;
    private String avatar_url;
    private String html_url;//gihub地址
    private String name;

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "login='" + login + '\'' +
                ", id=" + id +
                ", avatar_url='" + avatar_url + '\'' +
                ", html_url='" + html_url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
