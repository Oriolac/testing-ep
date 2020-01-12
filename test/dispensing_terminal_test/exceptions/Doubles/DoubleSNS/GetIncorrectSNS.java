package dispensing_terminal_test.exceptions.Doubles.DoubleSNS;

import cat.udl.ep.data.DispensableMedicines;
import cat.udl.ep.data.HealthCardID;
import cat.udl.ep.data.PatientContr;
import cat.udl.ep.data.ProductID;
import cat.udl.ep.data.exceptions.PatientIDException;
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
import java.util.Date;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GetIncorrectSNS implements NationalHealthService {

    private String code;
    private final String correctCode = "ALCE098765432112";
    private final String emptyCode = "ALCE123412341234";
    int contador = 0;
    HashMap<HealthCardID, List<Dispensing>> BDD_Dispensings = new HashMap<>();
    HashMap<ProductID, ProductSpecification> BDD_Product = new HashMap<>();


    public GetIncorrectSNS(String code) {
        this.code = code;
        addMockBDD(Date.from(Instant.now().minusSeconds(10L)), Date.from(Instant.now().plusSeconds(10L)));
    }

    private void addMockBDD(Date init, Date fin) {
        try {
            ArrayList<Dispensing> disps = new ArrayList<>();
            DispensableMedicines lines = new DispensableMedicines();
            ProductID prodID1 = new ProductID("123456789123");
            ProductID prodID2 = new ProductID("123456789987");
            ProductID prodID3 = new ProductID("123456789000");
            ProductSpecification prodSpec1 = new ProductSpecification(prodID1, "DESC", BigDecimal.TEN);
            ProductSpecification prodSpec2 = new ProductSpecification(prodID2, "DESC", BigDecimal.TEN);
            ProductSpecification prodSpec3 = new ProductSpecification(prodID3, "DESC", BigDecimal.TEN);
            BDD_Product.put(prodID1, prodSpec1);
            BDD_Product.put(prodID2, prodSpec2);
            BDD_Product.put(prodID3, prodSpec3);
            Dispensing eP = new Dispensing(init, fin, lines);
            MedicineDispensingLine line1 = new MedicineDispensingLine(eP, prodSpec1);
            lines.put(prodID1, line1);
            eP.setMedicineDispensingLines(lines);
            disps.add(eP);
            BDD_Dispensings.put(new HealthCardID(correctCode), disps);
            BDD_Dispensings.put(new HealthCardID(emptyCode), new ArrayList<>());
        } catch (PatientIDException | ProductIDException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Dispensing getePrescription(HealthCardID hcID) throws HealthCardException, NotValidePrescriptionException, ConnectException {
        try {
            if (hcID.equals(new HealthCardID("ALCE123456789012")))
                throw new HealthCardException();
            else if (hcID.equals(new HealthCardID("IBMI123456789012"))) {
                if (contador == 0) {
                    contador++;
                    throw new ConnectException();
                }
                throw new NotValidePrescriptionException("No existeix dispensació pròxima per aquest usuari.");
            } else if (hcID.equals(new HealthCardID(correctCode)) || hcID.equals(new HealthCardID(emptyCode))) {
                List<Dispensing> disps = BDD_Dispensings.get(hcID);
                if (disps.size() == 0)
                    throw new NotValidePrescriptionException("No existeix dispensació pròxima per aquest usuari.");
                return disps.get(0);
            }
        } catch(PatientIDException e){
                e.printStackTrace();
        }
        return null;
    }

    @Override
    public PatientContr getPatientContr(HealthCardID hcID) throws ConnectException {
        throw new ConnectException();
    }

    @Override
    public ProductSpecification getProductSpecific(ProductID pID) throws ProductNotFoundException, ConnectException {
        if(!BDD_Product.containsKey(pID))
            throw new ProductNotFoundException();
        if(pID.getProductCode().equals("123456789123")){
            return BDD_Product.get(pID);
        }
        throw new ConnectException();
    }

    @Override
    public List<Dispensing> updateePrescription(HealthCardID hcID, Dispensing disp) throws ConnectException {
        throw new ConnectException();
    }

    public String getCode() {
        return code;
    }
}
