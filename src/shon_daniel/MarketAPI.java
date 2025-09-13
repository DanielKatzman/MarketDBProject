package shon_daniel;

public interface MarketAPI {

    boolean addSeller();
    boolean addSellerDemo(Seller seller);
    boolean addBuyer();
    boolean addProductToSeller(Seller seller,String productName, double productPrice, int numberOfCategory, double specialPackagingPrice);
    boolean addProductToBuyerShoppingCart(Buyer buyer ,Product product);
    boolean paymentShoppingCartOfBuyer(Buyer buyer) throws PaymentForEmptyShoppingCartException;
    void showAllBuyers();
    void showAllSellers();
    void showAllProductsByCategory(int categoryOrdinal);
    boolean createNewShoppingCartFromHistory(Buyer buyer, int shoppingCartIndexHistory);


}
