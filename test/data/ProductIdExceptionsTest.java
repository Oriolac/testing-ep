package data;

import data.exceptions.ProductIDException;
import data.testInterfaces.DataExceptionsTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductIdExceptionsTest implements DataExceptionsTest {
    private ProductID productID;

    @Override
    @Test
    public void nullConstructorParameterTest() {
        Throwable exception = assertThrows(NullPointerException.class,
                () -> productID = new ProductID(null));
    }

    @Test
    public void getValueTest() throws ProductIDException {
        productID = new ProductID("123456789154");
        String code = "123456789154";
        assertTrue(productID.getProductCode().equals(code));
    }

    @Test
    public void equalsTest() throws ProductIDException {
        ProductID productId1, productId2;
        productId1 = new ProductID("123456789012");
        productId2 = new ProductID("123456789012");
        assertTrue(productId1.equals(productId2));
    }

    @Test
    public void notEqualsTest() throws ProductIDException {
        ProductID productId1, productId2;
        productId1 = new ProductID("123456789012");
        productId2 = new ProductID("123456789011");

        assertFalse(productId1.equals(productId2));
        assertFalse(productId1.equals(null));
        assertFalse(productId1.equals("123456789012"));
    }

    @Override
    @Test
    public void dataErrorExceptionTest() {
        Throwable exception = assertThrows(ProductIDException.class,
                () -> productID = new ProductID("12345678901"));
        exception = assertThrows(ProductIDException.class,
                () -> productID = new ProductID("12345678901a"));
    }
}
