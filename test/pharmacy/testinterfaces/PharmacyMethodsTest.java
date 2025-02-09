package pharmacy.testinterfaces;

import cat.udl.ep.data.exceptions.FormatErrorException;
import cat.udl.ep.pharmacy.exceptions.DispensingException;
import cat.udl.ep.data.exceptions.ProductIDException;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;

public interface PharmacyMethodsTest {

    @Test
    void equalsTest() throws ProductIDException, ConnectException, FormatErrorException;

    @Test
    void notEqualsTest() throws ProductIDException, ConnectException, FormatErrorException;

    @Test
    void gettersTest() throws ProductIDException;

    @Test
    void settersTest() throws FormatErrorException, ProductIDException, DispensingException;
}
