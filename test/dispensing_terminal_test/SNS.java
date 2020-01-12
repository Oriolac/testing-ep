package dispensing_terminal_test;

import cat.udl.ep.data.DispensableMedicines;
import cat.udl.ep.data.HealthCardID;
import cat.udl.ep.data.PatientContr;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.data.exceptions.FormatErrorException;
import cat.udl.ep.data.exceptions.ProductIDException;
import cat.udl.ep.pharmacy.Dispensing;
import cat.udl.ep.pharmacy.MedicineDispensingLine;
import cat.udl.ep.pharmacy.ProductSpecification;
import cat.udl.ep.pharmacy.exceptions.NotValidePrescriptionException;
import cat.udl.ep.services.NationalHealthService;
import cat.udl.ep.services.exceptions.HealthCardException;
import cat.udl.ep.services.exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.List;

public class SNS implements NationalHealthService {

    @Override
    public Dispensing getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
        try {
            Dispensing ePrescription = new Dispensing(Date.from(Instant.now().minusSeconds(10L)), Date.from(Instant.now().plusSeconds(10L)));
            DispensableMedicines dispensableMedicines = initDispensableMedicines(ePrescription);
            ePrescription.setMedicineDispensingLines(dispensableMedicines);
            return ePrescription;
        } catch (ProductIDException e) {
            e.printStackTrace();
        }
        return null;
    }

    private DispensableMedicines initDispensableMedicines(Dispensing ePrescription) throws ProductIDException {
        DispensableMedicines dispensableMedicines = new DispensableMedicines();
        ProductID prod1 = new ProductID("111111111111");
        ProductID prod2 = new ProductID("222222222222");
        ProductID prod3 = new ProductID("333333333333");

        ProductSpecification prodSpec1 = new ProductSpecification(prod1, "prod1" ,new BigDecimal("9.99"));
        ProductSpecification prodSpec2 = new ProductSpecification(prod1, "prod2" ,new BigDecimal("10.00"));
        ProductSpecification prodSpec3 = new ProductSpecification(prod1, "prod3" ,new BigDecimal("15.00"));

        dispensableMedicines.put(prod1, new MedicineDispensingLine(ePrescription, prodSpec1));
        dispensableMedicines.put(prod2, new MedicineDispensingLine(ePrescription, prodSpec2));
        dispensableMedicines.put(prod3, new MedicineDispensingLine(ePrescription, prodSpec3));


        return dispensableMedicines;
    }

    @Override
    public PatientContr getPatientContr(HealthCardID hcID) throws ConnectException {
        try {
            return new PatientContr(new BigDecimal("0.5"));
        } catch (FormatErrorException e) {
            e.printStackTrace();
        }

        throw new ConnectException();
    }

    @Override
    public ProductSpecification getProductSpecific(ProductID pID) throws ProductNotFoundException, ConnectException {
        ProductID prod1=null, prod2=null, prod3=null;
        ProductSpecification productSpec1=null, productSpec2=null, productSpec3=null;
        try {
            prod1 = new ProductID("111111111111");
            productSpec1 = new ProductSpecification(prod1, "prod1", new BigDecimal("9.99"));
            prod2 = new ProductID("222222222222");
            productSpec2 = new ProductSpecification(prod2, "prod2", new BigDecimal("10.0"));
            prod3 = new ProductID("333333333333");
            productSpec3 = new ProductSpecification(prod3, "prod3", new BigDecimal("15.0"));
        } catch (ProductIDException e) {

            e.printStackTrace();
        }

        if (pID.equals(prod1)) {
            return productSpec1;
        } else if (pID.equals(prod2)) {
            return productSpec2;
        } else if (pID.equals(prod3)) {
            return productSpec3;
        }

        throw new ProductNotFoundException();
    }

    @Override
    public List<Dispensing> updateePrescription(HealthCardID hcID, Dispensing disp) throws ConnectException {
        return null;
    }
}
