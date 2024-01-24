package org.hbrs.se1.ws23.uebung9;

public class GraficDocument extends CoreDocument{

    private String url;

    public GraficDocument(String url)
    {
        this.url = url;
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
        return 1200;  //Feste größe aus aufgabenstellung
    }
}
