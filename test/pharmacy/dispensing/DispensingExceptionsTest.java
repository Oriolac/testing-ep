package pharmacy.dispensing;

import cat.udl.ep.data.DispensableMedicines;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.pharmacy.Dispensing;
import cat.udl.ep.pharmacy.MedicineDispensingLine;
import cat.udl.ep.pharmacy.ProductSpecification;
import cat.udl.ep.pharmacy.exceptions.AcquiredMedicineDispensingLineException;
import cat.udl.ep.pharmacy.exceptions.CompletedDispensingException;
import cat.udl.ep.pharmacy.exceptions.DispensingException;
import cat.udl.ep.pharmacy.exceptions.DispensingNotAvailableException;
import cat.udl.ep.services.exceptions.ProductIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;


public class DispensingExceptionsTest {

    Instant now;
    Dispensing ePrescription;
    DispensableMedicines dispensableMedicines;


    @BeforeEach
    public void init() {
        now = Instant.now();
        dispensableMedicines = new DispensableMedicines();
        ePrescription = new Dispensing(Date.from(Instant.now().minusSeconds(10L)),
                Date.from(Instant.now().plusSeconds(10L)), dispensableMedicines);
    }

    @Test
    public void acquiredMedicineDispensingLineExceptionTest() throws ProductIDException {
        ProductID productID = new ProductID("123456789123");
        ProductSpecification productSpecification = new ProductSpecification(productID, "DESC", BigDecimal.TEN);
        MedicineDispensingLine medicineDispensingLine = new MedicineDispensingLine(ePrescription, productSpecification);
        medicineDispensingLine.setAcquired();
        dispensableMedicines.put(productID, medicineDispensingLine);
        assertThrows(AcquiredMedicineDispensingLineException.class, () -> ePrescription.setProductAsDispensed(productID));

        ProductID productID1 = new ProductID("123456789987");
        ProductSpecification productSpecification1 = new ProductSpecification(productID1, "DESC", BigDecimal.TEN);
        MedicineDispensingLine medicineDispensingLine1 = new MedicineDispensingLine(ePrescription, productSpecification1);
        dispensableMedicines.put(productID1, medicineDispensingLine1);
        assertDoesNotThrow(() -> ePrescription.setProductAsDispensed(productID1));
    }

    @Test
    public void DispensingNotAvailableExceptionTest() {
        ePrescription = new Dispensing(Date.from(Instant.now().plusSeconds(100L)),
                Date.from(Instant.now().plusSeconds(10L)), dispensableMedicines);
        assertThrows(DispensingNotAvailableException.class, () -> ePrescription.dispensingEnabled());
        ePrescription = new Dispensing(Date.from(Instant.now().plusSeconds(100L)),
                Date.from(Instant.now().plusSeconds(200L)), dispensableMedicines);
        assertThrows(DispensingNotAvailableException.class, () -> ePrescription.dispensingEnabled());
    }

    @Test
    public void CompletedDispensionException() throws ProductIDException {
        ProductID productID = new ProductID("123456789987");
        ProductSpecification productSpecification = new ProductSpecification(productID, "DESC", BigDecimal.TEN);
        MedicineDispensingLine medicineDispensingLine = new MedicineDispensingLine(ePrescription, productSpecification);
        dispensableMedicines.put(productID, medicineDispensingLine);
        assertDoesNotThrow(() -> ePrescription.setProductAsDispensed(productID));
        ePrescription.setCompleted();
        assertThrows(CompletedDispensingException.class, () -> ePrescription.setProductAsDispensed(productID));
    }

}
