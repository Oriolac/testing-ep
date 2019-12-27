import data.HealthCardID;
import data.exceptions.FormatErrorException;
import org.junit.jupiter.api.Test;
import testInterfaces.DataClassesInterfaceTest;

import static org.junit.jupiter.api.Assertions.*;

public class HealthCardIdExceptionsTest implements DataClassesInterfaceTest {
    private HealthCardID healthCardId;

    @Override
    @Test
    public void nullConstructorParameterTest() {
        Throwable exception = assertThrows(NullPointerException.class,
                () -> healthCardId = new HealthCardID(null));
    }

    @Override
    @Test
    public void getValueTest() throws FormatErrorException {
        healthCardId = new HealthCardID("IBMI473298320193");
        String code = "IBMI473298320193";
        assertTrue(healthCardId.getPersonalID().equals(code));
    }

    @Override
    @Test
    public void equalsTest() throws FormatErrorException {
        HealthCardID healthCardId1, healthCardId2;
        healthCardId1 = new HealthCardID("IBMI473298320193");
        healthCardId2 = new HealthCardID("IBMI473298320193");
        assertTrue(healthCardId1.equals(healthCardId2));
    }

    @Override
    @Test
    public void notEqualsTest() throws FormatErrorException {
        HealthCardID healthCardId1, healthCardId2;
        healthCardId1 = new HealthCardID("IBMI473298320193");
        healthCardId2 = new HealthCardID("ZZZZ473298320193");

        assertFalse(healthCardId1.equals(healthCardId2));
        assertFalse(healthCardId1.equals(null));
        assertFalse(healthCardId1.equals("IBMI473298320193"));
    }

    @Override
    @Test
    public void formatErrorExceptionTest() {
        Throwable exception = assertThrows(FormatErrorException.class,
                () -> healthCardId = new HealthCardID("IBMI47329832019"));
        exception = assertThrows(FormatErrorException.class,
                () -> healthCardId = new HealthCardID("B325897653492301"));
        exception = assertThrows(FormatErrorException.class,
                () -> healthCardId = new HealthCardID("BINO89765Z492301"));
    }

}
