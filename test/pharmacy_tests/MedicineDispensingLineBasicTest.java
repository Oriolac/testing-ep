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
        Dispensing ePrescription = new Dispensing();
        medDispensingLine = new MedicineDispensingLine(ePrescription, prodSpec);
    }


    @Test
    public void isAcquiredTest() {
        assertTrue(!medDispensingLine.isAcquired());
    }
}
