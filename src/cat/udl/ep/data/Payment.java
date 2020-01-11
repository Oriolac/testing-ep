package cat.udl.ep.data;

import cat.udl.ep.pharmacy.Sale;

import java.math.BigDecimal;

public class Payment {
    private BigDecimal imprt;
    private Sale sale;

    public Payment(BigDecimal imprt) {
        this.imprt = imprt;
    }

    public BigDecimal getImport() {
        return this.imprt;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }
}
