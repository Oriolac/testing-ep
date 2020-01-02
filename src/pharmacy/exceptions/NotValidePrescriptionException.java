package pharmacy.exceptions;

public class NotValidePrescriptionException extends Throwable {
    public NotValidePrescriptionException(String message) {
        super(message);
    }
}
