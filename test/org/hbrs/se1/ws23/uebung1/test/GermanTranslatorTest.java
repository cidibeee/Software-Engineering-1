package org.hbrs.se1.ws23.uebung1.test;

import org.hbrs.se1.ws23.uebung1.control.Factory;
import org.hbrs.se1.ws23.uebung1.control.GermanTranslator;
import org.hbrs.se1.ws23.uebung1.view.Client;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GermanTranslatorTest {

    @Test
    void aPositiveTest() {
        GermanTranslator translator = Factory.createGermanTranslater();
        String value = translator.translateNumber(5);
        assertEquals(value, "fünf");
    }

    @Test
    void aNegativTest()
    {
        GermanTranslator translator = Factory.createGermanTranslater();
        String value = translator.translateNumber(0);
        System.out.println(value);
        assertNotEquals(value, "null");
    }
}