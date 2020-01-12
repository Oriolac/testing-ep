package dispensing_terminal_test.exceptions.Doubles.Warehouse;

import cat.udl.ep.pharmacy.ProductSaleLine;
import cat.udl.ep.services.Warehouse;

import java.util.List;

public class InfiniteWarehouseDB implements Warehouse {
    @Override
    public void updateStock(List<ProductSaleLine> listOfProducts) {

    }
}
