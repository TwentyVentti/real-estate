package com.example.myproject.Models;

/**
 * This class provides us with efficient access to keeping track of the users
 * login details while they navigate our other features of the app.
 */
public class UserDetails {
    public String name;
    public String age;
    public String email;
    public String password;

    public UserDetails(String name, String age, String email, String password){
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
    }
}