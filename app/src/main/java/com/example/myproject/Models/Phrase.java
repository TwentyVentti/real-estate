package com.example.myproject.Models;


import java.io.Serializable;

public class Phrase implements Serializable {
    private String english;
    private String french;
    private String italian;
    private String spanish;
    private String dutch;
    private String section;
    private Integer level;

    public Phrase(String english, String french, String italian, String spanish, String dutch, String section, Integer level) {
        this.english = english;
        this.french = french;
        this.italian = italian;
        this.spanish = spanish;
        this.dutch = dutch;
        this.section = section;
        this.level = level;
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

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}