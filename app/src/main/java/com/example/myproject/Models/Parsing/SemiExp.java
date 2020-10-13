package com.example.myproject.Models.Parsing;

import java.util.ArrayList;
import java.util.HashMap;

public class SemiExp extends Exp{
    private Exp term;
    private Exp exp;
    private HashMap<String,String> hash;

    public SemiExp(Exp term, Exp exp) {
        this.term = term;
        this.exp = exp;
    }

    @Override
    public HashMap<String, String> hashMap() {
        hash.put(term.show(),exp.show());
        return hash;
    }

    @Override
    public String show() {
        return "SemiExp{" + "term=" + term.show() + "; exp=" + exp.show() + '}';
    }


}