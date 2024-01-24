package org.hbrs.se1.ws23.uebung9;

import java.util.ArrayList;
import java.util.List;

public class ComplexDocument implements Document{

    private int id;
    protected List<Document> Documents;

    public ComplexDocument(int id)
    {
        this.id = id;
        Documents = new ArrayList<>();
    }

    public void addDocument(Document d)
    {
        Documents.add(d);
    }

    public void deleteDocument(Document d)
    {
        Documents.remove(d);
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public void setID(int id) {
        this.id = id;
    }

    @Override
    public int size() {
        int ges = 0;
        for(Document d : Documents)
        {
            ges += d.size();
        }
        return ges;
    }
}
