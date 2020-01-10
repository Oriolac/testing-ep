package data.PatientContr;

import cat.udl.ep.data.PatientContr;
import cat.udl.ep.data.exceptions.FormatErrorException;
import data.testInterfaces.DataMethodsTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PatientContrMethodsTest implements DataMethodsTest {

    PatientContr patientContr;

    @Test
    public void getValueTest() throws FormatErrorException {
        patientContr = new PatientContr(new BigDecimal("50.0"));
        BigDecimal contribution = new BigDecimal("50.0");
        assertEquals(0, patientContr.getPatCont().compareTo(contribution));
    }

    @Test
    public void equalsTest() throws FormatErrorException {
        PatientContr patientContr1, patientContr2;
        patientContr1 = new PatientContr(new BigDecimal("70.0"));
        patientContr2 = new PatientContr(new BigDecimal("70.0"));
        assertEquals(patientContr1, patientContr2);
    }

    @Test
    public void notEqualsTest() throws FormatErrorException {
        PatientContr patientContr1, patientContr2;
        patientContr1 = new PatientContr(new BigDecimal("70.0"));
        patientContr2 = new PatientContr(new BigDecimal("50.0"));

        assertNotEquals(patientContr1, patientContr2);
        assertNotEquals(null, patientContr1);
        assertNotEquals("70.0", patientContr1);
    }

    @Override
    @Test
    public void formatErrorExceptionTest() {
        assertThrows(FormatErrorException.class,
                () -> patientContr = new PatientContr(new BigDecimal("-20.0")));
        assertThrows(FormatErrorException.class,
                () -> patientContr = new PatientContr(new BigDecimal("105.0")));
    }
}
