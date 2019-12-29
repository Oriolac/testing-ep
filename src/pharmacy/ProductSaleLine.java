package pharmacy;

import data.PatientContr;
import data.ProductID;

import java.math.BigDecimal;

public class ProductSaleLine {
    private ProductID prodID;
    private String description;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;

    public ProductSaleLine(ProductID prodID, BigDecimal price, PatientContr contr) {

    }
}
