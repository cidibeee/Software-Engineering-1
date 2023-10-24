package org.hbrs.se1.ws23.uebung1.control;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Factory {
    public static GermanTranslator createGermanTranslater()
    {
        return new GermanTranslator();
    }
}
