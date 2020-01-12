package dispensing_terminal_test.methods;

import cat.udl.ep.pharmacy.Sale;
import cat.udl.ep.pharmacy.SaleInt;
import cat.udl.ep.services.SalesHistory;

import java.util.HashMap;

public class SalesHistoryDB implements SalesHistory {

    HashMap<Integer, SaleInt> BDD = new HashMap<>();

    @Override
    public void registerSale(SaleInt sale) {
        BDD.put(sale.getSaleCode(), sale);
    }

    @Override
    public SaleInt getSale(int SaleCode){
        return BDD.get(SaleCode);
    }
}
