package com.example.myproject;

import com.example.myproject.Models.Parsing.BaseExp;
import com.example.myproject.Models.Parsing.Exp;
import com.example.myproject.Models.Parsing.Parser;
import com.example.myproject.Models.Parsing.Tokenizer;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;

public class ParserTest {
    private static final String testCityCase = "city = \"Paris\" ; ";
    private static final String testDurationCase = "duration = 68 days;";
    private static final String testCountryCase = "country = \"France\";";
    private static final String testSearchCase = "country = \"France\"; city = \"Paris\"; duration = 1 month ; ";

    @Test
    public void testCity() {
        Tokenizer tokenizer = new Tokenizer(testCityCase);
        BaseExp t1 = (BaseExp) new Parser(tokenizer).parseBase();
        t1.evaluate();
        assertEquals(t1.city, "paris");
    }

    @Test
    public void testCountry() {
        Tokenizer tokenizer = new Tokenizer(testCountryCase);
        BaseExp t1 = (BaseExp) new Parser(tokenizer).parseBase();
        t1.evaluate();
        assertEquals(t1.country, "france");
    }

    @Test
    public void testDuration() {
        Tokenizer tokenizer = new Tokenizer(testDurationCase);
        BaseExp t1 = (BaseExp) new Parser(tokenizer).parseBase();
        t1.evaluate();
        assertEquals(t1.tunit, "DAY");
    }

    @Test
    public void testSearch() {
        Tokenizer tokenizer = new Tokenizer(testSearchCase);
        BaseExp t1 = (BaseExp) new Parser(tokenizer).parseBase();
        t1.evaluate();
        assertEquals(t1.tunit, "MONTH");
        assertEquals(t1.city, "paris");
        assertEquals(t1.time, 1);
        assertEquals(t1.country, "france");
        assertEquals(t1.level, 3);

    }
}
