package dispensing_terminal_test;

import cat.udl.ep.data.HealthCardID;
import cat.udl.ep.data.PatientContr;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.pharmacy.Dispensing;
import cat.udl.ep.pharmacy.ProductSpecification;
import cat.udl.ep.pharmacy.exceptions.NotValidePrescriptionException;
import cat.udl.ep.services.NationalHealthService;
import cat.udl.ep.services.exceptions.HealthCardException;
import cat.udl.ep.services.exceptions.ProductIDException;

import java.net.ConnectException;
import java.util.List;

public class SNS implements NationalHealthService {
    @Override
    public Dispensing getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
        return null;
    }

    @Override
    public PatientContr getPatientContr(HealthCardID hcID) throws ConnectException {
        return null;
    }

    @Override
    public ProductSpecification getProductSpecific(ProductID pID) throws ProductIDException, ConnectException {
        return null;
    }

    @Override
    public List<Dispensing> updateePrescription(HealthCardID hcID, Dispensing disp) throws ConnectException {
        return null;
    }
}
