package cat.udl.ep.pharmacy;

import cat.udl.ep.DispensingTerminal;
import cat.udl.ep.data.DispensableMedicines;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.pharmacy.exceptions.DispensingNotAvailableException;
import cat.udl.ep.services.exceptions.ProductIDException;

import java.net.ConnectException;
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
    private DispensableMedicines medicineDispensingLines;
    private Sale sale;
    private DispensingTerminal dispensingTerminal;

    public Dispensing(Date initDate, Date finalDate, DispensableMedicines medicineDispensingLines) {
        nOrder = (byte) hashCode();
        this.initDate = initDate;
        this.finalDate = finalDate;
        isCompleted = false;
        this.medicineDispensingLines = medicineDispensingLines;
    }

    public Dispensing(DispensableMedicines medicineDispensingLines) {
        this.medicineDispensingLines = medicineDispensingLines;
    }

    public Dispensing() {

    }

    public boolean dispensingEnabled() throws DispensingNotAvailableException{
        if(Date.from(Instant.now()).after(getInitDate())) {
            return true;
        } else {
            throw new DispensingNotAvailableException("Dispensació no disponible a la cat.udl.ep.data d'avui.");
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

    public ProductSpecification getProductSpec(ProductID productID) throws ProductIDException, ConnectException {
        return sale.getDispensingTerminal().getProductSpec(productID);
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public MedicineDispensingLine getMedicineDispensingLine(ProductID productID) {
        return medicineDispensingLines.get(productID);
    }

    public void setCompleted() {
        isCompleted = true;
    }

    public boolean isCompleted() { return isCompleted; }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public void setDispensingTerminal(DispensingTerminal dispensingTerminal) {
    }
}
