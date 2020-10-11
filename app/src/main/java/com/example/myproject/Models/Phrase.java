package com.example.myproject.Models;


import java.io.Serializable;

public class Phrase implements Serializable {
    private String english;
    private String french;
    private String italian;
    private String spanish;
    private String dutch;

    public Phrase(String english) {
        this.english = english;
        this.french = french;
        this.italian = italian;
        this.spanish = spanish;
        this.dutch = dutch;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getFrench() {
        return french;
    }

    public void setFrench(String french) {
        this.french = french;
    }

    public String getItalian() {
        return italian;
    }

    public void setItalian(String italian) {
        this.italian = italian;
    }

    public String getSpanish() {
        return spanish;
    }

    public void setSpanish(String spanish) {
        this.spanish = spanish;
    }

    public String getDutch() {
        return dutch;
    }

    public void setDutch(String dutch) {
        this.dutch = dutch;
    }
}