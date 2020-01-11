package data.testInterfaces;

import cat.udl.ep.data.exceptions.FormatErrorException;
import cat.udl.ep.data.exceptions.IDException;
import org.junit.jupiter.api.Test;

public interface DataMethodsTest {

    @Test
    void getValueTest() throws FormatErrorException;

    @Test
    void equalsTest() throws IDException, FormatErrorException;

    @Test
    void notEqualsTest() throws FormatErrorException, IDException;

    @Test
    void formatErrorExceptionTest();
}
