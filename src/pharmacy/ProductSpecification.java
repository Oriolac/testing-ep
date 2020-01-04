package pharmacy;

import data.ProductID;

import java.math.BigDecimal;

public class ProductSpecification {
    private ProductID prodID;
    private String description;
    private BigDecimal price;
    private MedicineDispensingLine medDispensingLine;
    private ProductSaleLine prodSaleLine;

    public ProductSpecification() {

    }

    public ProductSpecification(ProductID productID, String description, BigDecimal price) {
        this.prodID = productID;
        this.description = description;
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public void setMedDispensingLine(MedicineDispensingLine medDispensingLine) {
        this.medDispensingLine = medDispensingLine;
    }

    public void setProdSaleLine(ProductSaleLine prodSaleLine) {
        this.prodSaleLine = prodSaleLine;
    }
}
