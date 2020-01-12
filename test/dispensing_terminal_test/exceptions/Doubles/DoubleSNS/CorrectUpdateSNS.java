package dispensing_terminal_test.exceptions.Doubles.DoubleSNS;

import cat.udl.ep.data.DispensableMedicines;
import cat.udl.ep.data.HealthCardID;
import cat.udl.ep.data.PatientContr;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.data.exceptions.FormatErrorException;
import cat.udl.ep.data.exceptions.PatientIDException;
import cat.udl.ep.data.exceptions.ProductIDException;
import cat.udl.ep.pharmacy.Dispensing;
import cat.udl.ep.pharmacy.MedicineDispensingLine;
import cat.udl.ep.pharmacy.ProductSpecification;
import cat.udl.ep.services.exceptions.ProductNotFoundException;

import java.math.BigDecimal;
import java.net.ConnectException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class CorrectUpdateSNS extends IncorrectUpdateSNS {

    private String code;

    public CorrectUpdateSNS(String code) {
        super(code);
        BDD_Dispensings = new HashMap<>();
        BDD_Product = new HashMap<>();
        BDD_Contr = new HashMap<>();
        BDD_DispensableMedicines = new HashMap<>();
        try {
            addMockBDD(new HealthCardID(code));
        } catch (FormatErrorException e) {
            e.printStackTrace();
        }
    }

    private void addMockBDD(HealthCardID hcID) throws FormatErrorException {
        BDD_Contr.put(hcID, new PatientContr(new BigDecimal("0.5")));
        DispensableMedicines lines = new DispensableMedicines();
        ProductID prodID1 = new ProductID("123456789123");
        ProductID prodID2 = new ProductID("123456789000");
        ProductSpecification prodSpec1 = new ProductSpecification(prodID1, "DESC", BigDecimal.TEN);
        ProductSpecification prodSpec2 = new ProductSpecification(prodID2, "DESC", BigDecimal.TEN);
        BDD_Product.put(prodID1, prodSpec1);
        BDD_Product.put(prodID2, prodSpec2);
        Date from = Date.from(Instant.now().minusSeconds(10L));
        Date to = Date.from(Instant.now().plusSeconds(10L));
        addDispensingInDB(from, to, lines, hcID, prodSpec1);

    }

    private void addDispensingInDB(Date from, Date to, DispensableMedicines lines, HealthCardID hcID, ProductSpecification prodS) {
        ArrayList<Dispensing> disps = new ArrayList<>();
        disps.add(addDisps(from, to, lines, prodS));
        disps.add(addDisps(to, to, lines, prodS));
        BDD_Dispensings.put(hcID, disps);
    }

    private Dispensing addDisps(Date from, Date to, DispensableMedicines lines, ProductSpecification prodS) {
        Dispensing eP = new Dispensing(from, to, lines);
        MedicineDispensingLine line1 = new MedicineDispensingLine(eP, prodS);
        lines.put(prodS.getProdID(), line1);
        BDD_DispensableMedicines.put(eP, lines);
        eP.setMedicineDispensingLines(lines);
        return eP;
    }

    @Override
    public List<Dispensing> updateePrescription(HealthCardID hcID, Dispensing disp) throws ConnectException {
        List<Dispensing> dispensings = BDD_Dispensings.get(hcID);
        BDD_Dispensings.put(hcID, destroyDispensing(disp, dispensings));
        return dispensings;
    }
}
