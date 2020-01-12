package cat.udl.ep.pharmacy;

import cat.udl.ep.data.ProductID;

import java.math.BigDecimal;

public class ProductSpecification {
    private ProductID prodID;
    private String description;
    private BigDecimal price;
    private MedicineDispensingLine medDispensingLine;
    private ProductSaleLine prodSaleLine;

    public ProductSpecification(ProductID productID, String description, BigDecimal price) {
        this.prodID = productID;
        this.description = description;
        this.price = price;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ProductSpecification))
            return false;
        ProductSpecification productSpec = (ProductSpecification) obj;
        return prodID.equals(productSpec.getProdID()) &&
                description.equals(productSpec.description) &&
                price.floatValue() == productSpec.getPrice().floatValue();
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

    public MedicineDispensingLine getMedDispensingLine() { return this.medDispensingLine; }

    public void setProdSaleLine(ProductSaleLine prodSaleLine) {
        this.prodSaleLine = prodSaleLine;
    }

    public ProductSaleLine getProdSaleLine() { return this.prodSaleLine; }

    public ProductID getProdID() {
        return prodID;
    }
}
