package data.ProductID;

import cat.udl.ep.data.ProductID;
import cat.udl.ep.data.exceptions.FormatErrorException;
import cat.udl.ep.services.exceptions.ProductIDException;
import data.testInterfaces.DataMethodsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ProductIdMethodsTest implements DataMethodsTest {

    private ProductID productId1;
    private String code = "123456789012";

    @BeforeEach
    public void set() throws ProductIDException {
        productId1 = new ProductID(code);
    }

    @Override
    public void getValueTest() {
        assertEquals("123456789012", productId1.getProductCode());
    }

    @Override
    @Test
    public void equalsTest() throws ProductIDException {
        ProductID productId2;
        productId2 = new ProductID(code);
        assertEquals(productId1, productId2);
    }

    @Override
    @Test
    public void notEqualsTest() throws ProductIDException {
        ProductID productId1, productId2;
        productId1 = new ProductID(code);
        productId2 = new ProductID("123456789011");

        assertNotEquals(productId1, productId2);
        assertNotEquals(null, productId1);
        assertNotEquals(code, productId1);
    }

    @Test
    public void formatErrorExceptionTest() {
        assertThrows(ProductIDException.class,
                () -> new ProductID("12345678901"));
        assertThrows(ProductIDException.class,
                () -> new ProductID("12345678901a"));
    }
}
