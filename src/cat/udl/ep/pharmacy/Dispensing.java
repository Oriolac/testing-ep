package cat.udl.ep.pharmacy;

import cat.udl.ep.DispensingTerminal;
import cat.udl.ep.data.DispensableMedicines;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.pharmacy.exceptions.CompletedDispensingException;
import cat.udl.ep.pharmacy.exceptions.DispensingException;
import cat.udl.ep.pharmacy.exceptions.DispensingNotAvailableException;
import cat.udl.ep.pharmacy.exceptions.AcquiredMedicineDispensingLineException;

import java.time.Instant;
import java.util.Arrays;
import java.util.Date;

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

    public Dispensing(Date initDate, Date finalDate) {
        this((byte) 0, initDate, finalDate, new DispensableMedicines());
        this.nOrder = (byte) hashCode();
    }

    public Dispensing(byte nOrder, Date initDate, Date finalDate) {
        this((byte) 0, initDate, finalDate, new DispensableMedicines());
        this.nOrder = (byte) hashCode();
    }

    public Dispensing(Date initDate, Date finalDate, DispensableMedicines medicineDispensingLines) {
        this((byte) 0, initDate, finalDate, medicineDispensingLines);
        this.nOrder = (byte) hashCode();
    }

    public Dispensing(byte nOrder, Date initDate, Date finalDate, DispensableMedicines medicineDispensingLines) {
        this.nOrder = nOrder;
        this.initDate = initDate;
        this.finalDate = finalDate;
        isCompleted = false;
        this.medicineDispensingLines = medicineDispensingLines;
    }


    public boolean dispensingEnabled() throws DispensingNotAvailableException {
        if(Date.from(Instant.now()).after(getInitDate())) {
            return true;
        } else {
            throw new DispensingNotAvailableException("Dispensació no disponible a la cat.udl.ep.data d'avui.");
        }
    }

    public void setProductAsDispensed(ProductID prodID) throws DispensingException {
        if (dispensingEnabled() && isCompleted())
            throw new CompletedDispensingException();
        MedicineDispensingLine medDispensingLine = medicineDispensingLines.get(prodID);
        if (medDispensingLine.isAcquired())
            throw new AcquiredMedicineDispensingLineException();
        medDispensingLine.setAcquired();
        medicineDispensingLines.put(prodID, medDispensingLine);
        if(medicineDispensingLines.allMatch(MedicineDispensingLine::isAcquired))
            setCompleted();
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

    public DispensableMedicines getDispensableMedicines() {
        return medicineDispensingLines;
    }

    public DispensingTerminal getDispensingTerminal() {
        return dispensingTerminal;
    }

    public void setCompleted() {
        isCompleted = true;
    }

    public boolean isCompleted() { return isCompleted; }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public void setDispensingTerminal(DispensingTerminal dispensingTerminal) {
        this.dispensingTerminal = dispensingTerminal;
    }

    public void setMedicineDispensingLines(DispensableMedicines medicineDispensingLines) {
        this.medicineDispensingLines = medicineDispensingLines;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Dispensing)){
            return false;
        }
        Dispensing o = (Dispensing) obj;
        return this.nOrder == o.nOrder;
    }
}
