package uebung2.test;

import org.hbrs.se1.ws23.uebung2.*;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceException;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyMongoDB;
import org.hbrs.se1.ws23.uebung3.persistence.PersistenceStrategyStream;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContainerTest {
    @Test
    void adddelete() throws ContainerException {
        Container c = Container.getInstance();
        assertEquals(0, c.size());
        Member m1 = new ConcreteMember();
        Member m2 = new ConcreteMember();
        c.addMember(m1);
        assertEquals(1, c.size());
        assertThrows(ContainerException.class, () -> c.addMember(m1));
        c.deleteMember(m1.getID());
        assertEquals(0, c.size());
        assertEquals("Die eingegebene ID ist NICHT in vorhanden!", c.deleteMember(m1.getID()));
        c.addMember(m1);
        c.addMember(m2);
        MemberView.dump(c.getCurrentList());
        assertEquals(2, c.size());
        assertEquals("Erfolgreich entfernt!", c.deleteMember(m2.getID()));
    }


    @Test
    void MongoDBException()
    {
        Container c = Container.getInstance();
        c.setPersistance(new PersistenceStrategyMongoDB<Member>());
        assertThrows(UnsupportedOperationException.class, c::store);
    }

    @Test
    void wrongLocation()
    {
        Container c = Container.getInstance();
        PersistenceStrategyStream<Member> p = new PersistenceStrategyStream<>();
        c.setPersistance(p);
        try {
            c.addMember(new ConcreteMember());
            c.addMember(new ConcreteMember());
            c.addMember(new ConcreteMember());
        } catch (ContainerException e) {
            throw new RuntimeException(e);
        }
        try {
            c.store();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
        p.setLocation("abc.txt");
        assertThrows(PersistenceException.class, () -> c.load());

    }

    @Test
    void roundTripTest()
    {
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
        assertEquals(c.size(), 3);

        try {
            c.store();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
        assertEquals(c.size(), 3);

        c.deleteMember(m1.getID());

        assertEquals(c.size(), 2);

        try {
            c.load();
        } catch (PersistenceException e) {
            throw new RuntimeException(e);
        }
        assertEquals(c.size(), 3);
    }
}


