package com.example.myproject.Models.DeserializingJSON;

// Phrases.java

import com.fasterxml.jackson.annotation.*;

public class Phrases {
    private AroundTown[] aroundTown;
    private AroundTown[] arrivingAtYourDestination;
    private AroundTown[] atCustoms;
    private AroundTown[] atARestaurant;
    private AroundTown[] atTheAirport;
    private AroundTown[] atTheHotel;
    private AroundTown[] commonProblems;
    private AroundTown[] greetings;
    private AroundTown[] onTheAirplane;

    @JsonProperty("Section")
    public AroundTown[] getAroundTown() { return aroundTown; }
    @JsonProperty("Section")
    public void setAroundTown(AroundTown[] value) { this.aroundTown = value; }

    @JsonProperty("Arriving at Your Destination")
    public AroundTown[] getArrivingAtYourDestination() { return arrivingAtYourDestination; }
    @JsonProperty("Arriving at Your Destination")
    public void setArrivingAtYourDestination(AroundTown[] value) { this.arrivingAtYourDestination = value; }

    @JsonProperty("At Customs")
    public AroundTown[] getAtCustoms() { return atCustoms; }
    @JsonProperty("At Customs")
    public void setAtCustoms(AroundTown[] value) { this.atCustoms = value; }

    @JsonProperty("At a Restaurant")
    public AroundTown[] getAtARestaurant() { return atARestaurant; }
    @JsonProperty("At a Restaurant")
    public void setAtARestaurant(AroundTown[] value) { this.atARestaurant = value; }

    @JsonProperty("At the Airport")
    public AroundTown[] getAtTheAirport() { return atTheAirport; }
    @JsonProperty("At the Airport")
    public void setAtTheAirport(AroundTown[] value) { this.atTheAirport = value; }

    @JsonProperty("At the Hotel")
    public AroundTown[] getAtTheHotel() { return atTheHotel; }
    @JsonProperty("At the Hotel")
    public void setAtTheHotel(AroundTown[] value) { this.atTheHotel = value; }

    @JsonProperty("Common Problems")
    public AroundTown[] getCommonProblems() { return commonProblems; }
    @JsonProperty("Common Problems")
    public void setCommonProblems(AroundTown[] value) { this.commonProblems = value; }

    @JsonProperty("Greetings")
    public AroundTown[] getGreetings() { return greetings; }
    @JsonProperty("Greetings")
    public void setGreetings(AroundTown[] value) { this.greetings = value; }

    @JsonProperty("On the Airplane")
    public AroundTown[] getOnTheAirplane() { return onTheAirplane; }
    @JsonProperty("On the Airplane")
    public void setOnTheAirplane(AroundTown[] value) { this.onTheAirplane = value; }
}
