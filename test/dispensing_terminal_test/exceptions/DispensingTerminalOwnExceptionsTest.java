package dispensing_terminal_test.exceptions;

import cat.udl.ep.DispensingTerminal;
import cat.udl.ep.NotImplementedException;
import cat.udl.ep.data.exceptions.PatientIDException;
import cat.udl.ep.pharmacy.Dispensing;
import cat.udl.ep.pharmacy.exceptions.DispensingNotAvailableException;
import cat.udl.ep.pharmacy.exceptions.NotValidePrescriptionException;
import cat.udl.ep.services.exceptions.HealthCardException;
import dispensing_terminal_test.exceptions.Doubles.DoubleSNS.CorrectUpdateSNS;
import dispensing_terminal_test.exceptions.Doubles.DoubleWarehouse.FiniteWarehouseDB;
import dispensing_terminal_test.exceptions.Doubles.HealthCardReaderDB;
import dispensing_terminal_test.exceptions.Doubles.SalesHistoryDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DispensingTerminalOwnExceptionsTest {

    DispensingTerminal dt;

    @BeforeEach
    public void init(){
        String correctCode = "ALCE098765432112";
        dt = new DispensingTerminal(new CorrectUpdateSNS(correctCode), new HealthCardReaderDB(correctCode), new SalesHistoryDB(), new FiniteWarehouseDB());

    }

    @Test
    public void printNotImplemented() throws HealthCardException, PatientIDException, ConnectException, NotValidePrescriptionException {
        dt.getePrescription('i');
        dt.printNextDispensingSheet();
    }

    @Test
    public void dispensingNotAvailableExceptionTest() {
        assertThrows(DispensingNotAvailableException.class, () -> dt.initNewSale());
    }
}
