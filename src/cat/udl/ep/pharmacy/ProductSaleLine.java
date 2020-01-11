package cat.udl.ep.pharmacy;

import cat.udl.ep.data.PatientContr;

import java.math.BigDecimal;

public class ProductSaleLine {
    private SaleInt sale;
    private MedicineDispensingLine medDispensingLine;
    private ProductSpecification productSpec;
    private BigDecimal subtotal;

    public ProductSaleLine(SaleInt sale, ProductSpecification productSpec, BigDecimal price, PatientContr contr) {
        this.sale = sale;
        this.medDispensingLine = null;
        if (sale.getePrescription() != null) {
            this.medDispensingLine = sale.getePrescription().getMedicineDispensingLine(productSpec.getProdID());
        }
        this.productSpec = productSpec;
        this.subtotal = price.multiply(contr.getPatCont());
    }

    public boolean equals(ProductSaleLine productSaleLine) {
        return this.sale==productSaleLine.getSale() && this.medDispensingLine.equals(productSaleLine.getMedDispensingLine())
            && this.productSpec.equals(productSaleLine.getProductSpec()) && this.subtotal.equals(productSaleLine.getSubtotal());
    }

    public ProductSpecification getProductSpec() {
        return productSpec;
    }

    public MedicineDispensingLine getMedDispensingLine() {
        return medDispensingLine;
    }

    public SaleInt getSale() {
        return sale;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

}
