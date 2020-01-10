package data.testInterfaces;

import data.HealthCardID;
import data.exceptions.FormatErrorException;
import data.exceptions.HealthCardException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public interface DataMethodsTest {

    @Test
    void getValueTest() throws HealthCardException, FormatErrorException;

    @Test
    void equalsTest() throws HealthCardException, FormatErrorException;

    @Test
    void notEqualsTest() throws HealthCardException, FormatErrorException;
}
