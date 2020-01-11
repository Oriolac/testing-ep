package pharmacy.testinterfaces;

import cat.udl.ep.data.exceptions.FormatErrorException;
import cat.udl.ep.services.exceptions.ProductIDException;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;

public interface PharmacyMethodsTest {

    @Test
    void equalsTest() throws ProductIDException, ConnectException;

    @Test
    void notEqualsTest() throws ProductIDException, ConnectException;

    @Test
    void gettersTest();

    @Test
    void settersTest() throws FormatErrorException;
}
