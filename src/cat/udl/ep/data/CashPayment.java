package cat.udl.ep.data;

import cat.udl.ep.services.exceptions.QuantityMinorThanImport;

import java.math.BigDecimal;

public class CashPayment extends Payment {
    private BigDecimal change;

    public CashPayment(BigDecimal imprt, BigDecimal quantity) throws QuantityMinorThanImport {
        super(imprt);
        if (imprt.compareTo(quantity) > 0) {
            throw new QuantityMinorThanImport("Quantitat entregada menor que import.");
        }
        this.change = quantity.subtract(imprt);
        setChange();
    }

    public BigDecimal getChange() {
        return this.change;
    }

    public void setChange() {
        super.setChange(change);
    }

}
