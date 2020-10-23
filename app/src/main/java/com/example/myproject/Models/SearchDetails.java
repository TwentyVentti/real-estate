package com.example.myproject.Models;


/**
 * @author Purvesh Mukesh Badmera - u7084724
 */
public class SearchDetails {
    public String city;
    public String country;
    public String user;

    /**
     * For guest login.
     */
    public SearchDetails () {}

    public SearchDetails(String user, String country, String city){
        this.city = city;
        this.country = country;
        this.user = user;
    }


}
