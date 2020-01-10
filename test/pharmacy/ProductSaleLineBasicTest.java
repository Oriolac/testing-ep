package pharmacy;

import cat.udl.ep.data.PatientContr;
import cat.udl.ep.data.exceptions.FormatErrorException;
import cat.udl.ep.pharmacy.ProductSaleLine;
import cat.udl.ep.pharmacy.ProductSpecification;
import cat.udl.ep.pharmacy.Sale;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductSaleLineBasicTest {
    private ProductSaleLine productSaleLine;

    @BeforeEach
    public void initProductSaleLine() throws FormatErrorException {
        Sale sale = new Sale();
        ProductSpecification prodSpec = new ProductSpecification();
        BigDecimal price = new BigDecimal("10.0");
        PatientContr patientContr = new PatientContr(new BigDecimal("0.5"));
        productSaleLine = new ProductSaleLine(sale, prodSpec, price, patientContr);
    }

    @Test
    public void getSubtotalTest() {
        BigDecimal exp_subtotal = new BigDecimal("5.0");
        BigDecimal obt_subtotal = productSaleLine.getSubtotal();
        BigDecimal n_exp_subtotal = new BigDecimal("4.0");

        assertTrue(exp_subtotal.compareTo(obt_subtotal) == 0);
    }
}
