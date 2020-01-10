package pharmacy_tests;

import org.junit.jupiter.api.BeforeEach;
import pharmacy.Dispensing;
import pharmacy.DispensingTerminal;
import pharmacy.Sale;

public class SaleTest {

    private Sale sale;

    @BeforeEach
    public void initSale() {
        Dispensing ePrescription = new Dispensing();
        DispensingTerminal dispensingTerminal = new DispensingTerminal();
        sale = new Sale(dispensingTerminal, ePrescription);
    }


}
