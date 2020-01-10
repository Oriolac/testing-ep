package pharmacy;

import cat.udl.ep.pharmacy.Dispensing;
import cat.udl.ep.pharmacy.MedicineDispensingLine;
import cat.udl.ep.pharmacy.ProductSpecification;
import cat.udl.ep.services.exceptions.ProductIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MedicineDispensingLineBasicTest {
    private MedicineDispensingLine medDispensingLine;

    @BeforeEach
    public void initMedDispensingLine() throws ProductIDException, ConnectException {
        ProductSpecification prodSpec = new ProductSpecification();
        Dispensing ePrescription = new Dispensing();
        medDispensingLine = new MedicineDispensingLine(ePrescription, prodSpec);
    }


    @Test
    public void isAcquiredTest() {
        assertTrue(!medDispensingLine.isAcquired());
    }
}
