package com.example.myproject.Models;

/**
 * This class provides us with efficient access to keeping track of the users
 * search details while they navigate our other features of the app.
 */
public class SearchDetails {
    public String city;
    public String country;
    public String user;

    public SearchDetails(String user, String country, String city){
        this.city = city;
        this.country = country;
        this.user = user;
    }
}
