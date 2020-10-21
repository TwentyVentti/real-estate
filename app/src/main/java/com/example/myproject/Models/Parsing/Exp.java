package com.example.myproject.Models.Parsing;

import java.io.Serializable;

public abstract class Exp implements Serializable {
    public abstract String[] evaluate();
    public abstract String show();
}
