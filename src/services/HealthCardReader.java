package services;

import data.HealthCardID;

public interface HealthCardReader {
    HealthCardID getHealthCardID() throws HealthCardException;
}
