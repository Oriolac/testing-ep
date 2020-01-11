package data.HealthCardID;

import cat.udl.ep.data.HealthCardID;
import cat.udl.ep.data.exceptions.PatientIDException;
import cat.udl.ep.services.exceptions.HealthCardException;
import data.testInterfaces.DataMethodsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HealthCardIdMethodsTest implements DataMethodsTest {

    private HealthCardID healthCardId1;
    private String code = "IBMI473298320193";

    @BeforeEach
    public void init() throws PatientIDException {
        healthCardId1 = new HealthCardID(code);
    }

    @Test
    public void getValueTest() {
        assertEquals(healthCardId1.getPersonalID(), code);
    }

    @Test
    public void equalsTest() throws PatientIDException {
        HealthCardID healthCardId2;
        healthCardId2 = new HealthCardID(code);
        assertEquals(healthCardId1, healthCardId2);
    }

    @Test
    public void notEqualsTest() throws PatientIDException {
        HealthCardID healthCardId2 = new HealthCardID("ZZZZ473298320193");
        assertNotEquals(healthCardId1, healthCardId2);
        assertNotEquals(null, healthCardId1);
        assertNotEquals(healthCardId1, code);
    }

    @Override
    @Test
    public void formatErrorExceptionTest() {
        assertThrows(PatientIDException.class,
                () ->  new HealthCardID("IBMI47329832019"));
        assertThrows(PatientIDException.class,
                () ->  new HealthCardID("B325897653492301"));
        assertThrows(PatientIDException.class,
                () ->  new HealthCardID("BINO89765Z492301"));
    }

}
