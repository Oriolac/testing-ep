package cat.udl.ep.services;

import cat.udl.ep.pharmacy.ProductSaleLine;
import cat.udl.ep.pharmacy.exceptions.InsuficientExistencies;

import java.util.List;

public interface Warehouse {
    void updateStock(List<ProductSaleLine> listOfProducts) throws InsuficientExistencies;
}
