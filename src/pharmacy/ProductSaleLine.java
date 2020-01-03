package pharmacy;

import data.PatientContr;
import data.ProductID;

import java.math.BigDecimal;

public class ProductSaleLine {
    private Sale sale;
    private MedicineDispensingLine medDispensingLine;
    private ProductSpecification productSpec;
    private BigDecimal subtotal;

    public ProductSaleLine(Sale sale, ProductID prodID, BigDecimal price, PatientContr contr) {
        this.sale = sale;
        // ...
    }

    public void setMedDispensingLine(MedicineDispensingLine medDispensingLine) {
        this.medDispensingLine = medDispensingLine;
    }
}
