package pharmacy_tests;

import data.DispensableMedicines;
import data.ProductID;
import data.exceptions.ProductIDException;
import org.junit.jupiter.api.BeforeEach;
import pharmacy.Dispensing;
import pharmacy.DispensingTerminal;
import pharmacy.MedicineDispensingLine;
import pharmacy.Sale;

public class SaleTest {

    private Sale sale;

    @BeforeEach
    public void initSale() throws ProductIDException {
        DispensingTerminal dispensingTerminal = new DispensingTerminal();
        DispensableMedicines dispensableMedicines = initDispensableMedicines(dispensingTerminal);
        Dispensing ePrescription = new Dispensing(dispensableMedicines);
        sale = new Sale(dispensingTerminal, ePrescription);
    }



    private DispensableMedicines initDispensableMedicines(DispensingTerminal dt) throws ProductIDException {
        DispensableMedicines dispensableMedicines = new DispensableMedicines();
        dispensableMedicines.put(new ProductID("111111111111"), new MedicineDispensingLine());
        dispensableMedicines.put(new ProductID("222222222222"), new MedicineDispensingLine());
        dispensableMedicines.put(new ProductID("333333333333"), new MedicineDispensingLine());
        return dispensableMedicines;
    }
}
