package dispensing_terminal_test.exceptions.testInterfaces;

import cat.udl.ep.data.exceptions.FormatErrorException;
import cat.udl.ep.data.exceptions.PatientIDException;
import cat.udl.ep.data.exceptions.ProductIDException;
import cat.udl.ep.pharmacy.exceptions.*;
import cat.udl.ep.services.exceptions.HealthCardException;
import cat.udl.ep.services.exceptions.ProductNotFoundException;
import cat.udl.ep.services.exceptions.SaleNotInitiatedException;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;

public interface SNSExceptionsInt {

    @Test
    void getePrescriptionExceptionsTest() throws HealthCardException, PatientIDException, ConnectException, NotValidePrescriptionException;

    @Test
    void getPatientContrConnectionExceptionTest() throws HealthCardException, ProductIDException, ProductNotFoundException, ConnectException;

    @Test
    void getProductSpecificConnectionExceptionTest() throws ProductIDException;

    @Test
    void updateePrescriptionConnectionTest() throws HealthCardException, ConnectException, NotValidePrescriptionException, DispensingException, FormatErrorException, ProductNotFoundException, ProductNotInDispensingException, SaleClosedException, SaleNotInitiatedException;

    @Test
    void getProductSpecificProductNotFoundExceptionTest() throws ProductIDException;

}
