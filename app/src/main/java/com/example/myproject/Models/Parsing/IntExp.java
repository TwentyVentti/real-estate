package com.example.myproject.Models.Parsing;

import java.util.ArrayList;
import java.util.HashMap;

public class IntExp extends Exp {
    private Integer value;

    public IntExp(Integer value) {
        this.value = value;
    }

    @Override
    public HashMap<String, String> hashMap() {
        return null;
    }

    @Override
    public String show() {
        return value.toString();
    }
}
