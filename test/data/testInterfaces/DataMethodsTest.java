package data.testInterfaces;

import cat.udl.ep.data.exceptions.FormatErrorException;
import cat.udl.ep.services.exceptions.HealthCardException;
import cat.udl.ep.services.exceptions.ProductIDException;
import org.junit.jupiter.api.Test;

public interface DataMethodsTest {

    @Test
    void getValueTest() throws HealthCardException, FormatErrorException;

    @Test
    void equalsTest() throws HealthCardException, FormatErrorException, ProductIDException;

    @Test
    void notEqualsTest() throws HealthCardException, FormatErrorException, ProductIDException;

    @Test
    void formatErrorExceptionTest();
}
