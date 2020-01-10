package data.PatientContr;

import data.exceptions.FormatErrorException;
import data.testInterfaces.DataExceptionsTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PatientContrExceptionsTest implements DataExceptionsTest {
    private PatientContr patientContr;

    @Override
    @Test
    public void nullConstructorParameterTest() {
        Throwable exception = assertThrows(NullPointerException.class,
                () -> patientContr = new PatientContr(null));
    }

    @Override
    @Test
    public void dataErrorExceptionTest() {
        Throwable exception = assertThrows(FormatErrorException.class,
                () -> patientContr = new PatientContr(new BigDecimal("-20.0")));
        exception = assertThrows(FormatErrorException.class,
                () -> patientContr = new PatientContr(new BigDecimal("105.0")));
    }

}
