package pharmacy;


public class MedicineDispensingLine {
    private boolean acquired;
    private ProductSpecification productSpec;
    private ProductSaleLine productSaleLine;
    private Dispensing ePrescription;

    public MedicineDispensingLine(Dispensing ePrescription, ProductSpecification productSpec) {
        this.ePrescription = ePrescription;
        acquired = false;
        this.productSpec = productSpec;
        productSaleLine = null;
    }

    public boolean isAcquired() {
        return acquired;
    }

    public ProductSpecification getProductSpec() {
        return productSpec;
    }

    public void setAcquired() {
        acquired = true;
    }

    public void setProductSaleLine(ProductSaleLine productSaleLine) {
        this.productSaleLine = productSaleLine;
    }
}
