package dispensing_terminal_test.exceptions.testInterfaces;

import cat.udl.ep.data.exceptions.IDException;
import cat.udl.ep.data.exceptions.PatientIDException;
import cat.udl.ep.data.exceptions.ProductIDException;
import cat.udl.ep.pharmacy.exceptions.*;
import cat.udl.ep.services.exceptions.HealthCardException;
import cat.udl.ep.services.exceptions.ProductNotFoundException;
import cat.udl.ep.services.exceptions.SaleNotInitiatedException;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;

public interface WarehouseExceptionsTestInt {

    @Test
    void insuficientExistencesTest() throws HealthCardException, IDException, ConnectException, NotValidePrescriptionException, DispensingException, ProductNotFoundException, ProductNotInDispensingException, SaleClosedException, SaleNotInitiatedException;
}
