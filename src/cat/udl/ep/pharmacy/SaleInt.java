package cat.udl.ep.pharmacy;

import cat.udl.ep.DispensingTerminal;
import cat.udl.ep.data.PatientContr;
import cat.udl.ep.data.Payment;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.data.exceptions.ProductIDException;
import cat.udl.ep.pharmacy.exceptions.ProductNotInDispensingException;
import cat.udl.ep.pharmacy.exceptions.SaleClosedException;
import cat.udl.ep.services.SalesHistory;
import cat.udl.ep.services.Warehouse;
import cat.udl.ep.services.exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.List;

public interface SaleInt {


    Dispensing getePrescription();
    
    boolean isClosed();

    void addLine(ProductID pID, BigDecimal price, PatientContr patientContr) throws SaleClosedException, ProductNotInDispensingException, ProductIDException, ConnectException;

    void calculateFinalAmount() throws SaleClosedException;

    BigDecimal getAmount();

    void setPayment(Payment payment);

    List<ProductSaleLine> getProductSaleLines();

    DispensingTerminal getDispensingTerminal();

    ProductSaleLine getProductSaleLine(ProductID prod1) throws ProductNotFoundException;

    Payment getPayment();

    int getSaleCode();

}
