package services.exceptions;

public class SaleNotClosedException extends Throwable {
    public SaleNotClosedException(String message) { super(message); }
}
