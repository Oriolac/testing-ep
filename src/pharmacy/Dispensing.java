package pharmacy;

import data.ProductID;
import pharmacy.exceptions.DispensingNotAvailableException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

public class Dispensing {
    /**
     * A class that represents the period for dispensing a certain set of * medicines, its state and the set of medicines
     */
    private byte nOrder; // n. of order for this dispensing inside the treatment
    private Date initDate, finalDate; // The period
    private boolean isCompleted;
    private HashMap<ProductID, MedicineDispensingLine> medicineDispensingLines;
    private Sale sale;
    private DispensingTerminal dispensingTerminal;

    public Dispensing(Date initDate, Date finalDate, HashMap<ProductID, MedicineDispensingLine> medicineDispensingLines) {
        nOrder = (byte) hashCode();
        this.initDate = initDate;
        this.finalDate = finalDate;
        isCompleted = false;
        this.medicineDispensingLines = medicineDispensingLines;
    }

    public boolean dispensingEnabled() throws DispensingNotAvailableException{
        if(Date.from(Instant.now()).after(getInitDate())) {
            return true;
        } else {
            throw new DispensingNotAvailableException("Dispensació no disponible a la data d'avui.");
        }
    }

    public void setProductAsDispensed(ProductID prodID) {
        MedicineDispensingLine medDispensingLine = medicineDispensingLines.get(prodID);
        medDispensingLine.setAcquired();
        medicineDispensingLines.put(prodID, medDispensingLine);
    }

    public Date getInitDate() {
        return initDate;
    }

    public ProductSpecification getProductSpec(ProductID productID) {
        return medicineDispensingLines.get(productID).getProductSpec();
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public MedicineDispensingLine getMedicineDispensingLine(ProductID productID) {
        return medicineDispensingLines.get(productID);
    }

    public HashMap<ProductID, MedicineDispensingLine> getMedicineDispensingLines() {
        return medicineDispensingLines;
    }

    public void setCompleted() {
        isCompleted = true;
    }

    public void setDispensingTerminal(DispensingTerminal dispensingTerminal) {
        this.dispensingTerminal = dispensingTerminal;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

}
