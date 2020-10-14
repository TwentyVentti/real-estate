package com.example.myproject;

import com.example.myproject.Models.Parsing.Token;
import com.example.myproject.Models.Parsing.Tokenizer;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * TODO: (a) If there is space within a string to search
 *       (b) Should Durations(s) be included ?
 */
public class TokenizerTest {
    private static Tokenizer tokenizer;
    private static final String testCityCase = "city = \"Paris\" ; ";
    private static final String testDurationCase = "duration = 68 days;";
    private static final String testCountryCase = "country = \"France\";";
    private static final String testSearchCase = "country = \"France\"; city = \"Paris\"; duration = 1 weeks ; ";

    @Test(timeOut = 1000)
    public void testCityToken() {
        tokenizer = new Tokenizer(testCityCase);

        assertEquals("wrong token type", Token.Type.CITY, tokenizer.current().type());
        assertEquals("wrong token value", "city", tokenizer.current().token());

        tokenizer.next();
        assertEquals("wrong token type", Token.Type.EQ, tokenizer.current().type());
        assertEquals("wrong token value", "=", tokenizer.current().token());

        tokenizer.next();
        assertEquals("wrong token type", Token.Type.STRING, tokenizer.current().type());
        assertEquals("wrong token value", "paris", tokenizer.current().token());

        tokenizer.next();
        assertEquals("wrong token type", Token.Type.SEMI, tokenizer.current().type());
        assertEquals("wrong token value", ";", tokenizer.current().token());

    }

    @Test
    public void testDurationToken() {
        tokenizer = new Tokenizer(testDurationCase);

        assertEquals("wrong token type", Token.Type.DURATION, tokenizer.current().type());
        assertEquals("wrong token value", "duration", tokenizer.current().token());

        tokenizer.next();
        assertEquals("wrong token type", Token.Type.EQ, tokenizer.current().type());
        assertEquals("wrong token value", "=", tokenizer.current().token());

        tokenizer.next();
        assertEquals("wrong token type", Token.Type.INT, tokenizer.current().type());
        assertEquals("wrong token value", "68", tokenizer.current().token());

        tokenizer.next();
        assertEquals("wrong token type", Token.Type.DAY, tokenizer.current().type());
        assertEquals("wrong token value", "days", tokenizer.current().token());

        tokenizer.next();
        assertEquals("wrong token type", Token.Type.SEMI, tokenizer.current().type());
        assertEquals("wrong token value", ";", tokenizer.current().token());

    }

    @Test
    public void testCountryToken() {
        tokenizer = new Tokenizer(testCountryCase);

        assertEquals("wrong token type", Token.Type.COUNTRY, tokenizer.current().type());
        assertEquals("wrong token value", "country", tokenizer.current().token());

        tokenizer.next();
        assertEquals("wrong token type", Token.Type.EQ, tokenizer.current().type());
        assertEquals("wrong token value", "=", tokenizer.current().token());

        tokenizer.next();
        assertEquals("wrong token type", Token.Type.STRING, tokenizer.current().type());
        assertEquals("wrong token value", "france", tokenizer.current().token());

        tokenizer.next();
        assertEquals("wrong token type", Token.Type.SEMI, tokenizer.current().type());
        assertEquals("wrong token value", ";", tokenizer.current().token());

    }

    @Test
    public void testSearch() {
        tokenizer = new Tokenizer(testSearchCase);

        assertEquals("wrong token type", Token.Type.COUNTRY, tokenizer.current().type());
        assertEquals("wrong token value", "country", tokenizer.current().token());

        tokenizer.next();
        assertEquals("wrong token type", Token.Type.EQ, tokenizer.current().type());
        assertEquals("wrong token value", "=", tokenizer.current().token());

        tokenizer.next();
        assertEquals("wrong token type", Token.Type.STRING, tokenizer.current().type());
        assertEquals("wrong token value", "france", tokenizer.current().token());


        tokenizer.next();
        assertEquals("wrong token type", Token.Type.SEMI, tokenizer.current().type());
        assertEquals("wrong token value", ";", tokenizer.current().token());

        tokenizer.next();
        assertEquals("wrong token type", Token.Type.CITY, tokenizer.current().type());
        assertEquals("wrong token value", "city", tokenizer.current().token());

        tokenizer.next();
        assertEquals("wrong token type", Token.Type.EQ, tokenizer.current().type());
        assertEquals("wrong token value", "=", tokenizer.current().token());

        tokenizer.next();
        assertEquals("wrong token type", Token.Type.STRING, tokenizer.current().type());
        assertEquals("wrong token value", "paris", tokenizer.current().token());
        System.out.println(tokenizer.current().token());
        tokenizer.next();
        assertEquals("wrong token type", Token.Type.SEMI, tokenizer.current().type());
        assertEquals("wrong token value", ";", tokenizer.current().token());

        tokenizer.next();
        assertEquals("wrong token type", Token.Type.DURATION, tokenizer.current().type());
        assertEquals("wrong token value", "duration", tokenizer.current().token());

        tokenizer.next();
        assertEquals("wrong token type", Token.Type.EQ, tokenizer.current().type());
        assertEquals("wrong token value", "=", tokenizer.current().token());

        tokenizer.next();
        assertEquals("wrong token type", Token.Type.INT, tokenizer.current().type());
        assertEquals("wrong token value", "1", tokenizer.current().token());

        tokenizer.next();
        assertEquals("wrong token type", Token.Type.WEEK, tokenizer.current().type());
        assertEquals("wrong token value", "weeks", tokenizer.current().token());

        tokenizer.next();
        assertEquals("wrong token type", Token.Type.SEMI, tokenizer.current().type());
        assertEquals("wrong token value", ";", tokenizer.current().token());
    }
}
