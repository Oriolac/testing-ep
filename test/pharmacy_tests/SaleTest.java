package pharmacy_tests;

import data.DispensableMedicines;
import data.PatientContr;
import data.ProductID;
import data.exceptions.FormatErrorException;
import data.exceptions.ProductIDException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pharmacy.*;
import pharmacy.exceptions.ProductNotInDispensingException;
import pharmacy.exceptions.SaleClosedException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class SaleTest {

    private Sale sale;

    @BeforeEach
    public void initSale() throws ProductIDException {
        DispensingTerminal dispensingTerminal = new DispensingTerminal();
        DispensableMedicines dispensableMedicines = initDispensableMedicines(dispensingTerminal);
        Dispensing ePrescription = new Dispensing(dispensableMedicines);
        sale = new Sale(dispensingTerminal, ePrescription);
    }

    @Test
    public void addLineTest() throws ProductIDException, FormatErrorException, SaleClosedException, ProductNotInDispensingException {
        ProductID prod1 = new ProductID("111111111111");
        sale.addLine(prod1, new BigDecimal("9.99"), new PatientContr(new BigDecimal("0.5")));
        List<ProductSaleLine> obtProductSaleLines = sale.getProductSaleLines();
        ProductSaleLine expProdSaleLine = new ProductSaleLine(sale, sale.getProductSpec(prod1));
        List<ProductSaleLine> expProductSaleLines = new ArrayList<>().add();

    }

    private DispensableMedicines initDispensableMedicines(DispensingTerminal dt) throws ProductIDException {
        DispensableMedicines dispensableMedicines = new DispensableMedicines();
        dispensableMedicines.put(new ProductID("111111111111"), new MedicineDispensingLine());
        dispensableMedicines.put(new ProductID("222222222222"), new MedicineDispensingLine());
        dispensableMedicines.put(new ProductID("333333333333"), new MedicineDispensingLine());
        return dispensableMedicines;
    }
}
