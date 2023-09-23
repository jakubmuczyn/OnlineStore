import java.util.Date;

public class Discount {
    private String discountCode;
    private double discountPercentage;
    private Date expirationDate;

    public Discount(String discountCode, double discountPercentage, Date expirationDate) {
        this.discountCode = discountCode;
        this.discountPercentage = discountPercentage;
        this.expirationDate = expirationDate;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

//     TODO - Metody:
//      Sprawdzenie ważności rabatu
//      Dodanie kodu
//      Usunięcie kodu
}
