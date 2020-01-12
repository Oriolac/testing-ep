package dispensing_terminal_test.methods.Doubles;

import cat.udl.ep.data.HealthCardID;
import cat.udl.ep.data.exceptions.PatientIDException;
import cat.udl.ep.services.HealthCardReader;

public class HealthCardReaderDB implements HealthCardReader {
    @Override
    public HealthCardID getHealthCardID() throws PatientIDException {
        return new HealthCardID("ZZZZ473298320193");
    }
}
