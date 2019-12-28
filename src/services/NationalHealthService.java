package services;

/***
 * External service for managing and storing ePrescriptions from population
 */

import data.HealthCardID;
import data.PatientContr;
import data.ProductID;


public interface NationalHealthService {
    Dispensing getePrescription(HealthCardID hcID) throws HealthCardException, NotValidPrescriptionException, ConnectException;

    PatientContr getPatientContr(HealthCardID hcID) throws ConnectException;

    ProductSpecification getProductSpecific(ProductID pID) throws ProductIDException, ConnectException;

    List<Dispensing> updateePrescription(HealthCardID hcID, Dispensing disp) throws ConnectException;
}
