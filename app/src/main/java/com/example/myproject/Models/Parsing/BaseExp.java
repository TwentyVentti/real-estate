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
//        if (unitOfTime.equals("DAY")||unitOfTime.equals("DAYS")){
//            totalDays = Integer.parseInt(number.toString());
//        }
//        else if (unitOfTime.equals("WEEK")||unitOfTime.equals("WEEKS")){
//            totalDays = Integer.parseInt(number.toString())*7;
//        }
//        else if (unitOfTime.equals("MONTH")||unitOfTime.equals("MONTHS")){
//            totalDays = Integer.parseInt(number.toString())*30;
//        }
//        int level = 0;
//        if (totalDays<=7){
//            level = 1;
//        } else if (totalDays<=14){
//            level = 2;
//        } else if (totalDays<=30){
//            level =3;
//        }
        return 0;
    }

    @Override
    public String show() {
        return "SemiExp{" + "term=" + term.show() + "; exp=" + exp.show() + '}';
    }


}