package pharmacy;


import data.ProductID;

public class MedicineDispensingLine {
    private boolean acquired;
    private ProductSpecification productSpec;
    private ProductSaleLine productSaleLine;
    private Dispensing ePrescription;

    public MedicineDispensingLine(Dispensing ePrescription, ProductSpecification productSpec) {
        this.ePrescription = ePrescription;
        this.acquired = false;
        this.productSpec = productSpec;
        this.productSaleLine = null;
    }

    public boolean isAcquired() {
        return acquired;
    }

    public boolean equals(MedicineDispensingLine medicineDispensingLine) {
        return ePrescription==medicineDispensingLine.getePrescription() && acquired==medicineDispensingLine.isAcquired()
                && productSpec.equals(medicineDispensingLine.getProductSpec());
    }

    public ProductSpecification getProductSpec() {
        return productSpec;
    }

    public ProductSaleLine getProductSaleLine() {
        return productSaleLine;
    }

    public Dispensing getePrescription() {
        return ePrescription;
    }

    public ProductSpecification getProductSpec(ProductID productID) {
        return productSpec;
    }

    public void setAcquired() {
        acquired = true;
    }

    public void setProductSaleLine(ProductSaleLine productSaleLine) {
        this.productSaleLine = productSaleLine;
    }

}
