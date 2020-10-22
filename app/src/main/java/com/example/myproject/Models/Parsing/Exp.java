package com.example.myproject.Models.Parsing;

import java.io.Serializable;

/**
 * Abstract class that is implemented in Parser.
 * Allows for inheritance when parsing for optimal object oriented programming.
 *
 * @author Andrew Carse u6666440
 */
public abstract class Exp implements Serializable {
    public abstract String[] evaluate();
    public abstract String show();
}
