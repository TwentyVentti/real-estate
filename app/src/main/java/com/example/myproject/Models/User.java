package com.example.myproject.Models;

public class User {
    private String language;
    private Integer level;
    private String city;
    private Integer duration;
    private String country;

    public User(String language, Integer level, String city, Integer duration, String country) {
        this.language = language;
        this.level = level;
        this.city = city;
        this.duration = duration;
        this.country = country;
    }
}
