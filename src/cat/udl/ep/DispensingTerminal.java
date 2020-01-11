package cat.udl.ep;

import cat.udl.ep.data.CashPayment;
import cat.udl.ep.data.Payment;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.services.exceptions.HealthCardException;
import cat.udl.ep.data.exceptions.IDException;
import cat.udl.ep.pharmacy.exceptions.*;
import cat.udl.ep.services.SalesHistory;
import cat.udl.ep.services.Warehouse;
import cat.udl.ep.services.exceptions.*;
import cat.udl.ep.pharmacy.Dispensing;
import cat.udl.ep.pharmacy.ProductSpecification;
import cat.udl.ep.pharmacy.Sale;
import cat.udl.ep.services.HealthCardReader;
import cat.udl.ep.services.NationalHealthService;

import java.math.BigDecimal;
import java.net.ConnectException;

public class DispensingTerminal {

    final static private char BY_HEALTHCARDID = 'i';
    final static private char BY_SHEET_TREATMENT = 't';
    final static private char MANUALLY = 'm';

    private Sale sale;

    private Dispensing ePrescription;
    private NationalHealthService SNS;
    private HealthCardReader HCR;
    private SalesHistory salesHistory;
    private Warehouse warehouse;

    public DispensingTerminal(NationalHealthService SNS, HealthCardReader HCR, SalesHistory salesHistory, Warehouse warehouse) {
        this.SNS = SNS;
        this.HCR = HCR;
        this.salesHistory = salesHistory;
        this.warehouse = warehouse;
    }

    public void getePrescription(char option) throws HealthCardException, NotValidePrescriptionException, ConnectException {
        switch (option){
            case BY_HEALTHCARDID:
                ePrescription = SNS.getePrescription(HCR.getHealthCardID());
                break;
            case BY_SHEET_TREATMENT:
                break;
            case MANUALLY:
                break;
            default:
                break;
        }
    }

    public void initNewSale() throws DispensingNotAvailableException {
        if(ePrescription == null || !ePrescription.dispensingEnabled())
            throw new DispensingNotAvailableException("La E-Recepta no està encara habilitada");
        sale = new Sale(this, ePrescription);
    }

    public void enterProduct(ProductID pID) throws SaleClosedException, ConnectException, IDException, DispensingException, ProductNotInDispensingException, ProductNotFoundException {
        ProductSpecification productSpecification = getProductSpec(pID);
        sale.addLine(pID, productSpecification.getPrice() ,SNS.getPatientContr(HCR.getHealthCardID()));
        ePrescription.setProductAsDispensed(pID);
    }

    public void finalizeSale() throws SaleNotInitiatedException, SaleClosedException {
        if (sale == null)
            throw new SaleNotInitiatedException("No hi ha venta en curs");
        sale.calculateFinalAmount();
    }

    public void realizePayment(BigDecimal quantity) throws ConnectException, QuantityMinorThanImport, InsuficientExistencies, SaleNotClosedException, HealthCardException {
        if (!sale.isClosed())
            throw new SaleNotClosedException("La venda no està tancada.");
        Payment payment = new CashPayment(sale.getAmount(), quantity);
        payment.setSale(sale);
        sale.setPayment(payment);
        warehouse.updateStock(sale.getProductSaleLines());
        salesHistory.registerSale(sale);
        SNS.updateePrescription(HCR.getHealthCardID(), ePrescription);
    }

    public void printNextDispensingSheet() {
        try {
            throw new NotImplementedException();
        } catch (NotImplementedException e) {
            e.printStackTrace();
        }
    }

    public ProductSpecification getProductSpec(ProductID productID) throws ConnectException, ProductNotFoundException {
        return SNS.getProductSpecific(productID);
    }

    public Sale getSale() {
        return sale;
    }

    public Dispensing getePrescription() {
        return ePrescription;
    }

    public NationalHealthService getSNS() {
        return SNS;
    }

    public HealthCardReader getHCR() {
        return HCR;
    }

}
