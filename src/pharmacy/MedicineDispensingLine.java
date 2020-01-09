package pharmacy;


import data.ProductID;
import data.exceptions.ProductIDException;

import java.net.ConnectException;

public class MedicineDispensingLine {
    private boolean acquired;
    private ProductSpecification productSpec;
    private ProductSaleLine productSaleLine;
    private Dispensing ePrescription;

    public MedicineDispensingLine(ProductSpecification productSpec) {
        this.productSpec = productSpec;
        this.acquired = false;
    }

    public MedicineDispensingLine(Dispensing ePrescription, ProductID productID) throws ProductIDException, ConnectException {
        this.ePrescription = ePrescription;
        this.acquired = false;
        this.productSpec = getProductSpec(productID);
        this.productSpec.setMedDispensingLine(this);
        this.productSaleLine = null;
    }

    public boolean isAcquired() {
        return acquired;
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

    public ProductSpecification getProductSpec(ProductID productID) throws ProductIDException, ConnectException {
        return ePrescription.getProductSpec(productID);
    }

    public void setAcquired() {
        acquired = true;
    }

    public void setProductSaleLine(ProductSaleLine productSaleLine) {
        this.productSaleLine = productSaleLine;
    }

}
