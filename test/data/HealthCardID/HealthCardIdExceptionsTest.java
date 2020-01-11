package data.HealthCardID;

import cat.udl.ep.data.HealthCardID;
import cat.udl.ep.data.exceptions.HealthCardException;
import data.testInterfaces.DataExceptionsTest;
import data.testInterfaces.NullInterfaceTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HealthCardIdExceptionsTest implements DataExceptionsTest, NullInterfaceTest {


    @Override
    @Test
    public void nullConstructorParameterTest() {
        assertThrows(NullPointerException.class,
                () -> new HealthCardID(null));
    }

    @Override
    @Test
    public void dataErrorExceptionTest() {
        assertThrows(HealthCardException.class,
                () -> new HealthCardID("IBMI47329832019"));
        assertThrows(HealthCardException.class,
                () -> new HealthCardID("B325897653492301"));
        assertThrows(HealthCardException.class,
                () -> new HealthCardID("BINO89765Z492301"));
    }

    @Override
    public void nullEqualsParameterTest() throws HealthCardException {
        HealthCardID healthCardID = new HealthCardID("IBMI473298320192");
        assertNotEquals(null, healthCardID);
    }
}
