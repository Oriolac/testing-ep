package services;

/**
 * External service for managing and storing ePrescriptions from population
 */

import data.HealthCardID;
import data.PatientContr;
import data.ProductID;
import data.exceptions.ProductIDException;
import pharmacy.Dispensing;
import pharmacy.ProductSpecification;
import data.exceptions.HealthCardException;
import pharmacy.exceptions.NotValidePrescriptionException;

import java.net.ConnectException;
import java.util.List;


public interface NationalHealthService {
    Dispensing getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException;

    PatientContr getPatientContr(HealthCardID hcID) throws ConnectException;

    ProductSpecification getProductSpecific(ProductID pID) throws ProductIDException, ConnectException;

    List<Dispensing> updateePrescription(HealthCardID hcID, Dispensing disp) throws ConnectException;
}
