package com.example.myproject.Models.Parsing;

import java.io.Serializable;

/**
 * Abstract class that is implemented in Parser.
 * Allows for inheritance when parsing for optimal object oriented programming.
 *
 * Evaluate and show functions inspired from 2100 lab
 */
public abstract class Exp implements Serializable {
    public abstract String[] evaluate();
    public abstract String show();
}
