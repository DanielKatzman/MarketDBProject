package shon_daniel;

public class Product {

    private String productName;
    private double productPrice;
    private Category category;
    private int serialNumber;
    private static int counter = 0;
    private double specialPackagingPrice = 0;
    private double totalPrice = 0;
    private boolean isInSpecialPackaging;

    //--------------------------------- ctor --------------------------------------

    public Product(String productName, double productPrice, Category category, double specialPackagingPrice) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.serialNumber = ++counter;
        this.category = category;
        this.specialPackagingPrice = specialPackagingPrice;
        totalPrice = productPrice + specialPackagingPrice;
        isInSpecialPackaging = specialPackagingPrice > 0;
    }

    public Product(String productName, double productPrice, int category, double specialPackagingPrice,int serialNumber) {
        if(specialPackagingPrice > 0){this.isInSpecialPackaging = true;}

        this.productName = productName;
        this.productPrice = productPrice;
        this.category = Category.values()[category];
        this.specialPackagingPrice = specialPackagingPrice;
        totalPrice = productPrice + specialPackagingPrice;
        this.serialNumber = serialNumber;
        counter = ++counter;
    }

    //--------------------------------- getters --------------------------------------

    public String getProductName() {
        return productName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public Category getCategory() {return category;}

    public double getProductPrice() {
        return productPrice;
    }

    public double getSpecialPackagingPrice() {
        return specialPackagingPrice;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    //--------------------------------- toString --------------------------------------

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", productPrice=" + productPrice +
                ", category=" + category +
                ", serialNumber=" + serialNumber +
                ", specialPackagingPrice=" + specialPackagingPrice + '$'+
                ", totalPrice=" + totalPrice + '$'+
                ", isInSpecialPackaging=" + isInSpecialPackaging +
                "}\n";
    }
}

