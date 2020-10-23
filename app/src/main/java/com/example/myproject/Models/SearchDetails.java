package com.example.myproject.Models;

public class SearchDetails {
    public String city;
    public String country;
    public String user;

    public SearchDetails () {}

    public SearchDetails(String user, String country, String city){
        this.city = city;
        this.country = country;
        this.user = user;
    }


}
