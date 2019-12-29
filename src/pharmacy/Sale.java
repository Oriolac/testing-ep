package pharmacy;

import data.PatientContr;
import data.ProductID;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/***
 * Package for the classes involved in the use case Suply next dispensing
 */

public class Sale {
    private int saleCode;
    private Date date;
    private BigDecimal amount;
    private boolean isClosed; // flag to know if the sale is closed
    private List<ProductSaleLine> productSaleLines;

    public Sale() {

    }

    public void addLine(ProductID prodID, BigDecimal price, PatientContr contr) throws SaleClosedException {
        if (!isClosed()) {
            productSaleLines.add(new ProductSaleLine(prodID, price, contr));
        } else {
            throw new SaleClosedException("La venda ja ha estat tancada.");
        }
    }

    private void calculateAmount() {
        for (ProductSaleLine prodSaleLine : productSaleLines) {
            amount = amount.add(prodSaleLine.getSubtotal());
        }
    }

    private void addTaxes() throws SaleClosedException {
        if (!isClosed()) {
            BigDecimal taxes = amount.multiply(new BigDecimal("0.21"));
            amount = amount.add(taxes);
        } else {
            throw new SaleClosedException("La venda ja ha estat tancada.");
        }
    }

    public void calculateFinalAmount() throws SaleClosedException {
        calculateAmount();
        addTaxes();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    private void setClosed() {
        isClosed = true;
    }

    public boolean isClosed() {
        return isClosed;
    }

    // the rest of getters, setters and methods
}
