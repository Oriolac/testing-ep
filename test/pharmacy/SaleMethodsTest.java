package pharmacy;

import cat.udl.ep.DispensingTerminal;
import cat.udl.ep.data.DispensableMedicines;
import cat.udl.ep.data.HealthCardID;
import cat.udl.ep.data.PatientContr;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.data.exceptions.FormatErrorException;
import cat.udl.ep.pharmacy.*;
import cat.udl.ep.pharmacy.exceptions.InsuficientExistencies;
import cat.udl.ep.pharmacy.exceptions.NotValidePrescriptionException;
import cat.udl.ep.pharmacy.exceptions.ProductNotInDispensingException;
import cat.udl.ep.pharmacy.exceptions.SaleClosedException;
import cat.udl.ep.services.HealthCardReader;
import cat.udl.ep.services.NationalHealthService;
import cat.udl.ep.services.SalesHistory;
import cat.udl.ep.services.Warehouse;
import cat.udl.ep.data.exceptions.HealthCardException;
import cat.udl.ep.data.exceptions.ProductIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaleMethodsTest {

    private Sale sale;
    private static Dispensing ePrescription;
    private DispensingTerminal dispensingTerminal;

    @BeforeEach
    public void initSale() throws ConnectException, HealthCardException, NotValidePrescriptionException {
        SNS sns = new SNS();
        HCR hcr = new HCR();
        Warehouse wh = new WarehouseDB();
        SalesHistoryDB sh = new SalesHistoryDB();
        dispensingTerminal = new DispensingTerminal(sns, hcr, sh, wh);
        ePrescription = sns.getePrescription(new HealthCardID("ZZZZ473298320193"));
        sale = new Sale(dispensingTerminal, ePrescription);
    }

    @Test
    public void addLineTest() throws ProductIDException, SaleClosedException, ProductNotInDispensingException, ConnectException, HealthCardException {
        List<ProductSaleLine> expProductSaleLines = new ArrayList<>();

        ProductID prod1 = new ProductID("111111111111");
        ProductID prod2 = new ProductID("222222222222");
        BigDecimal price1 = new BigDecimal("9.99");
        BigDecimal price2 = new BigDecimal("10.0");
        PatientContr contr = sale.getDispensingTerminal().getSNS().getPatientContr(new HealthCardID("ZZZZ473298320193"));

        sale.addLine(prod1, price1, contr);
        sale.addLine(prod2, price2, contr);

        ProductSaleLine expProdSaleLine1 = new ProductSaleLine(sale, sale.getProductSpec(prod1), price1, contr);
        ProductSaleLine expProdSaleLine2 = new ProductSaleLine(sale, sale.getProductSpec(prod2), price2, contr);
        expProductSaleLines.add(expProdSaleLine1);
        expProductSaleLines.add(expProdSaleLine2);

        assertTrue(equals(expProductSaleLines, sale.getProductSaleLines()));
    }

    @Test
    public void isDispensableTest() throws ProductIDException {
        ProductID prod1 = new ProductID("111111111111");
        ProductID prod3 = new ProductID("333333333333");
        assertTrue(sale.isDispensable(prod1));
        assertTrue(!sale.isDispensable(prod3));
    }

    @Test
    public void calculateFinalAmountTest() throws SaleClosedException, ProductIDException, ConnectException, ProductNotInDispensingException, HealthCardException {
        PatientContr contr = sale.getDispensingTerminal().getSNS().getPatientContr(new HealthCardID("ZZZZ473298320193"));;
        sale.addLine(new ProductID("111111111111"), new BigDecimal("9.99"), contr);
        sale.addLine(new ProductID("222222222222"), new BigDecimal("10.0"), contr);
        sale.calculateFinalAmount();

        BigDecimal price1 = ePrescription.getProductSpec(new ProductID("111111111111")).getPrice();
        BigDecimal price2 = ePrescription.getProductSpec(new ProductID("222222222222")).getPrice();
        BigDecimal expFinalAmount = price1.multiply(contr.getPatCont()).add(price2.multiply(contr.getPatCont()));
        expFinalAmount = expFinalAmount.add(expFinalAmount.multiply(new BigDecimal("0.21")));

        assertTrue(expFinalAmount.compareTo(sale.getAmount())==0);
    }

    @Test
    public void isClosedTest() throws ProductIDException, SaleClosedException, ProductNotInDispensingException, ConnectException, HealthCardException {
        PatientContr contr = sale.getDispensingTerminal().getSNS().getPatientContr(new HealthCardID("ZZZZ473298320193"));;
        sale.addLine(new ProductID("111111111111"), new BigDecimal("9.99"), contr);
        sale.addLine(new ProductID("222222222222"), new BigDecimal("10.0"), contr);
        assertTrue(!sale.isClosed());
        sale.calculateFinalAmount();
        assertTrue(sale.isClosed());
    }

    @Test
    public void gettersTest() {
        assertTrue(sale.hashCode()==sale.getSaleCode());
        assertTrue(dispensingTerminal.equals(sale.getDispensingTerminal()));
    }

    private boolean equals(List<ProductSaleLine> productSaleLines1, List<ProductSaleLine> productSaleLines2) {
        if (productSaleLines1.size() != productSaleLines2.size()) {
            return false;
        } else {
            for (int i = 0; i<productSaleLines1.size(); i++) {
                if (!productSaleLines1.get(i).equals(productSaleLines2.get(i))) {
                    return false;
                }
            }
            return true;
        }
    }

    private static DispensableMedicines initDispensableMedicines() throws ProductIDException {
        DispensableMedicines dispensableMedicines = new DispensableMedicines();
        ProductID prod1 = new ProductID("111111111111");
        ProductID prod2 = new ProductID("222222222222");

        ProductSpecification prodSpec1 = new ProductSpecification(prod1, "prod1" ,new BigDecimal("9.99"));
        ProductSpecification prodSpec2 = new ProductSpecification(prod1, "prod2" ,new BigDecimal("10.00"));

        dispensableMedicines.put(prod1, new MedicineDispensingLine(ePrescription, prodSpec1));
        dispensableMedicines.put(prod2, new MedicineDispensingLine(ePrescription, prodSpec2));


        return dispensableMedicines;
    }

    static class SNS implements NationalHealthService {

        @Override
        public Dispensing getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
            try {
                DispensableMedicines dispensableMedicines = initDispensableMedicines();
                return new Dispensing(new Date(), new Date(), dispensableMedicines);
            } catch (ProductIDException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public PatientContr getPatientContr(HealthCardID hcID) throws ConnectException {
            try {
                return new PatientContr(new BigDecimal("0.5"));
            } catch (FormatErrorException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public ProductSpecification getProductSpecific(ProductID pID) throws ProductIDException, ConnectException {
            return null;
        }

        @Override
        public List<Dispensing> updateePrescription(HealthCardID hcID, Dispensing disp) throws ConnectException {
            return null;
        }
    }

    static class HCR implements HealthCardReader {

        public HCR() {}

        @Override
        public HealthCardID getHealthCardID() throws HealthCardException {
            return null;
        }
    }

    static class WarehouseDB implements Warehouse {

        public WarehouseDB() {}

        @Override
        public void updateStock(List<ProductSaleLine> listOfProducts) throws InsuficientExistencies {

        }
    }

    static class SalesHistoryDB implements SalesHistory {

        @Override
        public void registerSale(Sale sale) {

        }
    }
}
