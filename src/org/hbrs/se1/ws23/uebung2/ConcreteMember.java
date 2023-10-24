package org.hbrs.se1.ws23.uebung2;

import java.util.UUID;

public class ConcreteMember implements Member{

    private int id;
    public ConcreteMember()
    {
        id = Math.abs(UUID.randomUUID().hashCode());
    }

    @Override
    public Integer getID() {
        return id;
    }

    @Override
    public String toString()
    {
        return ("Member (ID = " + this.getID() + ")");
    }
}
