package dispensing_terminal_test;

import cat.udl.ep.data.DispensableMedicines;
import cat.udl.ep.data.HealthCardID;
import cat.udl.ep.data.PatientContr;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.pharmacy.Dispensing;
import cat.udl.ep.pharmacy.MedicineDispensingLine;
import cat.udl.ep.pharmacy.ProductSpecification;
import cat.udl.ep.pharmacy.exceptions.NotValidePrescriptionException;
import cat.udl.ep.services.NationalHealthService;
import cat.udl.ep.services.exceptions.HealthCardException;
import cat.udl.ep.services.exceptions.ProductIDException;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.util.Date;
import java.util.List;

public class SNS implements NationalHealthService {

    @Override
    public Dispensing getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
        try {
            Dispensing ePrescription = new Dispensing(new Date(), new Date());
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
        return null;
    }

    @Override
    public ProductSpecification getProductSpecific(ProductID pID) throws ProductIDException, ConnectException {
        return null;
    }

    @Override
    public List<Dispensing> updateePrescription(HealthCardID hcID, Dispensing disp) throws ConnectException {
        return null;
    }
}
