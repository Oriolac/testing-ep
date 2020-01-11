package dispensing_terminal_test;

import cat.udl.ep.pharmacy.ProductSaleLine;
import cat.udl.ep.pharmacy.exceptions.InsuficientExistencies;
import cat.udl.ep.services.Warehouse;

import java.util.List;

public class WarehouseDB implements Warehouse {
    @Override
    public void updateStock(List<ProductSaleLine> listOfProducts) throws InsuficientExistencies {

    }
}
