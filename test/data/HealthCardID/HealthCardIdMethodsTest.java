package data.HealthCardID;

import data.HealthCardID;
import data.exceptions.HealthCardException;
import data.testInterfaces.DataMethodsTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class HealthCardIdMethodsTest implements DataMethodsTest {

    private HealthCardID healthCardId1;
    private String code = "IBMI473298320193";

    @Test
    public void getValueTest() throws HealthCardException {
        healthCardId1 = new HealthCardID(code);
        assertEquals(healthCardId1.getPersonalID(), code);
    }

    @Test
    public void equalsTest() throws HealthCardException {
        HealthCardID healthCardId1, healthCardId2;
        healthCardId1 = new HealthCardID(code);
        healthCardId2 = new HealthCardID(code);
        assertEquals(healthCardId1, healthCardId2);
    }

    @Test
    public void notEqualsTest() throws HealthCardException {
        healthCardId1 = new HealthCardID(code);
        HealthCardID healthCardId2 = new HealthCardID("ZZZZ473298320193");

        assertNotEquals(healthCardId1, healthCardId2);
        assertNotEquals(null, healthCardId1);
        assertNotEquals(healthCardId1, code);
    }

}
