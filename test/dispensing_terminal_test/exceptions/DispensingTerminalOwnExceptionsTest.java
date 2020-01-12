package dispensing_terminal_test.exceptions;

import cat.udl.ep.DispensingTerminal;
import cat.udl.ep.NotImplementedException;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.data.exceptions.IDException;
import cat.udl.ep.data.exceptions.PatientIDException;
import cat.udl.ep.data.exceptions.ProductIDException;
import cat.udl.ep.pharmacy.Dispensing;
import cat.udl.ep.pharmacy.exceptions.*;
import cat.udl.ep.services.exceptions.*;
import dispensing_terminal_test.exceptions.Doubles.DoubleSNS.CorrectUpdateSNS;
import dispensing_terminal_test.exceptions.Doubles.DoubleWarehouse.FiniteWarehouseDB;
import dispensing_terminal_test.exceptions.Doubles.HealthCardReaderDB;
import dispensing_terminal_test.exceptions.Doubles.SalesHistoryDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
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

    @Test
    public void SaleNotInitializedException() throws HealthCardException, PatientIDException, ConnectException, NotValidePrescriptionException {
        dt.getePrescription('i');
        assertThrows(SaleNotInitiatedException.class, () -> dt.finalizeSale());
    }

    @Test
    public void SaleNotClosedExceptionTest() throws HealthCardException, PatientIDException, ConnectException, NotValidePrescriptionException, DispensingNotAvailableException {
        dt.getePrescription('i');
        dt.initNewSale();
        assertThrows(SaleNotClosedException.class, () -> dt.realizePayment(BigDecimal.ONE));
    }

    @Test
    public void QuantityMinorThanImportTest() throws DispensingException, HealthCardException, IDException, ConnectException, NotValidePrescriptionException, ProductNotFoundException, ProductNotInDispensingException, SaleClosedException, SaleNotInitiatedException {
        dt.getePrescription('i');
        dt.initNewSale();
        ProductID prodID1 = new ProductID("123456789123");
        dt.enterProduct(prodID1);
        dt.finalizeSale();
        assertThrows(QuantityMinorThanImport.class, () -> dt.realizePayment(BigDecimal.ZERO));
    }
}
