package com.example.myproject.Models;


import android.content.res.AssetManager;
import android.os.Build;
import android.util.JsonReader;

import androidx.annotation.RequiresApi;

import com.example.myproject.Models.DeserializingJSON.Phrases;
import com.example.myproject.Models.Parsing.Token;
import com.fasterxml.jackson.core.JsonParser;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.net.IDN;
import java.util.ArrayList;
import java.util.List;

import bsh.Variable;

public class Phrase implements Serializable{
    private String english;
    private String french;
    private String italian;
    private String spanish;
    private String dutch;
    private String section;
    private Integer level;
    private Integer ID;

    @Override
    public String toString() {
        return "Phrase{" +
                "english='" + english + '\'' +
                ", french='" + french + '\'' +
                ", italian='" + italian + '\'' +
                ", spanish='" + spanish + '\'' +
                ", dutch='" + dutch + '\'' +
                ", section='" + section + '\'' +
                ", level=" + level +
                ", ID=" + ID +
                '}';
    }

    public Phrase(String english, String french, String italian, String spanish, String dutch, String section, Integer level, Integer ID) {
        this.english = english;
        this.french = french;
        this.italian = italian;
        this.spanish = spanish;
        this.dutch = dutch;
        this.section = section;
        this.level = level;
        this.ID = ID;
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

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }




}