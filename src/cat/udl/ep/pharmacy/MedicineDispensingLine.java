package cat.udl.ep.pharmacy;


import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicineDispensingLine that = (MedicineDispensingLine) o;
        return acquired == that.acquired &&
                Objects.equals(productSpec, that.productSpec) &&
                Objects.equals(productSaleLine, that.productSaleLine) &&
                Objects.equals(ePrescription, that.ePrescription);
    }

    @Override
    public int hashCode() {
        return Objects.hash(acquired, productSpec, productSaleLine, ePrescription);
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

    public void setAcquired() {
        acquired = true;
    }

    public void setProductSaleLine(ProductSaleLine productSaleLine) {
        this.productSaleLine = productSaleLine;
    }

}
