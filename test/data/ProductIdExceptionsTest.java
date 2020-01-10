import cat.udl.ep.data.ProductID;
import cat.udl.ep.data.exceptions.FormatErrorException;
import org.junit.jupiter.api.Test;
import testInterfaces.DataClassesInterfaceTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductIdExceptionsTest implements DataClassesInterfaceTest {
    private ProductID productID;

    @Override
    @Test
    public void nullConstructorParameterTest() {
        Throwable exception = assertThrows(NullPointerException.class,
                () -> productID = new ProductID(null));
    }

    @Override
    @Test
    public void getValueTest() throws FormatErrorException {
        productID = new ProductID("123456789154");
        String code = "123456789154";
        assertTrue(productID.getProductCode().equals(code));
    }

    @Override
    @Test
    public void equalsTest() throws FormatErrorException {
        ProductID productId1, productId2;
        productId1 = new ProductID("123456789012");
        productId2 = new ProductID("123456789012");
        assertTrue(productId1.equals(productId2));
    }

    @Override
    @Test
    public void notEqualsTest() throws FormatErrorException {
        ProductID productId1, productId2;
        productId1 = new ProductID("123456789012");
        productId2 = new ProductID("123456789011");

        assertFalse(productId1.equals(productId2));
        assertFalse(productId1.equals(null));
        assertFalse(productId1.equals("123456789012"));
    }

    @Override
    @Test
    public void formatErrorExceptionTest() {
        Throwable exception = assertThrows(FormatErrorException.class,
                () -> productID = new ProductID("12345678901"));
        exception = assertThrows(FormatErrorException.class,
                () -> productID = new ProductID("12345678901a"));
    }
}
