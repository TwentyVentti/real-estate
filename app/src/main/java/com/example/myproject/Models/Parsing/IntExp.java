package com.example.myproject.Models.Parsing;

import java.util.ArrayList;

public class IntExp extends Exp {
    private Integer stay;

    public IntExp(Integer stay) { this.stay = stay; }

    @Override
    public String show() { return stay.toString(); }

    @Override
    public ArrayList<String> selection() {
        return ;
    }
}
