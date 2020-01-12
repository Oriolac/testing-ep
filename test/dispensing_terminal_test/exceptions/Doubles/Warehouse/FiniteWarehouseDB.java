package dispensing_terminal_test.exceptions.Doubles.Warehouse;

import cat.udl.ep.data.ProductID;
import cat.udl.ep.pharmacy.ProductSaleLine;
import cat.udl.ep.pharmacy.exceptions.InsuficientExistencies;
import cat.udl.ep.services.Warehouse;

import java.util.HashMap;
import java.util.List;

public class FiniteWarehouseDB implements Warehouse {

    HashMap<ProductID, Integer> BD = new HashMap<>();

    @Override
    public void updateStock(List<ProductSaleLine> listOfProducts) throws InsuficientExistencies {
        if(listOfProducts.stream().allMatch((productSaleLine) ->
                !BD.containsKey(productSaleLine.getProductSpec().getProdID()) ||
                BD.get(productSaleLine.getProductSpec().getProdID()).equals(0)))
            throw new InsuficientExistencies("No hi ha suficients existències");
    }
}
