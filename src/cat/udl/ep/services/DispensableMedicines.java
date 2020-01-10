package cat.udl.ep.services;

import cat.udl.ep.data.ProductID;
import cat.udl.ep.pharmacy.MedicineDispensingLine;

import java.util.HashMap;

public class DispensableMedicines {
    private HashMap<ProductID, MedicineDispensingLine> medicineDispensingLines;

    public DispensableMedicines() {
        medicineDispensingLines = new HashMap<>();
    }

    public MedicineDispensingLine get(ProductID prodID) {
        return medicineDispensingLines.get(prodID);
    }

    public void put(ProductID prodID, MedicineDispensingLine medDispensingLine) {
        medicineDispensingLines.put(prodID, medDispensingLine);
    }

    public boolean contains(ProductID prodId) {
        return medicineDispensingLines.containsKey(prodId);
    }
}
