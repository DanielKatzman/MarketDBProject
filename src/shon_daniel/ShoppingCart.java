package shon_daniel;

import java.time.LocalDate;

public class ShoppingCart {

    private int idShoppingCart;
    private Product[] currentProducts;
    private int currentProductCounter;
    private double sumPrice;
    private LocalDate purchaseDate;
    private final int ARR_FACTOR_SIZE = 2;


    //--------------------------------- ctor --------------------------------------

    public ShoppingCart(int idShoppingCart) {
        currentProducts = new Product[ARR_FACTOR_SIZE];
        this.idShoppingCart = idShoppingCart;
    }

    public ShoppingCart(int idShoppingCart,LocalDate purchaseDate) {
        currentProducts = new Product[ARR_FACTOR_SIZE];
        this.idShoppingCart = idShoppingCart;
        this.purchaseDate = purchaseDate;
    }

    //------------------------------- getters --------------------------------------

    public double getSumPrice() {
        return sumPrice;
    }

    public int getCurrentProductCounter() {
        return currentProductCounter;
    }

    public Product getProductByIndex(int index){
        return currentProducts[index];
    }

    public int getIdShoppingCart() {
        return idShoppingCart;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public Product[] getCurrentProducts() {
        return currentProducts;
    }

    //------------------------------- setters --------------------------------------

    public void setPurchaseDate() {
        purchaseDate = LocalDate.now();
    }

    public void setDate(LocalDate date) {
        purchaseDate = date;
    }

    public void setIdShoppingCart(int idShoppingCart) {
        this.idShoppingCart = idShoppingCart;
    }

    //================================================================================
    //---------------------------- public methods ------------------------------------

    //-------------------------- resetShoppingCart ------------------------------------

    public void resetShoppingCart(){
        currentProductCounter = 0;
        sumPrice = 0;
    }


    //-------------------------- addProductToShoppingCart-----------------------------

    public boolean addProductToShoppingCart(Product product) {

        if(isArrayFull()){
            currentProducts = fixProductArray();
        }

        currentProducts[currentProductCounter] = product;
        currentProductCounter++;

        sumPrice += product.getTotalPrice(); //update current sum of cart

        return true; //true for added successfully
    }

    //-------------------------------- printShoppingCart---------------------------------


    public void printShoppingCart(){

        System.out.println("Shopping cart: ");
        System.out.println("Serial Number: " + idShoppingCart);

        if( currentProductCounter == 0 ){
            System.out.println("No Products");
        }

        for (int i = 0; i < currentProductCounter; ++i ){
            System.out.print(currentProducts[i].toString());
        }

        System.out.println("Total price is: " + sumPrice + "$");
        System.out.println("Purchase date: " + ( (purchaseDate == null) ? "Not yet bought" : purchaseDate.toString() ));

    }



    public boolean isShoppingCartEmpty(){
        return currentProductCounter == 0;
    }


    //---------------------------- private methods ------------------------------------


    private Product[] fixProductArray(){

        Product[] fixedArrayProduct = new Product[currentProducts.length * ARR_FACTOR_SIZE];

        for(int i = 0; i < currentProductCounter; i++){
            fixedArrayProduct[i] = currentProducts[i];
        }

        return fixedArrayProduct;
    }

    private boolean isArrayFull(){
        return currentProducts.length == currentProductCounter;
    }


}


