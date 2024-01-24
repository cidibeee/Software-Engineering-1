package org.hbrs.se1.ws23.uebung9;

import javax.sound.sampled.AudioFormat;
import java.io.UnsupportedEncodingException;

public class TextDocument extends CoreDocument{
    public String inhalt;
    public Encoding encoding;

    public TextDocument(String inhalt, Encoding encoding)
    {
        this.inhalt = inhalt;
        this.encoding = encoding;
    }

    @Override
    public int getID() {
        return super.id;
    }

    @Override
    public void setID(int id) {
        super.id = id;
    }

    @Override
    public int size() {
        try {
            return inhalt.getBytes(encoding.getString()).length;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public enum Encoding{
        UTF8,
        UTF32,
        UTF16;
        String getString()
        {
            if(this == UTF8)
                return "UTF-8";
            else if (this == UTF16)
                return "UTF-16";
            else
                return "UTF-32";

        }
    }
}
