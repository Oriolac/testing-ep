package cat.udl.ep.services;

import cat.udl.ep.data.HealthCardID;
import cat.udl.ep.data.exceptions.PatientIDException;
import cat.udl.ep.services.exceptions.HealthCardException;

public interface HealthCardReader {
    HealthCardID getHealthCardID() throws HealthCardException, PatientIDException;
}
