package pharmacy;

import cat.udl.ep.data.PatientContr;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.data.exceptions.FormatErrorException;
import cat.udl.ep.pharmacy.*;
import cat.udl.ep.data.DispensableMedicines;
import cat.udl.ep.services.exceptions.ProductIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pharmacy.testinterfaces.PharmacyMethodsTest;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class MedicineDispensingLineBasicTest implements PharmacyMethodsTest {
    private MedicineDispensingLine medDispensingLine;
    ProductSpecification prodSpec;
    DispensableMedicines dispensableMedicines;
    Dispensing ePrescription;

    private MedicineDispensingLine medicineDispensingLine(String code, String description, BigDecimal bigDecimal) throws ProductIDException {
        prodSpec = new ProductSpecification(new ProductID(code),description, bigDecimal);
        dispensableMedicines = new DispensableMedicines();
        ePrescription = new Dispensing(new Date(), new Date(), dispensableMedicines);
        return new MedicineDispensingLine(ePrescription, prodSpec);
    }

    @BeforeEach
    public void initMedDispensingLine() throws ProductIDException, ConnectException {
        medDispensingLine = medicineDispensingLine("123456789012", "DESCRIPTION", BigDecimal.ONE);
    }


    @Test
    public void isAcquiredTest() {
        assertFalse(medDispensingLine.isAcquired());
    }

    @Override
    public void equalsTest() throws ProductIDException, ConnectException {
        MedicineDispensingLine medDispensingLine2 = medicineDispensingLine("123456789012", "DESCRIPTION", BigDecimal.ONE);
        assertTrue(medDispensingLine.equals(medDispensingLine2));
    }

    @Override
    public void notEqualsTest() throws ProductIDException, ConnectException {
        MedicineDispensingLine medDispensingLine2 = medicineDispensingLine("123456789012", "", BigDecimal.ONE);
        assertFalse(medDispensingLine.equals(medDispensingLine2));
        medDispensingLine2 = medicineDispensingLine("123456789012", "DESCRIPTION", BigDecimal.TEN);
        assertFalse(medDispensingLine.equals(medDispensingLine2));
        medDispensingLine2 = medicineDispensingLine("222222222222", "DESCRIPTION", BigDecimal.TEN);
        assertFalse(medDispensingLine.equals(medDispensingLine2));
    }

    @Override
    public void gettersTest() {
        assertEquals(prodSpec, medDispensingLine.getProductSpec());
        assertEquals(ePrescription, medDispensingLine.getePrescription());
    }

    @Override
    public void settersTest() throws FormatErrorException {
        medDispensingLine.setAcquired();
        assertTrue(medDispensingLine.isAcquired());
        ProductSaleLine productSaleLine = new ProductSaleLine(new SaleDouble(), prodSpec, BigDecimal.ONE, new PatientContr(BigDecimal.ZERO));
        medDispensingLine.setProductSaleLine(productSaleLine);
        assertEquals(productSaleLine, medDispensingLine.getProductSaleLine());
    }


    public static class SaleDouble implements SaleInt {

        public SaleDouble(){

        }

        @Override
        public Dispensing getePrescription() {
            return null;
        }
    }
}
