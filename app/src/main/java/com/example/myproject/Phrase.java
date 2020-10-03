package com.example.myproject;


import java.io.Serializable;

public class Phrase implements Serializable {
    private String english;
    private String french;
    private String italian;

    public String getEnglish() {
        return english;
    }

    public String getFrench() {
        return french;
    }

    public String getItalian() {
        return italian;
    }

    public Phrase(String english, String french, String italian) {
        this.english = english;
        this.french = french;
        this.italian = italian;
    }
}