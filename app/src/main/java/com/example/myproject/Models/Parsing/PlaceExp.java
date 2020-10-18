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
    public String[] evaluate() {
        // Al
        String [] temp = {placeType.toString(),place};
        return temp;
    }

    @Override
    public String show() {
        return "type=" + placeType + "; value=";
    }


}