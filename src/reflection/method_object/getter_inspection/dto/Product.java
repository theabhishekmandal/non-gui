package reflection.method_object.getter_inspection.dto;

import java.util.Date;

public class Product {
    private double price;
    private String name;
    private long quantity;

    public void setPrice(double price) {
        this.price = price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    private Date expirationDate;

//
//    public Date getExpirationDate() {
//        return expirationDate;
//    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public long getQuantity() {
        return quantity;
    }
}
