package pharmacy;

import data.PatientContr;
import data.ProductID;

import java.math.BigDecimal;

public class ProductSaleLine {
    private Sale sale;
    private MedicineDispensingLine medDispensingLine;
    private ProductSpecification productSpec;
    private BigDecimal subtotal;

    public ProductSaleLine(ProductID prodID, BigDecimal price, PatientContr contr) {

    }
}
