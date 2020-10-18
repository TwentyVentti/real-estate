package com.example.myproject.Models.Parsing;

public class BaseExp extends Exp{
    private Exp term1;
    private Exp term2;

    // These 5 parameters can be accessed by the created object
    public int time;
    public String tunit;
    public String country;
    public String city;
    public int level;
    
    public BaseExp(Exp term1, BaseExp term2) {
        this.term1 = term1;
        this.term2 = term2.term1;
        setTerm(term2.term1);
        if (term2.term2 != null)
            setTerm(term2.term2);
    }

    public BaseExp(Exp term1) {
        this.term1 = term1;
    }

    private void setTerm (Exp type) {
        String[] temp = type.evaluate();
        switch (temp[0]) {
            case "TIME":
                time = Integer.parseInt(temp[1]);
                tunit = temp[2];
                int totalDays = 0;

                switch (tunit) {
                    case "DAY" : totalDays = time; break;
                    case "WEEK" : totalDays = time * 7; break;
                    case "MONTH" : totalDays = time * 30; break;
                }

                if (totalDays <= 7)
                    level = 1;
                else if (totalDays <= 14)
                    level = 2;
                else
                    level = 3;
                break;
            case "CITY":
                city = temp[1];
                break;
            case "COUNTRY" :
                country = temp[1];
                break;
            default :
                System.out.println("Error in parameters check parser");
        }
    }
    @Override
    public String[] evaluate() {

        setTerm(term1);
        return new String[0];
    }

    @Override
    public String show() {
        return "" + "term=" + term1.show() + "; exp=" + term2.show() + '}';
    }


}