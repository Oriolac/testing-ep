package services;

import data.HealthCardID;
import data.exceptions.HealthCardException;

public interface HealthCardReader {
    HealthCardID getHealthCardID() throws HealthCardException;
}
