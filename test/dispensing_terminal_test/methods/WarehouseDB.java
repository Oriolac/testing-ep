package dispensing_terminal_test.methods;

import cat.udl.ep.pharmacy.ProductSaleLine;
import cat.udl.ep.pharmacy.exceptions.InsuficientExistencies;
import cat.udl.ep.services.Warehouse;

import java.util.List;

public class WarehouseDB implements Warehouse {

    public WarehouseDB() {

    }
    @Override
    public void updateStock(List<ProductSaleLine> listOfProducts) {

    }
}
