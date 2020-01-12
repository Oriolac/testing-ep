package dispensing_terminal_test.exceptions.Doubles.SNS;

import cat.udl.ep.data.DispensableMedicines;
import cat.udl.ep.data.HealthCardID;
import cat.udl.ep.data.PatientContr;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.data.exceptions.FormatErrorException;
import cat.udl.ep.pharmacy.Dispensing;
import cat.udl.ep.pharmacy.MedicineDispensingLine;
import cat.udl.ep.pharmacy.ProductSpecification;
import cat.udl.ep.pharmacy.exceptions.NotValidePrescriptionException;
import cat.udl.ep.services.NationalHealthService;
import cat.udl.ep.services.exceptions.HealthCardException;
import cat.udl.ep.services.exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.sql.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class IncorrectUpdateSNS implements NationalHealthService {


    HashMap<HealthCardID, List<Dispensing>> BDD_Dispensings = new HashMap<>();
    HashMap<ProductID, ProductSpecification> BDD_Product = new HashMap<>();
    HashMap<HealthCardID, PatientContr> BDD_Contr = new HashMap<>();
    HashMap<Dispensing, DispensableMedicines> BDD_DispensableMedicines = new HashMap<>();


    public IncorrectUpdateSNS(String code) {
        try {
            HealthCardID hcID = new HealthCardID(code);
            addMockBDD(hcID);
        } catch (FormatErrorException e) {
            e.printStackTrace();
        }
    }

    private void addMockBDD(HealthCardID hcID) throws FormatErrorException {
        BDD_Contr.put(hcID, new PatientContr(new BigDecimal("0.5")));
        ArrayList<Dispensing> disps = new ArrayList<>();
        DispensableMedicines lines = new DispensableMedicines();
        ProductID prodID1 = new ProductID("123456789123");
        ProductID prodID2 = new ProductID("123456789000");
        ProductSpecification prodSpec1 = new ProductSpecification(prodID1, "DESC", BigDecimal.TEN);
        ProductSpecification prodSpec2 = new ProductSpecification(prodID2, "DESC", BigDecimal.TEN);
        BDD_Product.put(prodID1, prodSpec1);
        BDD_Product.put(prodID2, prodSpec2);
        Dispensing eP = new Dispensing(Date.from(Instant.now().minusSeconds(10L)),
                Date.from(Instant.now().plusSeconds(10L)),
                lines);
        MedicineDispensingLine line1 = new MedicineDispensingLine(eP, prodSpec1);
        lines.put(prodID1, line1);
        BDD_DispensableMedicines.put(eP, lines);
        eP.setMedicineDispensingLines(lines);
        disps.add(eP);
        BDD_Dispensings.put(hcID, disps);
    }

    @Override
    public Dispensing getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException {
        if(!BDD_Dispensings.containsKey(hcID))
            throw new HealthCardException();
        List<Dispensing> disps = BDD_Dispensings.get(hcID);
        if(disps.size() == 0)
            throw new NotValidePrescriptionException("No s'ha trobat una dispensació pròxima");
        return disps.get(0);
    }

    @Override
    public PatientContr getPatientContr(HealthCardID hcID) throws HealthCardException {
        if(!BDD_Contr.containsKey(hcID))
            throw new HealthCardException();
        return BDD_Contr.get(hcID);
    }

    @Override
    public ProductSpecification getProductSpecific(ProductID pID) throws ProductNotFoundException {
        if(!BDD_Product.containsKey(pID))
            throw new ProductNotFoundException();
        return BDD_Product.get(pID);
    }

    @Override
    public List<Dispensing> updateePrescription(HealthCardID hcID, Dispensing disp) throws ConnectException {
        List<Dispensing> dispensings = BDD_Dispensings.get(hcID);
        if(hcID.getPersonalID().equals("ALCE098765432112"))
            throw new ConnectException();
        BDD_Dispensings.put(hcID, destroyDispensing(disp, dispensings));
        return dispensings;
    }

    List<Dispensing> destroyDispensing(Dispensing toBeDestroyed, List<Dispensing> dispensings) throws ConnectException {
        if(!dispensings.remove(toBeDestroyed))
            throw new ConnectException();
        BDD_DispensableMedicines.remove(toBeDestroyed);
        return dispensings;
    }
}
