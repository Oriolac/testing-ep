package pharmacy;

import data.HealthCardID;
import data.ProductID;
import data.exceptions.HealthCardException;
import data.exceptions.ProductIDException;
import pharmacy.Dispensing;
import pharmacy.ProductSpecification;
import pharmacy.Sale;
import pharmacy.exceptions.DispensingNotAvailableException;
import pharmacy.exceptions.NotValidePrescriptionException;
import pharmacy.exceptions.PatientIDException;
import pharmacy.exceptions.SaleClosedException;
import services.HealthCardReader;
import services.NationalHealthService;
import services.exceptions.QuantityMinorThanImport;
import services.exceptions.SaleNotClosedException;
import services.exceptions.SaleNotInitiatedException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.net.ConnectException;

public class DispensingTerminal {

    final static private char BY_HEALTHCARD = 'i';
    final static private char BY_SHEET_TREATMENT = 't';
    final static private char MANUALLY = 'm';

    Sale sale;
    Dispensing ePrescription;
    NationalHealthService SNS;
    HealthCardReader HCR;

    public DispensingTerminal(NationalHealthService SNS, HealthCardReader HCR) {
        this.SNS = SNS;
        this.HCR = HCR;
    }

    public void getePrescription(char option) throws HealthCardException, NotValidePrescriptionException, ConnectException, PatientIDException {
        switch (option){
            case BY_HEALTHCARD:
                ePrescription = SNS.getePrescription(HCR.getHealthCardID());
                ePrescription.setDispensingTerminal(this);
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

    public void enterProduct(ProductID pID) throws SaleClosedException, ConnectException, ProductIDException, HealthCardException {
        ProductSpecification productSpecification = SNS.getProductSpecific(pID);
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

    public void realizePayment() {
        throw new NotImplementedException();
    }

    public void printNextDispensingSheet() {
        throw new NotImplementedException();
    }

    public ProductSpecification getProductSpec(ProductID productID) throws ProductIDException, ConnectException {
        return SNS.getProductSpecific(productID);
    }

}
