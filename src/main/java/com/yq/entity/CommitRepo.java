package com.yq.entity;

public class CommitRepo {
    private int id;
    private String full_name;

    public int getId() {
        return id;
    }

    public String getFull_name() {
        return full_name;
    }

    @Override
    public String toString() {
        return "CommitRepo{" +
                "id=" + id +
                ", full_name='" + full_name + '\'' +
                '}';
    }
}
