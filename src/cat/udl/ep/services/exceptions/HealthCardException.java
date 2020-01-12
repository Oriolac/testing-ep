package cat.udl.ep.services.exceptions;

import cat.udl.ep.data.exceptions.IDException;

public class HealthCardException extends Throwable{
    public HealthCardException(String message) {
        super(message);
    }

    public HealthCardException() {
        super();
    }
}