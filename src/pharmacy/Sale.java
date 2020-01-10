package pharmacy;

import data.PatientContr;
import data.ProductID;
import pharmacy.exceptions.ProductNotInDispensingException;
import pharmacy.exceptions.SaleClosedException;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    private final Dispensing ePrescription;
    private DispensingTerminal dispensingTerminal;

    public Sale(DispensingTerminal dispensingTerminal, Dispensing ePrescription) {
        saleCode = hashCode();
        date = new Date();
        amount = new BigDecimal("0.0");
        isClosed = false;
        productSaleLines = new ArrayList<>();
        this.ePrescription = ePrescription;
        this.dispensingTerminal = dispensingTerminal;
    }

    public void addLine(ProductID prodID, BigDecimal price, PatientContr contr) throws SaleClosedException, ProductNotInDispensingException {
        if (!isClosed()) {
            if (isDispensable(prodID)) {
                ProductSaleLine prodSaleLine = new ProductSaleLine(this, getProductSpec(prodID), price, contr);
                productSaleLines.add(prodSaleLine);
            } else {
                throw new ProductNotInDispensingException("El producte no és un dels dispensables per la eRecepta.");
            }
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

    private boolean isDispensable(ProductID prodId) {
        return ePrescription.getDispensableMedicines().contains(prodId);
    }

    public void calculateFinalAmount() throws SaleClosedException {
        if (isClosed())
            throw new SaleClosedException("La venta ja està tancada");
        calculateAmount();
        addTaxes();
        setClosed();
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

    public Date getDate() {
        return date;
    }

    public int getSaleCode() {
        return saleCode;
    }

    public List<ProductSaleLine> getProductSaleLines() {
        return productSaleLines;
    }

    public Dispensing getePrescription() { return ePrescription; }

    public DispensingTerminal getDispensingTerminal() {
        return dispensingTerminal;
    }

    public ProductSpecification getProductSpec(ProductID prodID) {
        return ePrescription.getProductSpec(prodID);
    }

}
