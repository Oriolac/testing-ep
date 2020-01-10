package data.ProductID;

import cat.udl.ep.data.ProductID;
import cat.udl.ep.data.exceptions.FormatErrorException;
import cat.udl.ep.services.exceptions.HealthCardException;
import data.testInterfaces.DataExceptionsTest;
import data.testInterfaces.NullInterfaceTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductIdExceptionsTest implements DataExceptionsTest , NullInterfaceTest {

    private ProductID productID;


    @Override
    @Test
    public void nullConstructorParameterTest() {
        assertThrows(NullPointerException.class,
                () -> productID = new ProductID(null));
    }

    @Override
    public void dataErrorExceptionTest() {

    }

    @Override
    public void nullEqualsParameterTest() {

    }
}
