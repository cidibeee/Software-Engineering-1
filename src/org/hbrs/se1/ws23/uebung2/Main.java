package org.hbrs.se1.ws23.uebung2;

import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyStream;

public class Main {
    public Main()
    {
        Container c = Container.getInstance();
        c.setPersistance(new PersistenceStrategyStream<>());
    }
}
