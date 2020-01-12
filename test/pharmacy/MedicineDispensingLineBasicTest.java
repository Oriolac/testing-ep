package pharmacy;

import cat.udl.ep.DispensingTerminal;
import cat.udl.ep.data.PatientContr;
import cat.udl.ep.data.Payment;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.data.exceptions.FormatErrorException;
import cat.udl.ep.pharmacy.*;
import cat.udl.ep.data.DispensableMedicines;
import cat.udl.ep.data.exceptions.ProductIDException;
import cat.udl.ep.pharmacy.exceptions.ProductNotInDispensingException;
import cat.udl.ep.pharmacy.exceptions.SaleClosedException;
import cat.udl.ep.services.exceptions.ProductNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pharmacy.testinterfaces.PharmacyMethodsTest;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MedicineDispensingLineBasicTest implements PharmacyMethodsTest {
    private MedicineDispensingLine medDispensingLine;
    ProductSpecification prodSpec;
    DispensableMedicines dispensableMedicines;
    Dispensing ePrescription;

    private MedicineDispensingLine medicineDispensingLine(String code, String description, BigDecimal bigDecimal) throws ProductIDException {
        prodSpec = new ProductSpecification(new ProductID(code),description, bigDecimal);
        ePrescription = new Dispensing(Byte.MIN_VALUE, new Date(), new Date());
        return new MedicineDispensingLine(ePrescription, prodSpec);
    }

    @BeforeEach
    public void initMedDispensingLine() throws ProductIDException {
        medDispensingLine = medicineDispensingLine("123456789012", "DESCRIPTION", BigDecimal.ONE);
    }


    @Test
    public void isAcquiredTest() {
        assertFalse(medDispensingLine.isAcquired());
    }

    @Override
    @Test
    public void equalsTest() throws ProductIDException {
        MedicineDispensingLine medDispensingLine2 = medicineDispensingLine("123456789012", "DESCRIPTION", BigDecimal.ONE);
        assertTrue(medDispensingLine.equals(medDispensingLine2));
    }

    @Override
    @Test
    public void notEqualsTest() throws ProductIDException {
        MedicineDispensingLine medDispensingLine2 = medicineDispensingLine("123456789012", "", BigDecimal.ONE);
        assertFalse(medDispensingLine.equals(medDispensingLine2));
        medDispensingLine2 = medicineDispensingLine("123456789012", "DESCRIPTION", BigDecimal.TEN);
        assertFalse(medDispensingLine.equals(medDispensingLine2));
        medDispensingLine2 = medicineDispensingLine("222222222222", "DESCRIPTION", BigDecimal.TEN);
        assertFalse(medDispensingLine.equals(medDispensingLine2));
    }

    @Override
    @Test
    public void gettersTest() {
        assertEquals(prodSpec, medDispensingLine.getProductSpec());
        assertEquals(ePrescription, medDispensingLine.getePrescription());
    }

    @Override
    @Test
    public void settersTest() throws FormatErrorException {
        medDispensingLine.setAcquired();
        assertTrue(medDispensingLine.isAcquired());
        ProductSaleLine productSaleLine = new ProductSaleLine(new SaleDouble(), prodSpec, BigDecimal.ONE, new PatientContr(BigDecimal.ZERO));
        medDispensingLine.setProductSaleLine(productSaleLine);
        assertEquals(productSaleLine, medDispensingLine.getProductSaleLine());
    }


    public static class SaleDouble implements SaleInt {

        private Dispensing ePrescription;

        public SaleDouble(){

        }

        @Override
        public Dispensing getePrescription() {
            return null;
        }

        @Override
        public boolean isClosed() {
            return false;
        }

        @Override
        public void addLine(ProductID pID, BigDecimal price, PatientContr patientContr) {

        }

        @Override
        public void calculateFinalAmount() {

        }

        @Override
        public BigDecimal getAmount() {
            return null;
        }

        @Override
        public void setPayment(Payment payment) {

        }

        @Override
        public List<ProductSaleLine> getProductSaleLines() {
            return null;
        }

        @Override
        public DispensingTerminal getDispensingTerminal() {
            return null;
        }

        @Override
        public ProductSaleLine getProductSaleLine(ProductID prod1) {
            return null;
        }

        @Override
        public Payment getPayment() {
            return null;
        }

        @Override
        public int getSaleCode() {
            return 0;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof SaleDouble) {
                SaleDouble sale = (SaleDouble) obj;
                return ePrescription.equals(sale.getePrescription());
            }
            return false;
        }
    }
}
