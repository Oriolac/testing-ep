package cat.udl.ep.data;

import cat.udl.ep.pharmacy.MedicineDispensingLine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

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



    public boolean allMatch(Predicate<? super MedicineDispensingLine> action) {
        return medicineDispensingLines.values().parallelStream().allMatch(action);
    }
}
