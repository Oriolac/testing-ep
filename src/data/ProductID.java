package data;

import data.exceptions.FormatErrorException;

final public class ProductID {
    private final String productCode;

    public ProductID (String code) throws NullPointerException, FormatErrorException {
        if (code == null) {
            throw new NullPointerException("Rebut objecte sense instanciar.");
        }

        if (isValidUPC(code)) {
            this.productCode = code;
        } else {
            throw new FormatErrorException("Error amb el format del UPC.");
        }
    }

    public String getProductCode() { return productCode; }

    @Override
    public int hashCode() {
        return productCode.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductID prodID = (ProductID) o;
        return productCode.equals(prodID.productCode);
    }

    @Override
    public String toString() {
        return "ProductID(" + "product code='" + productCode + '\'' + '}';
    }

    private boolean isValidUPC (String code) {
        char current_char;
        if (code.length() != 12) {
            return false;
        } else {
            for (int i=0; i<12; i++) {
                current_char = code.charAt(i);
                if (!Character.isDigit(current_char)) {
                    return false;
                }
            }
            return true;
        }
    }
}
