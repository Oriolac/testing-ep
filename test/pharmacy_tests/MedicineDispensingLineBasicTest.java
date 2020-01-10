package pharmacy_tests;

import data.DispensableMedicines;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pharmacy.Dispensing;
import pharmacy.MedicineDispensingLine;
import pharmacy.ProductSaleLine;
import pharmacy.ProductSpecification;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MedicineDispensingLineBasicTest {
    private MedicineDispensingLine medDispensingLine;

    @BeforeEach
    public void initMedDispensingLine() {
        ProductSpecification prodSpec = new ProductSpecification();
        DispensableMedicines dispensableMedicines = initDispensableMedicines();
        Dispensing ePrescription = new Dispensing(dispensableMedicines);
        medDispensingLine = new MedicineDispensingLine(ePrescription, prodSpec);
    }

    private DispensableMedicines initDispensableMedicines() {
        DispensableMedicines dispensableMedicines =
    }

    @Test
    public void isAcquiredTest() {
        assertTrue(!medDispensingLine.isAcquired());
    }
}
