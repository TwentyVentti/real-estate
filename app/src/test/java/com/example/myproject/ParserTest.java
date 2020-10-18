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
    private static final String testSearchCase = "country = \"France\"; city = \"Paris\"; duration = 1 week ; ";

    @Test
    public void basicTest() {
        Tokenizer tokenizer = new Tokenizer(testCityCase);
        BaseExp t1 = (BaseExp) new Parser(tokenizer).parseBase();
        t1.evaluate();
        assertEquals(t1.city, "Paris");
    }
}
