package shon_daniel;

public class Buyer extends User{

    private UserAddress userAddress;
    private ShoppingCart[] allShoppingCarts;
    private int currentShoppingCartIndex = 0;
    private final int ARR_FACTOR_SIZE = 2;
    private ShoppingCart currentShoppingCart; // the only cart that will be stored

    //--------------------------------- ctor --------------------------------------

    public Buyer(UserAccount userAccount,UserAddress userAddress) {
        super(userAccount);
        this.userAddress = userAddress;
        allShoppingCarts = new ShoppingCart[ARR_FACTOR_SIZE];
        allShoppingCarts[0] = new ShoppingCart(currentShoppingCartIndex);
    }


    //-------------------------------- getters --------------------------------------


    public ShoppingCart getCurrentShoppingCart() {
        return allShoppingCarts[currentShoppingCartIndex];
    }

    public int getCurrentShoppingCartIndex() {
        return currentShoppingCartIndex;
    }

    public UserAddress getUserAddress() {
        return userAddress;
    }


    //-------------------------------- setters --------------------------------------

    public void setAllShoppingCarts(ShoppingCart[] allShoppingCarts) {
        this.allShoppingCarts = allShoppingCarts;
    }

    public void setCurrentShoppingCartIndex(int currentShoppingCartIndex) {
        this.currentShoppingCartIndex = currentShoppingCartIndex;
    }

    //=================================================================================
    //----------------------------- public methods --------------------------------------

    //----------------------------- printUserDetails --------------------------------------

    @Override
    public void printUserDetails() {
        System.out.print(getUserAccount().toString());
        System.out.println(userAddress.toString());

        System.out.println("Current shopping cart: ");
        allShoppingCarts[currentShoppingCartIndex].printShoppingCart();

        System.out.println("\nHistory shopping carts: ");

        if( ! hasShoppingCartsHistory() ){
            System.out.println("History is Empty");
        }

        printAllShoppingCartsHistory();
    }

    //----------------------------- printAllShoppingCarts --------------------------------------

    public void printAllShoppingCartsHistory(){
        for (int i = currentShoppingCartIndex - 1; i >= 0 ; --i){
            allShoppingCarts[i].printShoppingCart();
            System.out.println("-------------------");
        }
    }

    //----------------------------- setCurrentShoppingCart --------------------------------------

    public void setCurrentShoppingCart() {

        // setting the date of purchase
        allShoppingCarts[currentShoppingCartIndex].setPurchaseDate();

        currentShoppingCartIndex++;
        if(isArrayFull()) {
            allShoppingCarts = fixShoppingCartArray();
        }
        allShoppingCarts[currentShoppingCartIndex] = new ShoppingCart(currentShoppingCartIndex);
    }


    //------------------------------- hasShoppingCartsHistory -----------------------------------------

    public boolean hasShoppingCartsHistory(){
        return currentShoppingCartIndex > 0;
    }

    //------------------------------- hasShoppingCartsHistory -----------------------------------------

    public void setCurrentShoppingCartFromHistory(int shoppingCartIndexHistory){

        ShoppingCart currentShoppingCart = getCurrentShoppingCart();
        ShoppingCart historyShoppingCart = allShoppingCarts[shoppingCartIndexHistory];

        currentShoppingCart.resetShoppingCart();

        int lenHistoryShoppingCart = historyShoppingCart.getCurrentProductCounter();

        for( int i = 0 ; i < lenHistoryShoppingCart; ++i ){
            currentShoppingCart.addProductToShoppingCart(historyShoppingCart.getProductByIndex(i));
        }

    }



    //=================================================================================
    //----------------------------- private methods ------------------------------------


    private ShoppingCart[] fixShoppingCartArray(){

        ShoppingCart[] fixedArrayProduct = new ShoppingCart[allShoppingCarts.length * ARR_FACTOR_SIZE];

        for(int i = 0; i < currentShoppingCartIndex; i++){
            fixedArrayProduct[i] = allShoppingCarts[i];
        }

        return fixedArrayProduct;
    }

    private boolean isArrayFull(){
        return allShoppingCarts.length == currentShoppingCartIndex;
    }

    @Override
    public String toString() {
        return getUserAccount().toString();
    }
}
