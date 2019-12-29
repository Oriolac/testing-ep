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

    }

    private void calculateAmount() {

    }

    private void addTaxes() {

    }

    public void calculateFinalAmount() throws SaleClosedException {

    }

    public BigDecimal getAmount() {

    }

    private void setClosed() {

    }

    public boolean isClosed() {

    }

    // the rest of getters, setters and methods
}
