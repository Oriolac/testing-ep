package cat.udl.ep.services;

import cat.udl.ep.pharmacy.SaleInt;

public interface SalesHistory {
    void registerSale(SaleInt sale);

    SaleInt getSale(int SaleCode);
}
