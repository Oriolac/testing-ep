package pharmacy;

import cat.udl.ep.data.ProductID;
import cat.udl.ep.pharmacy.ProductSpecification;
import cat.udl.ep.services.exceptions.ProductIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(0, exp_price.compareTo(obt_price));
        assertTrue(n_exp_price.compareTo(obt_price) < 0);
    }

    @Test
    public void getDescriptionTest() {
        String exp_descr = "Medicament pel mal de cap.";
        String obt_descr = productSpecification.getDescription();
        String n_exp_descr = "Medicament pel mal de coll.";
        assertEquals(exp_descr, obt_descr);
        assertNotEquals(n_exp_descr, obt_descr);
    }
}
