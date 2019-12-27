package data;

import data.exceptions.FormatErrorException;

final public class HealthCardID {
    private final String personalID;

    public HealthCardID(String code) throws NullPointerException, FormatErrorException {
        if (code == null) {
            throw new NullPointerException("Rebut objecte sense instanciar.");
        }

        if (isValidCIP(code)) {
            this.personalID = code;
        } else {
            throw new FormatErrorException("Error amb el format del CIP.");
        }
    }

    public String getPersonalID() { return personalID; }

    @Override
    public int hashCode() {
        return personalID.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HealthCardID hcardID = (HealthCardID) o;
        return personalID.equals(hcardID.personalID);
    }

    @Override
    public String toString() {
        return "HealthCardID(" + "personal code='" + personalID + '\'' + '}';
    }

    private boolean isValidCIP(String code) {
        char current_char;
        if (code.length() != 16) {
            return false;
        } else {
            for(int i=0; i<16; i++) {
                if (i < 4) {
                    current_char = code.charAt(i);
                    if (!Character.isLetter(current_char)) {
                        return false;
                    }
                } else {
                    current_char = code.charAt(i);
                    if (!Character.isDigit(current_char)) {
                        return false;
                    }
                }
            }
            return true;
        }
    }
}
