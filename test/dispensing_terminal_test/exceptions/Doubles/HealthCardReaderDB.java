package dispensing_terminal_test.exceptions.Doubles;

import cat.udl.ep.data.HealthCardID;
import cat.udl.ep.data.exceptions.PatientIDException;
import cat.udl.ep.services.HealthCardReader;
import cat.udl.ep.services.exceptions.HealthCardException;

public class HealthCardReaderDB implements HealthCardReader {

    String code;

    public HealthCardReaderDB(String code){
        this.code = code;
    }

    @Override
    public HealthCardID getHealthCardID() {
        try {
            return new HealthCardID(code);
        } catch (PatientIDException e) {
            e.printStackTrace();
        }
        return null;
    }
}
