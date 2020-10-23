package com.example.myproject.Models;

/**
 * @author Purvesh Mukesh Badmera - u7084724
 */
public class UserDetails {
    public String name;
    public String age;
    public String email;
    public String password;

    /**
     * For guest login.
     */
    public UserDetails () {}

    public UserDetails(String name, String age, String email, String password){
        this.name = name;
        this.age = age;
        this.email = email;
        this.password = password;
    }
}