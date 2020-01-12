package dispensing_terminal_test.exceptions;

import cat.udl.ep.DispensingTerminal;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.data.exceptions.FormatErrorException;
import cat.udl.ep.data.exceptions.IDException;
import cat.udl.ep.data.exceptions.PatientIDException;
import cat.udl.ep.data.exceptions.ProductIDException;
import cat.udl.ep.pharmacy.exceptions.*;
import cat.udl.ep.services.exceptions.HealthCardException;
import cat.udl.ep.services.exceptions.ProductNotFoundException;
import cat.udl.ep.services.exceptions.SaleNotInitiatedException;
import dispensing_terminal_test.exceptions.Doubles.DoubleSNS.CorrectUpdateSNS;
import dispensing_terminal_test.exceptions.Doubles.DoubleSNS.GetIncorrectSNS;
import dispensing_terminal_test.exceptions.Doubles.DoubleSNS.IncorrectUpdateSNS;
import dispensing_terminal_test.exceptions.Doubles.DoubleWarehouse.FiniteWarehouseDB;
import dispensing_terminal_test.exceptions.Doubles.DoubleWarehouse.InfiniteWarehouseDB;
import dispensing_terminal_test.exceptions.Doubles.HealthCardReaderDB;
import dispensing_terminal_test.exceptions.Doubles.IncorrectHealthCardReaderDB;
import dispensing_terminal_test.exceptions.Doubles.SalesHistoryDB;
import dispensing_terminal_test.exceptions.testInterfaces.HealthCardReaderExceptionsInt;
import dispensing_terminal_test.exceptions.testInterfaces.SNSExceptionsInt;
import dispensing_terminal_test.exceptions.testInterfaces.WarehouseExceptionsTestInt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.ConnectException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DispensingTerminalServicesExceptionsTest implements HealthCardReaderExceptionsInt, SNSExceptionsInt, WarehouseExceptionsTestInt {

    DispensingTerminal dt;

    @BeforeEach
    public void init() {
        String correctCode = "ALCE098765432112";
        dt = new DispensingTerminal(new GetIncorrectSNS(correctCode), new HealthCardReaderDB(correctCode), new SalesHistoryDB(), new InfiniteWarehouseDB());
    }

    @Override
    @Test
    public void healthCardExceptionTest() {
        dt = new DispensingTerminal(new GetIncorrectSNS("IBMI123456789012"), new IncorrectHealthCardReaderDB(), new SalesHistoryDB(), new InfiniteWarehouseDB());
        assertThrows(HealthCardException.class, () -> dt.getePrescription('i'));
    }

    @Override
    @Test
    public void getePrescriptionExceptionsTest() {
        String code = "IBMI123456789012";
        dt = new DispensingTerminal(new GetIncorrectSNS(code), new HealthCardReaderDB(code), new SalesHistoryDB(), new InfiniteWarehouseDB());
        assertThrows(ConnectException.class, () -> dt.getePrescription('i'));
        assertThrows(NotValidePrescriptionException.class, () -> dt.getePrescription('i'));

        String emptyCode = "ALCE123412341234";
        dt = new DispensingTerminal(new GetIncorrectSNS(emptyCode), new HealthCardReaderDB(emptyCode), new SalesHistoryDB(), new InfiniteWarehouseDB());
        assertThrows(NotValidePrescriptionException.class, () -> dt.getePrescription('i'));

        code = "ALCE123456789012";
        dt = new DispensingTerminal(new GetIncorrectSNS(code), new HealthCardReaderDB(code), new SalesHistoryDB(), new InfiniteWarehouseDB());
        assertThrows(HealthCardException.class, () -> dt.getePrescription('i'));
    }

    @Override
    @Test
    public void getPatientContrConnectionExceptionTest() throws ProductIDException {
        ProductID pid = new ProductID("123456789123");
        assertThrows(ConnectException.class, () -> dt.enterProduct(pid));
    }

    @Override
    @Test
    public void getProductSpecificConnectionExceptionTest() throws ProductIDException {
        ProductID pid = new ProductID("123456789987");
        assertThrows(ConnectException.class, () -> dt.getProductSpec(pid));
    }

    @Override
    @Test
    public void getProductSpecificProductNotFoundExceptionTest() throws ProductIDException {
        ProductID pid = new ProductID("123098123098");
        assertThrows(ProductNotFoundException.class, () -> dt.getProductSpec(pid));
    }

    @Override
    @Test
    public void updateePrescriptionConnectionTest() throws HealthCardException, FormatErrorException, ConnectException, NotValidePrescriptionException, DispensingException, ProductNotFoundException, ProductNotInDispensingException, SaleClosedException, SaleNotInitiatedException {
        String correctCode = "ALCE098765432112";
        dt = new DispensingTerminal(new IncorrectUpdateSNS(correctCode), new HealthCardReaderDB(correctCode), new SalesHistoryDB(), new InfiniteWarehouseDB());
        dt.getePrescription('i');
        dt.initNewSale();
        ProductID prod1 = new ProductID("123456789123");
        dt.enterProduct(prod1);
        dt.finalizeSale();
        BigDecimal quantity = new BigDecimal("20.0");
        assertThrows(ConnectException.class, () -> dt.realizePayment(quantity));
    }


    @Override
    @Test
    public void insuficientExistencesTest() throws HealthCardException, IDException, ConnectException, NotValidePrescriptionException, DispensingException, ProductNotFoundException, ProductNotInDispensingException, SaleClosedException, SaleNotInitiatedException {
        String correctCode = "ALCE098765432112";
        dt = new DispensingTerminal(new CorrectUpdateSNS(correctCode), new HealthCardReaderDB(correctCode), new SalesHistoryDB(), new FiniteWarehouseDB());
        dt.getePrescription('i');
        dt.initNewSale();
        ProductID prod1 = new ProductID("123456789123");
        dt.enterProduct(prod1);
        dt.finalizeSale();
        BigDecimal quantity = new BigDecimal("20.0");
        assertThrows(InsuficientExistencies.class, () -> dt.realizePayment(quantity));
    }
}
