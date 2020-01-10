package cat.udl.ep;

import cat.udl.ep.data.ProductID;
import cat.udl.ep.pharmacy.exceptions.*;
import cat.udl.ep.services.exceptions.HealthCardException;
import cat.udl.ep.services.exceptions.ProductIDException;
import cat.udl.ep.pharmacy.Dispensing;
import cat.udl.ep.pharmacy.ProductSpecification;
import cat.udl.ep.pharmacy.Sale;
import cat.udl.ep.services.HealthCardReader;
import cat.udl.ep.services.NationalHealthService;
import cat.udl.ep.services.exceptions.QuantityMinorThanImport;
import cat.udl.ep.services.exceptions.SaleNotClosedException;
import cat.udl.ep.services.exceptions.SaleNotInitiatedException;

import java.math.BigDecimal;
import java.net.ConnectException;

public class DispensingTerminal {

    final static private char BY_HEALTHCARD = 'i';
    final static private char BY_SHEET_TREATMENT = 't';
    final static private char MANUALLY = 'm';

    private Sale sale;

    private Dispensing ePrescription;
    private NationalHealthService SNS;
    private HealthCardReader HCR;

    public DispensingTerminal() {

    }

    public DispensingTerminal(NationalHealthService SNS, HealthCardReader HCR) {
        this.SNS = SNS;
        this.HCR = HCR;
    }

    public void getePrescription(char option) throws HealthCardException, NotValidePrescriptionException, ConnectException, PatientIDException {
        switch (option){
            case BY_HEALTHCARD:
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

    public void enterProduct(ProductID pID) throws SaleClosedException, ConnectException, ProductIDException, HealthCardException, ProductNotInDispensingException {
        ProductSpecification productSpecification = getProductSpec(pID);
        sale.addLine(pID, productSpecification.getPrice() ,SNS.getPatientContr(HCR.getHealthCardID()));
        ePrescription.setProductAsDispensed(pID);
    }

    public void finalizeSale() throws SaleNotInitiatedException, SaleClosedException {
        if (sale == null)
            throw new SaleNotInitiatedException("No hi ha venta en curs");
        sale.calculateFinalAmount();
    }

    public void realizePayment(BigDecimal quantity) throws QuantityMinorThanImport, SaleNotClosedException {
        if (!sale.isClosed())
            throw new SaleNotClosedException("La venda no està tancada.");
        BigDecimal amount = sale.getAmount();
        if(amount.compareTo(quantity) < 0)
            throw new QuantityMinorThanImport("La quantitat és menor que l'import a pagar.");
        //TODO: S'ha d'acabar de fer a la part opcional!!
    }

    public void realizePayment(float quantity)  {
        try {
            throw new NotImplementedException();
        } catch (NotImplementedException e) {
            e.printStackTrace();
        }
    }

    public void printNextDispensingSheet() {
        try {
            throw new NotImplementedException();
        } catch (NotImplementedException e) {
            e.printStackTrace();
        }
    }

    public ProductSpecification getProductSpec(ProductID productID) throws ProductIDException, ConnectException {
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
