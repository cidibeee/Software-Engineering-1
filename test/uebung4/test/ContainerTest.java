package uebung4.test;

import org.hbrs.se1.ws23.uebung4.prototype.ContainerException;
import org.hbrs.se1.ws23.uebung4.prototype.UserStory;
import org.junit.jupiter.api.Test;
import org.hbrs.se1.ws23.uebung4.prototype.Container;

import static org.junit.jupiter.api.Assertions.*;

public class ContainerTest {
    @Test
    void singeltonTest()
    {
        Container a = Container.getInstance();
        Container b = Container.getInstance();
        assertSame(a, b);
    }

    @Test
    void storeloadTest() throws ContainerException {
        Container container = Container.getInstance();
        UserStory uss = new UserStory(1, "test", "alle tests positiv", 1, 2, 3, 4, "erstes");
        container.addUserStory(uss);
        assertThrows(ContainerException.class, ()-> container.addUserStory(uss));
    }
}
