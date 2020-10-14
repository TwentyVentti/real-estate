package com.example.myproject.Models.Parsing;

import java.util.ArrayList;
import java.util.HashMap;

public class PlaceExp extends Exp{
    private Token.Type placeType;
    private String place;

    public PlaceExp(Token.Type placeType, String place) {
        this.placeType = placeType;
        this.place = place;
    }


    @Override
    public int evaluate() {
        // Al
        return 0;
    }

    @Override
    public String show() {
        return "SemiExp{" + "term=" + placeType + "; exp=" + place + '}';
    }


}