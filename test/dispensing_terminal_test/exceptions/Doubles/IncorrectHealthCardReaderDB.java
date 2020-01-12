package dispensing_terminal_test.exceptions.Doubles;

import cat.udl.ep.data.HealthCardID;
import cat.udl.ep.data.exceptions.PatientIDException;
import cat.udl.ep.services.HealthCardReader;
import cat.udl.ep.services.exceptions.HealthCardException;

public class IncorrectHealthCardReaderDB implements HealthCardReader {

    public IncorrectHealthCardReaderDB() { super(); }

    @Override
    public HealthCardID getHealthCardID() throws HealthCardException {
        throw new HealthCardException();
    }
}
