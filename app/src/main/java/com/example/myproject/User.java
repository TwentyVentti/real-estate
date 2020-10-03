package com.example.myproject;

import java.io.Serializable;

public class User implements Serializable {
    private String id;
    private String username;
    private String password;

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public User(String id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }
}