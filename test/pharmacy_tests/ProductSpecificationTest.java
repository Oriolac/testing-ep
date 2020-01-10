package pharmacy_tests;

import data.ProductID;
import data.exceptions.ProductIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pharmacy.ProductSpecification;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductSpecificationTest {
    private ProductSpecification productSpecification;

    @BeforeEach
    public void initProductSpec() throws ProductIDException {
        ProductID productID = new ProductID("123456789154");
        String description = "Medicament pel mal de cap.";
        BigDecimal price = new BigDecimal("5.0");
        productSpecification = new ProductSpecification(productID, description, price);
    }

    @Test
    public void getPriceTest() {
        BigDecimal exp_price = new BigDecimal("5.0");
        BigDecimal obt_price = productSpecification.getPrice();
        BigDecimal n_exp_price = new BigDecimal("2.0");
        assertTrue(exp_price.compareTo(obt_price) == 0);
        assertTrue(n_exp_price.compareTo(obt_price) < 0);
    }

    @Test
    public void getDescriptionTest() {
        String exp_descr = "Medicament pel mal de cap.";
        String obt_descr = productSpecification.getDescription();
        String n_exp_descr = "Medicament pel mal de coll.";
        assertTrue(exp_descr.equals(obt_descr));
        assertFalse(n_exp_descr.equals(obt_descr));
    }
}
