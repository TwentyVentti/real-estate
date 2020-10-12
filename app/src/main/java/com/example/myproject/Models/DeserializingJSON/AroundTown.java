package com.example.myproject.Models.DeserializingJSON;

import com.fasterxml.jackson.annotation.*;

public class AroundTown {
    private String dutch;
    private String english;
    private String french;
    private long id;
    private String italian;
    private long level;
    private String section;
    private String spanish;

    @JsonProperty("dutch")
    public String getDutch() { return dutch; }
    @JsonProperty("dutch")
    public void setDutch(String value) { this.dutch = value; }

    @JsonProperty("english")
    public String getEnglish() { return english; }
    @JsonProperty("english")
    public void setEnglish(String value) { this.english = value; }

    @JsonProperty("french")
    public String getFrench() { return french; }
    @JsonProperty("french")
    public void setFrench(String value) { this.french = value; }

    @JsonProperty("id")
    public long getID() { return id; }
    @JsonProperty("id")
    public void setID(long value) { this.id = value; }

    @JsonProperty("italian")
    public String getItalian() { return italian; }
    @JsonProperty("italian")
    public void setItalian(String value) { this.italian = value; }

    @JsonProperty("level")
    public long getLevel() { return level; }
    @JsonProperty("level")
    public void setLevel(long value) { this.level = value; }

    @JsonProperty("section")
    public String getSection() { return section; }
    @JsonProperty("section")
    public void setSection(String value) { this.section = value; }

    @JsonProperty("spanish")
    public String getSpanish() { return spanish; }
    @JsonProperty("spanish")
    public void setSpanish(String value) { this.spanish = value; }
}
