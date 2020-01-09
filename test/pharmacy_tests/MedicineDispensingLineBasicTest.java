package pharmacy_tests;

import org.junit.jupiter.api.BeforeEach;
import pharmacy.Dispensing;
import pharmacy.MedicineDispensingLine;
import pharmacy.ProductSaleLine;
import pharmacy.ProductSpecification;

public class MedicineDispensingLineBasicTest {
    private MedicineDispensingLine medDispensingLine;

    @BeforeEach
    public void initMedDispensingLine() {
        ProductSpecification prodSepec = new ProductSpecification();
        ProductSaleLine prodSaleLine = new ProductSaleLine();
        Dispensing ePrescription = new Dispensing();
        medDispensingLine = new MedicineDispensingLine(ePrescription, )
    }
}
