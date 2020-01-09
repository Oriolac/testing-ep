package pharmacy_tests;

import data.PatientContr;
import data.exceptions.FormatErrorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pharmacy.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductSaleLineBasicTest {
    private ProductSaleLine productSaleLine;

    @BeforeEach
    public void initProductSaleLine() throws FormatErrorException {
        Sale sale = new Sale();
        MedicineDispensingLine medDispensingLine = new MedicineDispensingLine();
        BigDecimal price = new BigDecimal("10.0");
        PatientContr patientContr = new PatientContr(new BigDecimal("0.5"));
        productSaleLine = new ProductSaleLine(sale, medDispensingLine, price, patientContr);
    }

    @Test
    public void getSubtotalTest() {
        BigDecimal exp_subtotal = new BigDecimal("5.0");
        BigDecimal obt_subtotal = productSaleLine.getSubtotal();
        BigDecimal n_exp_subtotal = new BigDecimal("4.0");

        assertTrue(exp_subtotal.compareTo(obt_subtotal) == 0);
        assertTrue(n_exp_subtotal.compareTo(obt_subtotal) < 0);
    }
}
