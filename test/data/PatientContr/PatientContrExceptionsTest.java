package data.PatientContr;

import cat.udl.ep.data.PatientContr;
import cat.udl.ep.data.exceptions.FormatErrorException;
import data.testInterfaces.DataExceptionsTest;
import data.testInterfaces.NullInterfaceTest;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class PatientContrExceptionsTest implements DataExceptionsTest, NullInterfaceTest {
    private PatientContr patientContr;

    @Override
    @Test
    public void nullConstructorParameterTest() {
        assertThrows(NullPointerException.class,
                () -> new PatientContr(null));
    }

    @Override
    @Test
    public void dataErrorExceptionTest() {
        assertThrows(FormatErrorException.class,
                () -> new PatientContr(new BigDecimal("-20.0")));
        assertThrows(FormatErrorException.class,
                () -> new PatientContr(new BigDecimal("105.0")));
    }

    @Override
    public void nullEqualsParameterTest() throws FormatErrorException {
        PatientContr patientContr = new PatientContr(BigDecimal.ONE);
        assertNotEquals(null, patientContr);
    }
}
