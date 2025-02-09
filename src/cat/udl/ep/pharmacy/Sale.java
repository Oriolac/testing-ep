package cat.udl.ep.pharmacy;

import cat.udl.ep.DispensingTerminal;
import cat.udl.ep.data.PatientContr;
import cat.udl.ep.data.Payment;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.pharmacy.exceptions.ProductNotInDispensingException;
import cat.udl.ep.pharmacy.exceptions.SaleClosedException;
import cat.udl.ep.data.exceptions.ProductIDException;
import cat.udl.ep.services.exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/***
 * Package for the classes involved in the use case Suply next dispensing
 */

public class Sale implements SaleInt {
    private int saleCode;
    private Date date;
    private BigDecimal amount;
    private boolean isClosed; // flag to know if the sale is closed
    private List<ProductSaleLine> productSaleLines;
    private final Dispensing ePrescription;
    private DispensingTerminal dispensingTerminal;
    private Payment payment;

    public Sale(DispensingTerminal dispensingTerminal, Dispensing ePrescription) {
        saleCode = hashCode();
        date = new Date();
        amount = new BigDecimal("0.0");
        isClosed = false;
        productSaleLines = new ArrayList<>();
        this.ePrescription = ePrescription;
        this.dispensingTerminal = dispensingTerminal;
        payment = null;
    }

    @Override
    public void addLine(ProductID prodID, BigDecimal price, PatientContr contr) throws SaleClosedException, ProductNotInDispensingException {
        if (!isClosed()) {
            if (isDispensable(prodID)) {
                MedicineDispensingLine medDispensingLine = ePrescription.getMedicineDispensingLine(prodID);
                ProductSaleLine prodSaleLine = new ProductSaleLine(this, getProductSpec(prodID), price, contr);
                productSaleLines.add(prodSaleLine);
            } else {
                throw new ProductNotInDispensingException("El producte no és un dels dispensables");
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

    public boolean isDispensable(ProductID productID) {
        return ePrescription.getDispensableMedicines().contains(productID);
    }

    @Override
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

    @Override
    public int getSaleCode() {
        return saleCode;
    }

    public List<ProductSaleLine> getProductSaleLines() {
        return productSaleLines;
    }

    @Override
    public ProductSaleLine getProductSaleLine(ProductID productID) throws ProductNotFoundException {
        for (ProductSaleLine productSaleLine : productSaleLines) {
            if (productSaleLine.getProductSpec().getProdID().equals(productID)) {
                return productSaleLine;
            }
        }
        throw new ProductNotFoundException();
    }

    @Override
    public Dispensing getePrescription() { return ePrescription; }

    @Override
    public DispensingTerminal getDispensingTerminal() { return dispensingTerminal; }

    public ProductSpecification getProductSpec(ProductID prodID) {
        return ePrescription.getProductSpec(prodID);
    }

    public Payment getPayment() {
        return this.payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

}
