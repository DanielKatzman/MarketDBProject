package shon_daniel;

import shon_daniel.data_base.DBapi;

public class Seller extends User{

    private Product[] products;
    private int productsCounter;
    private final int ARR_FACTOR_SIZE = 2;

    //--------------------------------- ctor --------------------------------------

    public Seller(UserAccount userAccount) {
        super(userAccount);
        products = new Product[ARR_FACTOR_SIZE];
        productsCounter = 0;
    }

    //--------------------------------- getters --------------------------------------

    public int getProductsCounter() {
        return productsCounter;
    }


    //=================================================================================
    //----------------------------- public methods --------------------------------------


    //----------------------------- hasProduct --------------------------------------
    public boolean hasProducts() {
        return productsCounter > 0;
    }

    //----------------------------- printProducts --------------------------------------
    public void printProducts(){
        for(int i = 0; i < productsCounter; i++){
            System.out.print(products[i]);
        }
    }

    //-------------------------- addProductToSeller --------------------------------------
    public boolean addProductToSeller(Product product,Seller seller) {

        if(isArrayFull()){
            products = fixProductArray();
        }

        products[productsCounter] = product;
        productsCounter++;

        DBapi.addProductToSeller(product,seller);

        return true; //true for added successfuly
    }

    //------------------------ addProductSellerLoad
    public boolean addProductToSellerLoad(Product product) {

        if(isArrayFull()){
            products = fixProductArray();
        }

        products[productsCounter] = product;
        productsCounter++;

        return true; //true for added successfuly
    }


    //-------------------------- printSellerDetails --------------------------------------

    @Override
    public void printUserDetails() {
        System.out.println(getUserAccount().toString());
        System.out.println("The products of the seller: ");

        if( productsCounter == 0 ){
            System.out.println("No Products");
        }

        for( int  i = 0; i < productsCounter; ++i ){
            System.out.print(products[i].toString());
        }
    }

    //---------------------------------- getProduct --------------------------------------
    public Product getProduct(String productName){
        for (int i = 0; i < productsCounter; i++) {
            if (products[i].getProductName().equals(productName)) {
                return products[i];
            }
        }
        return null;
    }

    //---------------------------------- printProductByCategory --------------------------------------

    public void printProductByCategory(Category category){
        if (!hasProducts()){
            System.out.println("Seller has no products at all!");
            return;
        }

        boolean productCategoryFound = false;
        for( int i = 0; i < productsCounter; i++){
            if (products[i].getCategory().equals(category)) {
                System.out.print(products[i].toString());
                productCategoryFound = true;
            }
        }

        if (!productCategoryFound){
            System.out.println("Seller does not have any products by that category!");
        }
    }


    //=================================================================================
    //----------------------------- private methods --------------------------------------

    private Product[] fixProductArray(){

        Product[] fixedArrayProduct = new Product[products.length * ARR_FACTOR_SIZE];

        for(int i = 0; i < productsCounter; i++){
            fixedArrayProduct[i] = products[i];
        }

        return fixedArrayProduct;
    }

    private boolean isArrayFull(){
        return products.length == productsCounter;
    }


    @Override
    public String toString() {
        return getUserAccount().toString();
    }
}


