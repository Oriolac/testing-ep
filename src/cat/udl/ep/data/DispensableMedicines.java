package cat.udl.ep.data;

import cat.udl.ep.pharmacy.MedicineDispensingLine;


import java.util.*;


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

    public HashMap<ProductID, MedicineDispensingLine> medicineDispensingLinesHashMap() {
        return medicineDispensingLines;
    }


    public boolean allMatch(Predicate<? super MedicineDispensingLine> action) {
        return medicineDispensingLines.values().parallelStream().allMatch(action);
    }
}
