package org.hbrs.se1.ws23.uebung2;

import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyStream;

public class Client {
    public static void main(String[] args) {
        new Main();
        Container c = Container.getInstance();
        Member m1 = new ConcreteMember();
        Member m2 = new ConcreteMember();
        Member m3 = new ConcreteMember();
        try {
            c.addMember(m1);
            c.addMember(m2);
            c.addMember(m3);
        } catch (ContainerException e) {
            throw new RuntimeException(e);
        }

        MemberView.dump(c.getCurrentList());

    }
}
