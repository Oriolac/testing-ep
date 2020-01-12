package dispensing_terminal_test.exceptions.Doubles;

import cat.udl.ep.pharmacy.SaleInt;
import cat.udl.ep.services.SalesHistory;

public class SalesHistoryDB implements SalesHistory {
    @Override
    public void registerSale(SaleInt sale) {

    }

    @Override
    public SaleInt getSale(int SaleCode) {
        return null;
    }
}
