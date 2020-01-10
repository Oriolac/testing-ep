package data_tests;

import data.PatientContr;
import data.exceptions.FormatErrorException;
import data_tests.testInterfaces.DataClassesInterfaceTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PatientContrExceptionsTest implements DataClassesInterfaceTest {
    private PatientContr patientContr;

    @Override
    @Test
    public void nullConstructorParameterTest() {
        Throwable exception = assertThrows(NullPointerException.class,
                () -> patientContr = new PatientContr(null));
    }

    @Test
    public void getValueTest() throws FormatErrorException {
        patientContr = new PatientContr(new BigDecimal("50.0"));
        BigDecimal contribution = new BigDecimal("50.0");
        assertTrue(patientContr.getPatCont().compareTo(contribution) == 0);
    }

    @Test
    public void equalsTest() throws FormatErrorException {
        PatientContr patientContr1, patientContr2;
        patientContr1 = new PatientContr(new BigDecimal("70.0"));
        patientContr2 = new PatientContr(new BigDecimal("70.0"));
        assertTrue(patientContr1.equals(patientContr2));
    }

    @Test
    public void notEqualsTest() throws FormatErrorException {
        PatientContr patientContr1, patientContr2;
        patientContr1 = new PatientContr(new BigDecimal("70.0"));
        patientContr2 = new PatientContr(new BigDecimal("50.0"));

        assertFalse(patientContr1.equals(patientContr2));
        assertFalse(patientContr1.equals(null));
        assertFalse(patientContr1.equals("70.0"));
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
