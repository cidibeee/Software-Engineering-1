package uebung2.test;

import org.hbrs.se1.ws23.uebung2.ConcreteMember;
import org.hbrs.se1.ws23.uebung2.Container;
import org.hbrs.se1.ws23.uebung2.ContainerException;
import org.hbrs.se1.ws23.uebung2.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContainerTest {
    @Test
    void adddelete() throws ContainerException {
        Container c = new Container();
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
        c.dump();
        assertEquals(2, c.size());
        assertEquals("Erfolgreich entfernt!", c.deleteMember(m2.getID()));
    }
}
