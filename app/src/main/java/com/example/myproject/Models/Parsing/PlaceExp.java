package com.example.myproject.Models.Parsing;

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
        return new String[]{placeType.toString(), place};
    }

    @Override
    public String show() {
        return "type=" + placeType + "; value=";
    }


}