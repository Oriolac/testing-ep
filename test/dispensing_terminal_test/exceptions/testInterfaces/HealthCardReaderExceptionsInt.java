package dispensing_terminal_test.exceptions.testInterfaces;

import cat.udl.ep.pharmacy.exceptions.DispensingNotAvailableException;
import cat.udl.ep.pharmacy.exceptions.NotValidePrescriptionException;
import cat.udl.ep.services.exceptions.HealthCardException;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;

public interface HealthCardReaderExceptionsInt {

    @Test
    void healthCardExceptionTest() throws DispensingNotAvailableException, NotValidePrescriptionException, HealthCardException, ConnectException;
}
