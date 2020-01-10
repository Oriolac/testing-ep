package data.testInterfaces;

import data.HealthCardID;
import data.exceptions.HealthCardException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public interface DataMethodsTest {

    @Test
    void getValueTest() throws HealthCardException;

    @Test
    void equalsTest() throws HealthCardException;

    @Test
    void notEqualsTest() throws HealthCardException;
}
