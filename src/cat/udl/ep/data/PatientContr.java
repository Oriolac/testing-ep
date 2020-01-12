package cat.udl.ep.data;


import cat.udl.ep.data.exceptions.FormatErrorException;

import java.math.BigDecimal;

final public class PatientContr {
    private final BigDecimal patCont;

    public PatientContr(BigDecimal contribution) throws NullPointerException, FormatErrorException {
        if (contribution==null) {
            throw new NullPointerException("Rebut objecte sense instanciar.");
        }

        if (isValidContr(contribution)) {
            this.patCont = contribution;
        } else {
            throw new FormatErrorException("Error amb el format de l'aportació del pacient.");
        }
    }

    public BigDecimal getPatCont() { return patCont; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatientContr patientContr = (PatientContr) o;
        return (patCont.compareTo(patientContr.patCont)==0);
    }

    @Override
    public String toString() {
        return "PatientContribution(" + "patCont='" + patCont + '\'' + '}';
    }

    private boolean isValidContr(BigDecimal contribution) {
        return contribution.compareTo(new BigDecimal("0.0")) >= 0 &&
                contribution.compareTo(new BigDecimal("100.0")) <= 0;
    }
}
