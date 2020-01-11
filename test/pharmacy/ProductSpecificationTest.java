package pharmacy;

import cat.udl.ep.data.DispensableMedicines;
import cat.udl.ep.data.PatientContr;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.data.exceptions.FormatErrorException;
import cat.udl.ep.pharmacy.*;
import cat.udl.ep.services.exceptions.ProductIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pharmacy.testinterfaces.PharmacyMethodsTest;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class ProductSpecificationTest implements PharmacyMethodsTest {

    private ProductSpecification productSpecification;
    ProductID productID;


    private ProductSpecification getProductSpecification(String code, String description, BigDecimal price) throws ProductIDException {
        productID = new ProductID("123456789154");
        description = "Medicament pel mal de cap.";
        price = new BigDecimal("5.0");
        return new ProductSpecification(productID, description, price);
    }

    @BeforeEach
    public void initProductSpec() throws ProductIDException {
        productSpecification = getProductSpecification("123456789154", "Medicament pel mal de cap", new BigDecimal("0.5"));

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

    @Override
    public void equalsTest() throws ProductIDException {
        ProductSpecification productSpecification2 = getProductSpecification("123456789154", "Medicament pel mal de cap",
                new BigDecimal("5.0"));
        assertEquals(productSpecification, productSpecification2);
    }

    @Override
    public void notEqualsTest() throws ProductIDException {
        ProductSpecification productSpecification2 = getProductSpecification("123456789123", "Medicament pel mal de cap",
                new BigDecimal("5.0"));
        assertNotEquals(productSpecification, productSpecification2);
        productSpecification2 = getProductSpecification("123456789154", "Medicament pel mal de coll",
                new BigDecimal("5.0"));
        assertNotEquals(productSpecification, productSpecification2);
        productSpecification2 = getProductSpecification("123456789154", "Medicament pel mal de cap",
                new BigDecimal("4.99"));
        assertNotEquals(productSpecification, productSpecification2);
    }

    @Override
    public void gettersTest() throws ProductIDException {
        assertEquals(new BigDecimal("5.0"), productSpecification.getPrice());
        assertEquals(new ProductID("123456789154"),productSpecification.getProdID());
        assertEquals("Medicament pel mal de cap", productSpecification.getDescription());
    }

    @Override
    public void settersTest() throws FormatErrorException {
        DispensableMedicines dispensableMedicines = new DispensableMedicines();
        Dispensing ePrescription = new Dispensing(new Date(), new Date(), dispensableMedicines);
        MedicineDispensingLine medicineDispensingLine = new MedicineDispensingLine(ePrescription, productSpecification);
        ePrescription.getDispensableMedicines().put(productID, medicineDispensingLine);
        productSpecification.setMedDispensingLine(medicineDispensingLine);
        assertEquals(medicineDispensingLine, productSpecification.getMedDispensingLine());

        SaleDouble sale = new SaleDouble();
        sale.setVoidePrescription();
        sale.putDispensingMedicine(productID, medicineDispensingLine);
        PatientContr patientContr = new PatientContr(new BigDecimal("0.1"));
        ProductSaleLine productSaleLine =
                new ProductSaleLine(sale, productSpecification, new BigDecimal("0.5"), patientContr);
        productSpecification.setProdSaleLine(productSaleLine);
        assertEquals(productSaleLine, productSpecification.getProdSaleLine());

    }
    static class SaleDouble implements SaleInt {

        private Dispensing ePrescription;

        public SaleDouble() {
            this.ePrescription = null;
        }

        public void setVoidePrescription(){
            ePrescription = new Dispensing(new Date(), new Date(), new DispensableMedicines());
        }

        public void putDispensingMedicine(ProductID productID, MedicineDispensingLine medicineDispensingLine){
            ePrescription.getDispensableMedicines().put(productID, medicineDispensingLine);
        }


        @Override
        public Dispensing getePrescription() {
            return ePrescription;
        }
    }
}
