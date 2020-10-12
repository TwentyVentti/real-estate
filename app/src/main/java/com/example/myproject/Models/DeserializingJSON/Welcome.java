package com.example.myproject.Models.DeserializingJSON;

import com.fasterxml.jackson.annotation.*;

public class Welcome {
    private Phrases phrases;

    @JsonProperty("Phrases")
    public Phrases getPhrases() { return phrases; }
    @JsonProperty("Phrases")
    public void setPhrases(Phrases value) { this.phrases = value; }
}
