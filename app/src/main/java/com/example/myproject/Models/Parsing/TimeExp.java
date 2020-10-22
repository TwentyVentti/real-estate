package com.example.myproject.Models.Parsing;

/**
 * @author Abhaas Goyal - u7145384
 */
public class TimeExp extends Exp{

    int time;
    Token.Type unit;

    public TimeExp(int time, Token.Type unit) {
        this.time = time;
        this.unit = unit;
    }

    @Override
    public String show() {
        return "type=time " + time + "; value=" + unit ;
    }

    @Override
    public String[] evaluate()
    {
        return new String[]{"TIME", Integer.toString(time), unit.toString()};
    }
}
