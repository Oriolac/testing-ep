package pharmacy.Sale;

import cat.udl.ep.DispensingTerminal;
import cat.udl.ep.data.HealthCardID;
import cat.udl.ep.data.PatientContr;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.data.exceptions.FormatErrorException;
import cat.udl.ep.data.exceptions.PatientIDException;
import cat.udl.ep.pharmacy.Dispensing;
import cat.udl.ep.pharmacy.Sale;
import cat.udl.ep.pharmacy.exceptions.NotValidePrescriptionException;
import cat.udl.ep.pharmacy.exceptions.ProductNotInDispensingException;
import cat.udl.ep.pharmacy.exceptions.SaleClosedException;
import cat.udl.ep.services.exceptions.HealthCardException;
import cat.udl.ep.data.exceptions.ProductIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.ConnectException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class SaleExceptionsTest {
    private Sale sale;

    private static Dispensing ePrescription;
    private DispensingTerminal dispensingTerminal;

    @BeforeEach
    public void initSale() throws PatientIDException {
        SaleMethodsTest.SNS sns = new SaleMethodsTest.SNS();
        SaleMethodsTest.HCR hcr = new SaleMethodsTest.HCR();
        SaleMethodsTest.SalesHistoryDB sh = new SaleMethodsTest.SalesHistoryDB();
        SaleMethodsTest.WarehouseDB wh = new SaleMethodsTest.WarehouseDB();
        dispensingTerminal = new DispensingTerminal(sns, hcr, sh, wh);
        ePrescription = sns.getePrescription(new HealthCardID("ZZZZ473298320193"));
        sale = new Sale(dispensingTerminal, ePrescription);
    }

    @Test
    public void ProductNotInDispensingExceptionTest() {
        assertThrows(ProductNotInDispensingException.class,
                () -> sale.addLine(new ProductID("333333333333"), new BigDecimal("3.0"),
                        new PatientContr(new BigDecimal("1"))));
    }

    @Test
    public void SaleClosedExceptionTest() {
        assertThrows(SaleClosedException.class,
                this::closeSaleThenAddLine);
        assertThrows(SaleClosedException.class,
                this::calculateFinalAmountTwice);
    }

    private void closeSaleThenAddLine() throws SaleClosedException, FormatErrorException, ProductNotInDispensingException {
        sale.calculateFinalAmount();
        sale.addLine(new ProductID("111111111111"), new BigDecimal("10.0"),
                new PatientContr(new BigDecimal("0.5")));
    }

    private void calculateFinalAmountTwice() throws SaleClosedException {
        sale.calculateFinalAmount();
        sale.calculateFinalAmount();
    }
}
