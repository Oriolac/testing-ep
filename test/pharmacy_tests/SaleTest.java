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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SaleTest {

    private Sale sale;
    private Dispensing ePrescription;

    @BeforeEach
    public void initSale() throws ProductIDException {
        DispensingTerminal dispensingTerminal = new DispensingTerminal();
        DispensableMedicines dispensableMedicines = initDispensableMedicines(dispensingTerminal);
        ePrescription = new Dispensing(dispensableMedicines);
        sale = new Sale(dispensingTerminal, ePrescription);
    }

    @Test
    public void addLineTest() throws ProductIDException, FormatErrorException, SaleClosedException, ProductNotInDispensingException {
        ProductID prod1 = new ProductID("111111111111");
        BigDecimal price = new BigDecimal("9.99");
        PatientContr contr = new PatientContr(new BigDecimal("0.5"));
        sale.addLine(prod1, price, contr);
        ProductSaleLine expProdSaleLine = new ProductSaleLine(sale, sale.getProductSpec(prod1), price, contr);
        List<ProductSaleLine> expProductSaleLines = new ArrayList<>();
        expProductSaleLines.add(expProdSaleLine);

        assertTrue(equals(expProductSaleLines, sale.getProductSaleLines()));

    }

    private boolean equals(List<ProductSaleLine> productSaleLines1, List<ProductSaleLine> productSaleLines2) {
        if (productSaleLines1.size() != productSaleLines2.size()) {
            return false;
        } else {
            for (int i = 0; i<productSaleLines1.size(); i++) {
                if (productSaleLines1.get(i).equals(productSaleLines2.get(i)) == false ) {
                    return false;
                }
            }
            return true;
        }
    }

    private DispensableMedicines initDispensableMedicines(DispensingTerminal dt) throws ProductIDException {
        DispensableMedicines dispensableMedicines = new DispensableMedicines();
        ProductID prod1 = new ProductID("111111111111");

        ProductSpecification prodSpec1 = new ProductSpecification(prod1, "prod1" ,new BigDecimal("9.99"));

        dispensableMedicines.put(prod1, new MedicineDispensingLine(ePrescription, prodSpec1));

        return dispensableMedicines;
    }
}
