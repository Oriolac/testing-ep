import data.HealthCardID;
import data.exceptions.FormatErrorException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HealthCardIdExceptionsTest {
    private HealthCardID healthCardId;

    @Test
    public void nullConstructorParameterTest() {
        Throwable exception = assertThrows(NullPointerException.class,
                () -> healthCardId = new HealthCardID(null));
    }

    @Test
    public void getPersonalIdTest() throws FormatErrorException {
        healthCardId = new HealthCardID("IBMI473298320193");
        String code = "IBMI473298320193";
        assertTrue(healthCardId.getPersonalID().equals(code));
    }

    @Test
    public void equalsTest() throws FormatErrorException {
        HealthCardID healthCardId1, healthCardId2;
        healthCardId1 = new HealthCardID("IBMI473298320193");
        healthCardId2 = new HealthCardID("IBMI473298320193");
        assertTrue(healthCardId1.equals(healthCardId2));
    }

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
