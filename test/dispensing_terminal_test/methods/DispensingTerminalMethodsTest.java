package dispensing_terminal_test.methods;

import cat.udl.ep.DispensingTerminal;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.data.exceptions.IDException;
import cat.udl.ep.data.exceptions.PatientIDException;
import cat.udl.ep.pharmacy.exceptions.*;
import cat.udl.ep.services.HealthCardReader;
import cat.udl.ep.services.NationalHealthService;
import cat.udl.ep.services.SalesHistory;
import cat.udl.ep.services.Warehouse;
import cat.udl.ep.services.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.ConnectException;

import static org.junit.jupiter.api.Assertions.*;

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
    public void getePrescriptionTest() throws NotValidePrescriptionException, HealthCardException, ConnectException, PatientIDException {
        dispensingTerminal.getePrescription('i');
        assertEquals(dispensingTerminal.getePrescription().getDispensingTerminal(), dispensingTerminal);
    }

    @Test
    public void initNewSaleTest() throws NotValidePrescriptionException, HealthCardException, ConnectException, DispensingNotAvailableException, PatientIDException {
        dispensingTerminal.getePrescription('i');
        dispensingTerminal.initNewSale();

        assertEquals(dispensingTerminal.getSale().getDispensingTerminal(), dispensingTerminal);
        assertEquals(dispensingTerminal.getSale().getePrescription(), dispensingTerminal.getePrescription());
    }

    @Test
    public void enterProductTest() throws NotValidePrescriptionException, IDException, ConnectException, DispensingException, ProductNotFoundException, ProductNotInDispensingException, SaleClosedException, HealthCardException {
        dispensingTerminal.getePrescription('i');
        dispensingTerminal.initNewSale();
        ProductID prod1 = new ProductID("111111111111");
        dispensingTerminal.enterProduct(prod1);
        assertEquals(dispensingTerminal.getSale().getProductSaleLine(prod1).getSale(), dispensingTerminal.getSale());
        assertTrue(dispensingTerminal.getSale().getProductSaleLine(prod1).getProductSpec().equals(dispensingTerminal.getProductSpec(prod1)));
        assertTrue(dispensingTerminal.getSale().getProductSaleLine(prod1).getMedDispensingLine().equals(dispensingTerminal.getePrescription().getMedicineDispensingLine(prod1)));
        BigDecimal subtotal = dispensingTerminal.getProductSpec(prod1).getPrice().multiply(dispensingTerminal.getSNS().getPatientContr(dispensingTerminal.getHCR().getHealthCardID()).getPatCont());
        assertEquals(dispensingTerminal.getSale().getProductSaleLine(prod1).getSubtotal(), subtotal);
        assertTrue(dispensingTerminal.getePrescription().getMedicineDispensingLine(prod1).isAcquired());
    }

    @Test
    public void finalizeSaleDispensingNotCompleted() throws NotValidePrescriptionException, IDException, ConnectException, DispensingException, ProductNotFoundException, ProductNotInDispensingException, SaleClosedException, SaleNotInitiatedException, HealthCardException {
        dispensingTerminal.getePrescription('i');
        dispensingTerminal.initNewSale();
        ProductID prod1 = new ProductID("111111111111");
        dispensingTerminal.enterProduct(prod1);
        dispensingTerminal.finalizeSale();
        assertTrue(dispensingTerminal.getSale().isClosed());
        BigDecimal result = dispensingTerminal.getProductSpec(prod1).getPrice().multiply(new BigDecimal("0.5"));
        result = result.add(result.multiply(new BigDecimal("0.21")));
        assertEquals(dispensingTerminal.getSale().getAmount(), result);
        assertFalse(dispensingTerminal.getePrescription().isCompleted());
    }

    @Test
    public void finalizeSaleDispensingCompleted() throws NotValidePrescriptionException, IDException, ConnectException, DispensingException, ProductNotFoundException, ProductNotInDispensingException, SaleClosedException, SaleNotInitiatedException, HealthCardException {
        dispensingTerminal.getePrescription('i');
        dispensingTerminal.initNewSale();
        ProductID prod1 = new ProductID("111111111111");
        dispensingTerminal.enterProduct(prod1);
        ProductID prod2 = new ProductID("222222222222");
        dispensingTerminal.enterProduct(prod2);
        ProductID prod3 = new ProductID("333333333333");
        dispensingTerminal.enterProduct(prod3);
        dispensingTerminal.finalizeSale();
        assertTrue(dispensingTerminal.getePrescription().isCompleted());
    }

    @Test
    public void realizePaymentTest() throws NotValidePrescriptionException, IDException, ConnectException, ProductNotFoundException, DispensingException, ProductNotInDispensingException, SaleClosedException, SaleNotInitiatedException, InsuficientExistencies, QuantityMinorThanImport, SaleNotClosedException, HealthCardException {
        dispensingTerminal.getePrescription('i');
        dispensingTerminal.initNewSale();
        ProductID prod1 = new ProductID("111111111111");
        dispensingTerminal.enterProduct(prod1);
        dispensingTerminal.finalizeSale();
        BigDecimal quantity = new BigDecimal("20.0");
        dispensingTerminal.realizePayment(quantity);

        assertEquals(dispensingTerminal.getSale().getPayment().getImport(), dispensingTerminal.getSale().getAmount());
        BigDecimal change = quantity.subtract(dispensingTerminal.getSale().getAmount());
        assertEquals(change, dispensingTerminal.getSale().getPayment().getChange());
        assertEquals(dispensingTerminal.getSale().getPayment().getSale(), dispensingTerminal.getSale());

        assertEquals(dispensingTerminal.getSale(), dispensingTerminal.getSalesHistory().getSale(dispensingTerminal.getSale().getSaleCode()));
        assertThrows(NotValidePrescriptionException.class, () -> dispensingTerminal.getePrescription('i'));
    }



}
