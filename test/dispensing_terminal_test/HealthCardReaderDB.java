package dispensing_terminal_test;

import cat.udl.ep.data.HealthCardID;
import cat.udl.ep.data.exceptions.PatientIDException;
import cat.udl.ep.services.HealthCardReader;
import cat.udl.ep.services.exceptions.HealthCardException;

public class HealthCardReaderDB implements HealthCardReader {
    @Override
    public HealthCardID getHealthCardID() throws PatientIDException {
        return new HealthCardID("ZZZZ473298320193");
    }
}
