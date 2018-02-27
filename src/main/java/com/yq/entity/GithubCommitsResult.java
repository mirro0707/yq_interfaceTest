package com.yq.entity;

import java.util.Arrays;

public class GithubCommitsResult {
    private CommitItem[] items;

    public CommitItem[] getItems() {
        return items;
    }

    @Override
    public String toString() {
        return "GithubCommitsResult{" +
                "items=" + Arrays.toString(items) +
                '}';
    }
}
