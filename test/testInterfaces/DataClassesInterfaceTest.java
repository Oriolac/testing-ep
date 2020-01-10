package testInterfaces;

import cat.udl.ep.data.exceptions.FormatErrorException;
import cat.udl.ep.services.exceptions.HealthCardException;
import org.junit.jupiter.api.Test;

public interface DataClassesInterfaceTest {
    @Test
    void nullConstructorParameterTest();

    @Test
    void getValueTest() throws FormatErrorException, HealthCardException;

    @Test
    void equalsTest() throws FormatErrorException, HealthCardException;

    @Test
    void notEqualsTest() throws FormatErrorException, HealthCardException;

    @Test
    void formatErrorExceptionTest();
}
