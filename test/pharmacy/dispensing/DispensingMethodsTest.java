package pharmacy.dispensing;

import cat.udl.ep.DispensingTerminal;
import cat.udl.ep.data.DispensableMedicines;
import cat.udl.ep.data.HealthCardID;
import cat.udl.ep.data.PatientContr;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.data.exceptions.FormatErrorException;
import cat.udl.ep.pharmacy.Dispensing;
import cat.udl.ep.pharmacy.MedicineDispensingLine;
import cat.udl.ep.pharmacy.ProductSpecification;
import cat.udl.ep.pharmacy.Sale;
import cat.udl.ep.pharmacy.exceptions.*;
import cat.udl.ep.services.HealthCardReader;
import cat.udl.ep.services.NationalHealthService;
import cat.udl.ep.services.exceptions.HealthCardException;
import cat.udl.ep.services.exceptions.ProductIDException;
import org.junit.jupiter.api.BeforeEach;
import pharmacy.testinterfaces.PharmacyMethodsTest;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DispensingMethodsTest implements PharmacyMethodsTest {

    Dispensing ePrescription;
    DispensableMedicines dispensableMedicines;
    Instant now;


    private Dispensing getVoidePrescription(int id) {
        return getVoidePrescription(id, Date.from(now.minusSeconds(10L)),
                Date.from(now.plusSeconds(10L)));
    }

    private Dispensing getVoidePrescription(int id, Date initD, Date finalD) {
        return new Dispensing((byte) id, initD, finalD);
    }

    @BeforeEach
    public void init() {
        now = Instant.now();
        ePrescription = getVoidePrescription(0);
    }

    @Override
    public void equalsTest() throws ProductIDException, ConnectException, FormatErrorException {
        Dispensing ePrescriptionSameOrder = getVoidePrescription(0);
        assertEquals(ePrescription, ePrescriptionSameOrder);

        Dispensing ePrescriptionDiffTime = getVoidePrescription(0, Date.from(now.minusSeconds(10L)),
                Date.from(now.plusSeconds(5L)));
        assertEquals(ePrescription, ePrescriptionDiffTime);


    }

    @Override
    public void notEqualsTest() throws ProductIDException, ConnectException, FormatErrorException {
        Dispensing diffEPrescription = getVoidePrescription(1);
        assertNotEquals(ePrescription, diffEPrescription);
    }

    @Override
    public void gettersTest() throws ProductIDException {

        assertEquals(Date.from(now.minusSeconds(10L)), ePrescription.getInitDate());
        assertEquals(Date.from(now.plusSeconds(10L)), ePrescription.getFinalDate());
        assertEquals(dispensableMedicines, ePrescription.getDispensableMedicines());

        ProductID productId = new ProductID("1234567890123");
        ProductSpecification prodS = new ProductSpecification(productId, "DESC", BigDecimal.TEN);
        MedicineDispensingLine medicineDispensingLine = new MedicineDispensingLine(ePrescription, prodS);
        dispensableMedicines.put(productId, medicineDispensingLine);

        assertEquals(medicineDispensingLine, ePrescription.getMedicineDispensingLine(productId));
        assertEquals(prodS, ePrescription.getProductSpec(productId));

        assertFalse(ePrescription.isCompleted());
    }

    @Override
    public void settersTest() throws ProductIDException, DispensingException {
        ProductID productId = new ProductID("1234567890123");

        ePrescription.setProductAsDispensed(productId);
        assertTrue(ePrescription.getMedicineDispensingLine(productId).isAcquired());

        assertFalse(ePrescription.isCompleted());
        ePrescription.setCompleted();
        assertTrue(ePrescription.isCompleted());

        DispensingTerminal dt = new DispensingTerminal(new SNS(), new HCR());
        ePrescription.setDispensingTerminal(dt);

        ePrescription.setSale(new Sale(dt, ePrescription));
    }

    static class SNS implements NationalHealthService {

        public SNS() { }

        @Override
        public Dispensing getePrescription(HealthCardID hcID) {
            return null;
        }

        @Override
        public PatientContr getPatientContr(HealthCardID hcID) {
            return null;
        }

        @Override
        public ProductSpecification getProductSpecific(ProductID pID) {
            return null;
        }

        @Override
        public List<Dispensing> updateePrescription(HealthCardID hcID, Dispensing disp) {
            return null;
        }
    }

    static class HCR implements HealthCardReader {

        public HCR() {}

        @Override
        public HealthCardID getHealthCardID() {
            return null;
        }
    }
}
