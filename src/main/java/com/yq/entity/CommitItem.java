package com.yq.entity;

public class CommitItem {
    private  CommitRepo repository;

    public CommitRepo getRepository() {
        return repository;
    }

    @Override
    public String toString() {
        return "CommitItem{" +
                "repository=" + repository +
                '}';
    }
}
