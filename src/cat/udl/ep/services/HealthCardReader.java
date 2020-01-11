package cat.udl.ep.services;

import cat.udl.ep.data.HealthCardID;
import cat.udl.ep.data.exceptions.HealthCardException;

public interface HealthCardReader {
    HealthCardID getHealthCardID() throws HealthCardException;
}
