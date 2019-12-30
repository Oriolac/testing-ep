import data.HealthCardID;
import data.ProductID;
import pharmacy.Dispensing;
import pharmacy.ProductSpecification;
import pharmacy.Sale;
import pharmacy.exceptions.DispensingNotAvailableException;
import pharmacy.exceptions.SaleClosedException;
import services.HealthCardReader;
import services.NationalHealthService;

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

    public void getePrescription(char option) throws HealthCardException, NotValidPrescriptionException, ConnectException, PatientIDException {
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
        sale = new Sale(ePrescription);
    }

    public void enterProduct(ProductID pID) throws SaleClosedException, ConnectException {
        ProductSpecification productSpecification = SNS.getProductSpecific(pID);
        sale.addLine(pID, productSpecification.getPrice() ,SNS.getPatientContr(HCR.getHealthCardID()));
        ePrescription.setProductAsDispensed(pID);
    }




}
