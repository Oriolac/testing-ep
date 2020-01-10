package data.testInterfaces;

import cat.udl.ep.data.exceptions.FormatErrorException;
import cat.udl.ep.services.exceptions.HealthCardException;
import org.junit.jupiter.api.Test;

public interface NullInterfaceTest {


    @Test
    void nullEqualsParameterTest() throws HealthCardException, FormatErrorException;

}
