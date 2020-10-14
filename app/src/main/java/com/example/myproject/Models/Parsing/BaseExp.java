package com.example.myproject.Models.Parsing;

import java.util.HashMap;

public class BaseExp extends Exp{
    private Exp term;
    private Exp exp;
    private HashMap<String,String> hash;

    public BaseExp(Exp term, Exp exp) {
        this.term = term;
        this.exp = exp;
    }

    public HashMap<String, String> hashMap() {
        hash.put(term.show(),exp.show());
        return hash;
    }

    @Override
    public int evaluate() {
        return 0;
    }

    @Override
    public String show() {
        return "SemiExp{" + "term=" + term.show() + "; exp=" + exp.show() + '}';
    }


}