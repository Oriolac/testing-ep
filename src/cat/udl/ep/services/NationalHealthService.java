package cat.udl.ep.services;

/*
  External service for managing and storing ePrescriptions from population
 */

import cat.udl.ep.data.HealthCardID;
import cat.udl.ep.data.PatientContr;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.pharmacy.Dispensing;
import cat.udl.ep.pharmacy.ProductSpecification;
import cat.udl.ep.services.exceptions.HealthCardException;
import cat.udl.ep.pharmacy.exceptions.NotValidePrescriptionException;
import cat.udl.ep.services.exceptions.ProductNotFoundException;

import java.net.ConnectException;
import java.util.List;


public interface NationalHealthService {
    Dispensing getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException;

    PatientContr getPatientContr(HealthCardID hcID) throws ConnectException, HealthCardException;

    ProductSpecification getProductSpecific(ProductID pID) throws ProductNotFoundException, ConnectException;

    List<Dispensing> updateePrescription(HealthCardID hcID, Dispensing disp) throws ConnectException;
}
