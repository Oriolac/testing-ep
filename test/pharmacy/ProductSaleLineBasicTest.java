package pharmacy;

import cat.udl.ep.DispensingTerminal;
import cat.udl.ep.data.PatientContr;
import cat.udl.ep.data.Payment;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.data.exceptions.FormatErrorException;
import cat.udl.ep.pharmacy.*;
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

public class ProductSaleLineBasicTest implements PharmacyMethodsTest {
    private ProductSaleLine productSaleLine;
    ProductSpecification prodSpec;
    SaleInt sale;
    PatientContr patientContr;
    String codeProductID = "123456789012";

    private ProductID getProductID(String code) throws ProductIDException {
        return new ProductID(code);
    }

    private ProductSaleLine getProductSaleLine(String code, String description, BigDecimal price, BigDecimal contr) throws FormatErrorException {
        sale = new SaleDouble();
        prodSpec = new ProductSpecification(getProductID(code), description, price);
        patientContr = new PatientContr(contr);
        return new ProductSaleLine(sale, prodSpec, price, patientContr);
    }

    @BeforeEach
    public void initProductSaleLine() throws FormatErrorException {
        productSaleLine = getProductSaleLine(codeProductID, "DESC", BigDecimal.ONE, new BigDecimal("0.5"));
    }

    @Test
    public void getSubtotalTest() {
        BigDecimal exp_subtotal = new BigDecimal("0.5");
        BigDecimal n_exp_subtotal = new BigDecimal("4.0");
        assertEquals(exp_subtotal, productSaleLine.getSubtotal());
        assertNotEquals(n_exp_subtotal, productSaleLine.getSubtotal());
    }

    @Override
    @Test
    public void equalsTest() throws FormatErrorException {
        ProductSaleLine productSaleLine1;
        productSaleLine1 = getProductSaleLine("123456789012", "DESC", BigDecimal.ONE, new BigDecimal("0.5"));
        assertEquals(productSaleLine1, productSaleLine);
    }

    @Override
    @Test
    public void notEqualsTest() throws FormatErrorException {
        ProductSaleLine productSaleLine1;
        productSaleLine1 = getProductSaleLine("222222222222", "DESC", BigDecimal.ONE, new BigDecimal("0.5"));
        assertNotEquals(productSaleLine1, productSaleLine);
        productSaleLine1 = getProductSaleLine("123456789012", "", BigDecimal.ONE, new BigDecimal("0.5"));
        assertNotEquals(productSaleLine1, productSaleLine);
        productSaleLine1 = getProductSaleLine("123456789012", "DESC", BigDecimal.TEN, new BigDecimal("0.5"));
        assertNotEquals(productSaleLine1, productSaleLine);
        productSaleLine1 = getProductSaleLine("123456789012", "DESC", BigDecimal.ONE, new BigDecimal("0.1"));
        assertNotEquals(productSaleLine1, productSaleLine);
    }

    @Override
    @Test
    public void gettersTest() throws ProductIDException {
        assertEquals(new BigDecimal("0.5"), productSaleLine.getSubtotal());
        assertNull(productSaleLine.getMedDispensingLine()); //TODO: Mirar en altres casos
        assertEquals(prodSpec, productSaleLine.getProductSpec());
        assertEquals(sale, productSaleLine.getSale());

        SaleDouble sale2 = new SaleDouble();
        sale2.setVoidePrescription();
        productSaleLine = new ProductSaleLine(sale2, prodSpec, BigDecimal.ONE, patientContr);
        assertNull(productSaleLine.getMedDispensingLine());

        MedicineDispensingLine medicineDispensingLine = new MedicineDispensingLine(sale2.getePrescription(), prodSpec);
        sale2.putDispensingMedicine(getProductID(codeProductID), medicineDispensingLine);
        productSaleLine = new ProductSaleLine(sale2, prodSpec, BigDecimal.ONE, patientContr);
        assertEquals(medicineDispensingLine ,productSaleLine.getMedDispensingLine());
    }

    @Override
    @Test
    public void settersTest() {

    }

    static class SaleDouble implements SaleInt {

        private Dispensing ePrescription;

        public SaleDouble() {
            ePrescription = null;
        }

        public void setVoidePrescription(){
            ePrescription = new Dispensing(new Date(), new Date());
        }

        public void putDispensingMedicine(ProductID productID, MedicineDispensingLine medicineDispensingLine){
            ePrescription.getDispensableMedicines().put(productID, medicineDispensingLine);
        }


        @Override
        public Dispensing getePrescription() {
            return ePrescription;
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
    }
}
