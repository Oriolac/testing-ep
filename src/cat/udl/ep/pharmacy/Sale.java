package cat.udl.ep.pharmacy;

import cat.udl.ep.DispensingTerminal;
import cat.udl.ep.data.PatientContr;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.pharmacy.exceptions.SaleClosedException;

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
    private final DispensingTerminal dispensingTerminal;

    public Sale(DispensingTerminal dispensingTerminal, Dispensing ePrescription) {
        saleCode = hashCode();
        date = new Date();
        amount = new BigDecimal("0.0");
        isClosed = false;
        productSaleLines = new ArrayList<>();
        this.ePrescription = ePrescription;
        this.dispensingTerminal = dispensingTerminal;
    }

    public void addLine(ProductID prodID, BigDecimal price, PatientContr contr) throws SaleClosedException {
        if (!isClosed()) {
            // TODO: Comprovar que el producte és un dels dispensables
            MedicineDispensingLine medDispensingLine = ePrescription.getMedicineDispensingLine(prodID);
            ProductSaleLine prodSaleLine = new ProductSaleLine(this, medDispensingLine, price, contr);
            productSaleLines.add(prodSaleLine);
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

    public DispensingTerminal getDispensingTerminal() { return dispensingTerminal; }

}
