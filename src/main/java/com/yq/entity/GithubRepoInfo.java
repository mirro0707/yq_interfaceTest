package com.yq.entity;

public class GithubRepoInfo {
    private String language;

    public String getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        return "GithubRepoInfo{" +
                "language='" + language + '\'' +
                '}';
    }
}
