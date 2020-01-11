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

    @Override
    public boolean equals(Object productSaleLine) {
        if (!(productSaleLine instanceof ProductSaleLine))
            return false;
        ProductSaleLine obj = (ProductSaleLine) productSaleLine;
        if(medDispensingLine != null)
            return this.medDispensingLine.equals(obj.getMedDispensingLine())
                    && this.productSpec.equals(obj.getProductSpec()) && this.subtotal.equals(obj.getSubtotal());
        Boolean isTrue = this.productSpec.equals(obj.getProductSpec());
        isTrue = this.subtotal.equals(obj.getSubtotal());
        return this.productSpec.equals(obj.getProductSpec()) && this.subtotal.equals(obj.getSubtotal());
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
