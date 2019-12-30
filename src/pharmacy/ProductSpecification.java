package pharmacy;

import data.ProductID;

import java.math.BigDecimal;

public class ProductSpecification {
    private ProductID prodID;
    private String description;
    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }
}
