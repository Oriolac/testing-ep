package pharmacy;

import data.PatientContr;
import data.ProductID;

import java.math.BigDecimal;

public class ProductSaleLine {
    private Sale sale;
    private MedicineDispensingLine medDispensingLine;
    private ProductSpecification productSpec;
    private BigDecimal subtotal;

    public ProductSaleLine(Sale sale, MedicineDispensingLine medDispensingLine, BigDecimal price, PatientContr contr) {
        this.sale = sale;
        this.medDispensingLine = medDispensingLine;
        this.productSpec = medDispensingLine.getProductSpec();
        productSpec = null;
        subtotal = price.multiply(contr.getPatCont());
    }

    public ProductSpecification getProductSpec() {
        return productSpec;
    }

    public MedicineDispensingLine getMedDispensingLine() {
        return medDispensingLine;
    }

    public Sale getSale() {
        return sale;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

}
