package dispensing_terminal_test;

import cat.udl.ep.DispensingTerminal;
import cat.udl.ep.services.HealthCardReader;
import cat.udl.ep.services.NationalHealthService;
import cat.udl.ep.services.SalesHistory;
import cat.udl.ep.services.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DispensingTerminalMethodsTest {
    private DispensingTerminal dispensingTerminal;

    @BeforeEach
    public void initDispensingTerminal() {
        NationalHealthService sns = new SNS();
        HealthCardReader healthCardReader = new HealthCardReaderDB();
        SalesHistory salesHistory = new SalesHistoryDB();
        Warehouse warehouse = new WarehouseDB();
        dispensingTerminal = new DispensingTerminal(sns, healthCardReader, salesHistory, warehouse);
    }

    @Test
    public void getePrescriptionTest() {
        assertDoesNotThrow(() -> dispensingTerminal.getePrescription('i'));
    }


}
