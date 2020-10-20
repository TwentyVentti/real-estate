package com.example.myproject;

import com.example.myproject.Models.Parsing.BaseExp;

import com.example.myproject.Models.Parsing.GrammarException;
import com.example.myproject.Models.Parsing.Parser;
import com.example.myproject.Models.Parsing.ParserException;
import com.example.myproject.Models.Parsing.TokenException;
import com.example.myproject.Models.Parsing.Tokenizer;
import static org.testng.AssertJUnit.assertEquals;
import org.testng.annotations.Test;

public class ParserTest {
    private static final String testCityCase = "city = \"Paris\" ; ";
    private static final String testDurationCase = "duration = 68 days;";
    private static final String testCountryCase = "country = \"France\";";
    private static final String testSearchCase = "country = \"France\"; city = \"Paris\"; duration = 1 month ; ";
    private static final String testCombination = "city = \"Paris\"; duration = 1 month ; country = \"France\"; ";
    private static final String testParanException = "city = \"Paris";
    private static final String testWithoutParanException = "city = paris";
    private static final String testIncompleteException = "city=";
    private static final String testRandomException = "city = \"Paris\"; duration = \"1 days\" ; country = \"France\";";

    @Test
    public void testCity() throws GrammarException {
        Tokenizer tokenizer = new Tokenizer(testCityCase);
        BaseExp t1 = (BaseExp) new Parser(tokenizer).parseBase();
        t1.evaluate();
        assertEquals(t1.city, "paris");
    }

    @Test
    public void testCountry() throws GrammarException {
        Tokenizer tokenizer = new Tokenizer(testCountryCase);
        BaseExp t1 = (BaseExp) new Parser(tokenizer).parseBase();
        t1.evaluate();
        assertEquals(t1.country, "france");
    }

    @Test
    public void testDuration() throws GrammarException {
        Tokenizer tokenizer = new Tokenizer(testDurationCase);
        BaseExp t1 = (BaseExp) new Parser(tokenizer).parseBase();
        t1.evaluate();
        assertEquals(t1.tunit, "DAY");
    }

    @Test
    public void testSearch() throws GrammarException {
        Tokenizer tokenizer = new Tokenizer(testSearchCase);
        BaseExp t1 = (BaseExp) new Parser(tokenizer).parseBase();
        t1.evaluate();
        assertEquals(t1.tunit, "MONTH");
        assertEquals(t1.city, "paris");
        assertEquals(t1.time, 1);
        assertEquals(t1.country, "france");
        assertEquals(t1.level, 3);

    }

    @Test
    public void testCombination() throws GrammarException {
        Tokenizer tokenizer = new Tokenizer(testCombination);
        BaseExp t1 = (BaseExp) new Parser(tokenizer).parseBase();
        t1.evaluate();
        assertEquals(t1.tunit, "MONTH");
        assertEquals(t1.city, "paris");
        assertEquals(t1.time, 1);
        assertEquals(t1.country, "france");
        assertEquals(t1.level, 3);
    }

    @Test(expectedExceptions = ParserException.class)
    public void testParanException() throws GrammarException {
        Tokenizer tokenizer = new Tokenizer(testParanException);
        BaseExp t1 = (BaseExp) new Parser(tokenizer).parseBase();
        t1.evaluate();
    }

    @Test(expectedExceptions = TokenException.class)
    public void testWithoutParanException() throws GrammarException {
        Tokenizer tokenizer = new Tokenizer(testWithoutParanException);
        BaseExp t1 = (BaseExp) new Parser(tokenizer).parseBase();
        t1.evaluate();
    }

    @Test(expectedExceptions = ParserException.class)
    public void testIncompleteException() throws GrammarException {
        Tokenizer tokenizer = new Tokenizer(testIncompleteException);
        BaseExp t1 = (BaseExp) new Parser(tokenizer).parseBase();
        t1.evaluate();
    }

    @Test(expectedExceptions = ParserException.class)
    public void testRandomException() throws GrammarException {
        Tokenizer tokenizer = new Tokenizer(testRandomException);
        BaseExp t1 = (BaseExp) new Parser(tokenizer).parseBase();
        t1.evaluate();
    }

}
