package testInterfaces;

import data.exceptions.FormatErrorException;
import org.junit.jupiter.api.Test;

public interface DataClassesInterfaceTest {
    @Test
    void nullConstructorParameterTest();

    @Test
    void getValueTest() throws FormatErrorException;

    @Test
    void equalsTest() throws FormatErrorException;

    @Test
    void notEqualsTest() throws FormatErrorException;

    @Test
    void formatErrorExceptionTest();
}
