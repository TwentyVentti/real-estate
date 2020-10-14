package com.example.myproject.Models.Parsing;

public class TimeExp extends Exp{

    int time;
    Token.Type unit;

    public TimeExp(int time, Token.Type unit) {
        this.time = time;
        this.unit = unit;
    }

    @Override
    public String show() {
        return Integer.toString(time);
    }

    @Override
    public int evaluate() {
        return 0;
    }
}
