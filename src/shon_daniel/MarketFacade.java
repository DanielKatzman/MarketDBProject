package shon_daniel;

import shon_daniel.data_base.DBapi;

import java.util.Stack;

public class MarketFacade {

    private MarketManager marketManager = MarketManager.getInstance(); // also singleton
    private static MarketFacade instance; // for singleton

    // --------------------------- memento ---------------------------------
    private static Stack<MarketManager.Memento> stack = new Stack<>();
    //----------------------------------------------------------------------


    //============================================ Singleton ============================================
    //Singleton - private static instance, private constructor, and static getInstance()
    private MarketFacade(){}

    public static MarketFacade getInstance(){
        if(instance == null){
            instance = new MarketFacade();
        }
        return instance;
    }


    /////////////////////////////////////  /////////////////////////////////////////


    //------------------------------------ selectShoppingCartOfBuyer  ------------------------------------

    private static int selectShoppingCartOfBuyer(Buyer buyer){

        System.out.println("These are all shopping carts history of " + buyer.getUserAccount().getUserName());
        buyer.printAllShoppingCartsHistory();

        while( true ){

            System.out.println("Choose between 0-" + (buyer.getCurrentShoppingCartIndex()-1) );

            int choose = UserInput.enterInt();

            if( choose >= 0 && choose <= (buyer.getCurrentShoppingCartIndex()-1) ){
                return choose;
            }

            System.out.println("Wrong input!");

        }

    }


    //------------------------------------ enterExistSeller  ------------------------------------

    private String enterExistSeller(){

        System.out.print("Choose Seller - \n");
        marketManager.printAllSellers();
        String sellerName = UserInput.chooseName();

        while ((marketManager.getSeller(sellerName) == null)){
            System.out.print("Seller is not exists! try again: \n");
            marketManager.printAllSellers();
            sellerName = UserInput.chooseName();
        }

        System.out.println("------The seller " + sellerName + " has been chosen successfully");

        return sellerName;

    }

    //------------------------------------ enterExistBuyer  ------------------------------------

    private String enterExistBuyer(){

        System.out.print("Choose Buyer - \n");
        marketManager.printAllBuyers();
        String buyerName = UserInput.chooseName();

        while ((marketManager.getBuyer(buyerName) == null)){
            System.out.print("Buyer is not exists! try again: ");
            marketManager.printAllBuyers();
            buyerName = UserInput.chooseName();
        }

        System.out.println("------The buyer " + buyerName + " has been chosen successfully");

        return buyerName;



    }



    // ======================================= Options 0-9 ======================================================


    // ------------------------------------ option -1 - addSeller ------------------------------------------------
    public void option_minus_1(){
        System.out.println("------------------------------- Demo -------------------------------\n");
        System.out.println("------------------------------- Adding Sellers -------------------------------\n");

        User seller = UserFactory.createUserDemo(UserEnum.SELLER,"Daniel","sdjsd");

        marketManager.addSellerDemo((Seller) seller);
        System.out.println("Adding Daniel with pass sdjsd\n");

        seller = UserFactory.createUserDemo(UserEnum.SELLER, "Dani", "sdknhsjd");
        marketManager.addSellerDemo((Seller) seller);
        System.out.println("Adding Dani with pass sdknhsjd\n");

        seller = UserFactory.createUserDemo(UserEnum.SELLER, "Dan", "sndksjds");
        marketManager.addSellerDemo((Seller) seller);
        System.out.println("Adding Dan with pass sndksjds\n");

        seller = UserFactory.createUserDemo(UserEnum.SELLER, "D", "nsdjgskudbsdj");
        marketManager.addSellerDemo((Seller) seller);
        System.out.println("Adding D with pass nsdjgskudbsdj\n");

        seller = UserFactory.createUserDemo(UserEnum.SELLER, "Alon", "sfsf");
        marketManager.addSellerDemo((Seller) seller);
        System.out.println("Adding Alon with pass sfsf\n");

        seller = UserFactory.createUserDemo(UserEnum.SELLER, "alon", "sfsf");
        marketManager.addSellerDemo((Seller) seller);
        System.out.println("Adding alon with pass sfsf\n");

        seller = UserFactory.createUserDemo(UserEnum.SELLER, "ALON", "sSDSDf");
        marketManager.addSellerDemo((Seller) seller);
        System.out.println("Adding ALON with pass sSDSDf\n");

        seller = UserFactory.createUserDemo(UserEnum.SELLER, "alOn", "sfsdsdsf");
        marketManager.addSellerDemo((Seller) seller);
        System.out.println("Adding alOn with pass sfsdsdsf\n");

        seller = UserFactory.createUserDemo(UserEnum.SELLER, "Lea", "leapass");
        marketManager.addSellerDemo((Seller) seller);
        System.out.println("Adding Lea with pass leapass\n");

        seller = UserFactory.createUserDemo(UserEnum.SELLER, "Sam", "sam123");
        marketManager.addSellerDemo((Seller) seller);
        System.out.println("Adding Sam with pass sam123\n");

        seller = UserFactory.createUserDemo(UserEnum.SELLER, "Isabella", "bella789");
        marketManager.addSellerDemo((Seller) seller);
        System.out.println("Adding Isabella with pass bella789\n");

        seller = UserFactory.createUserDemo(UserEnum.SELLER, "Romi", "shlomo");
        marketManager.addSellerDemo((Seller) seller);
        System.out.println("Adding Romi with pass shlomo\n");

        seller = UserFactory.createUserDemo(UserEnum.SELLER, "al", "pass123");
        marketManager.addSellerDemo((Seller) seller);
        System.out.println("Adding al with pass pass123\n");

        seller = UserFactory.createUserDemo(UserEnum.SELLER, "Daniel", "kjugguj");
        marketManager.addSellerDemo((Seller) seller);
        System.out.println("Adding Daniel with pass kjugguj\n");

        seller = UserFactory.createUserDemo(UserEnum.SELLER, "dAnIeL", "ljhusd");
        marketManager.addSellerDemo((Seller) seller);
        System.out.println("Adding dAnIeL with pass ljhusd\n");

        seller = UserFactory.createUserDemo(UserEnum.SELLER, "Alon", "sdjfdfsd");
        marketManager.addSellerDemo((Seller) seller);
        System.out.println("Adding Alon with pass sdjfdfsd\n");



    }

    // ------------------------------------ option 1 - add seller ------------------------------------------------
    public void option1(){

        System.out.println("------------------------------- SELECTED: Add seller -------------------------------\n");

        while(!marketManager.addSeller()){
            System.out.println("Seller already exist!");
        }
        System.out.println("Seller added successfully!");

    }

    // ------------------------------------ option 2 - add buyer ------------------------------------------------

    public void option2(){

        System.out.println("--------------------------- SELECTED: Add buyer was selected ------------------------\n");

        while(!marketManager.addBuyer()){
            System.out.println("Buyer already exist!");
        }
        System.out.println("Buyer added successfully!");


    }


    // ------------------------------------ option 3 - add product to seller ------------------------------------------------

    public void option3(){

        System.out.println("---------------------------- SELECTED: add Product To Seller -----------------------------\n");
//todo remove this function
        if(marketManager.isSellersEmpty()){
            System.out.println("There are no sellers in the market !");
            return;
        }

        String sellerName = enterExistSeller();
        Seller seller = marketManager.getSeller(sellerName);

        String productName = UserInput.chooseProductName();

        if(seller.getProduct(productName) != null){
            System.out.println("Product already exists!");
            return;
        }

        double productPrice = UserInput.chooseProductPrice();
        int category = UserInput.chooseValidCategoryOrdinal();
        double specialPackagingPrice = UserInput.chooseSpecialPackagingPrice();

        while(!marketManager.addProductToSeller(seller, productName, productPrice, category, specialPackagingPrice)){
            System.out.println("Product not added!");
        }

        System.out.println("Added product to seller successfully");


    }


    // ------------------------------------ option 4 - add product to buyer ------------------------------------------------

    public void option4(){

        System.out.println("--------------------- SELECTED: add Product To Buyer Shopping Cart ------------------------\n");

        if(marketManager.isBuyersEmpty()){
            System.out.println("There are no buyers in the market !");
            return;
        }

        if(marketManager.isSellersEmpty()){
            System.out.println("There are no sellers in the market to buy from them products !");
            return;
        }

        String buyerName = enterExistBuyer();
        Buyer buyer = marketManager.getBuyer(buyerName);

        String sellerName = enterExistSeller();
        Seller seller = marketManager.getSeller(sellerName);

        if(!seller.hasProducts()){
            System.out.println("the seller "+ sellerName +" doesn't have products!");
            return;
        }

        System.out.println("These are the products the seller " + sellerName + " has: ");
        seller.printProducts();

        Product product = seller.getProduct(UserInput.chooseProductName());

        while(product == null){
            System.out.println("product doesn't exist!");
            product = seller.getProduct(UserInput.chooseProductName());
        }

        marketManager.addProductToBuyerShoppingCart(buyer, product);
        System.out.println("Added product to buyer shopping cart successfully");



    }


    // ------------------------------------ option 5 - payment for buyer shopping cart ------------------------------------------------

    public void option5(){

        System.out.println("------------------ SELECTED: payment Shopping Cart Of Buyer -----------------------\n");

        if(marketManager.isBuyersEmpty()){
            System.out.println("There are no buyers in the market !");
            return;
        }

        String buyerName = enterExistBuyer();
        Buyer buyer = marketManager.getBuyer(buyerName);

        try{
            marketManager.paymentShoppingCartOfBuyer(buyer);
        }
        catch (PaymentForEmptyShoppingCartException e){
            System.out.println(e.getMessage());
            return;
        }


        System.out.println("the shopping cart added to the history successfully");
        System.out.println("new shopping cart was added!");

    }


    // ------------------------------------ option 6 - show all buyers ------------------------------------------------

    public void option6(){

        System.out.println("-------------------------- SELECTED: show All Buyers -------------------------------\n");
        marketManager.showAllBuyers();

    }

    // ------------------------------------ option 7 - show all sellers ------------------------------------------------

    public void option7(){


        System.out.println("-------------------------- SELECTED: show All Sellers -------------------------------\n");
        marketManager.showAllSellers();

    }


    // -------------------------------- option 8 - show all products of same category from all sellers --------------------

    public void option8(){

        System.out.println("-------------------------- SELECTED: show All Products By Category ------------------------------\n");
        if(marketManager.isSellersEmpty()){
            System.out.println("There are no sellers in the market! So there are no products at all!");
            return;
        }
        marketManager.showAllProductsByCategory(UserInput.chooseValidCategoryOrdinal());

    }


    // ------------------------------------ option 9 - Create Cart From History ------------------------------------------------

    public void option9(){

        System.out.println("---------------------------- SELECTED: Create Cart From History ---------------------------------\n");

        if(marketManager.isBuyersEmpty()){
            System.out.println("There are no buyers in the market !");
            return;
        }

        Buyer buyer = marketManager.getBuyer(enterExistBuyer());

        if( !buyer.hasShoppingCartsHistory() ){
            System.out.println("Buyer doesn't have history of shopping carts!");
            return;
        }

        if( !buyer.getCurrentShoppingCart().isShoppingCartEmpty() ){

            System.out.println("Warning! Your shopping cart is not empty, are you sure that do you want to change cart?");

            if( !UserInput.enterYesOrNo() ){
                return;
            }
        }

        marketManager.createNewShoppingCartFromHistory(buyer, selectShoppingCartOfBuyer(buyer));

    }
    // ------------------------------------ Part 2 ------------------------------------------------
    private boolean isSellersArrInitialized(){
        if(marketManager.isSellersEmpty()){
            System.out.println("There are no sellers in the market!");
            //System.out.println("PRESS -1 FOR DEMO TO ADD NAMES AUTOMATICALLY");
            return false;
        }
        return true;
    }

    private boolean isBuyerArrInitialized(){
        if(marketManager.isBuyersEmpty()){
            System.out.println("There are no buyers in the market!");
            //System.out.println("PRESS -1 FOR DEMO TO ADD NAMES AUTOMATICALLY");
            return false;
        }
        return true;
    }
    // ------------------------------------ option 99 - Print Sellers not sorted ------------------------------------------------

    public void option99(){
        if(!isSellersArrInitialized()){
            return;
        }
        marketManager.printSellerNameNotSorted();
    }

    // ------------------------------------ option 100 - print sellers without duplicates ------------------------------------------------
    public void option100(){
        if(!isSellersArrInitialized()){
            return;
        }
        marketManager.printSellerNameWithoutDuplicates();
    }

    // ------------------------------------ option 101 - how many times a string shows up in original arr ------------------------------------------------
    public void option101(){
        if(!isSellersArrInitialized()){
            return;
        }

        String input = UserInput.chooseName().toLowerCase();
        marketManager.printRepeatsOfStringInArr(input);
    }

    // ------------------------------------ option 102 - print With Iterators  ------------------------------------------------
    public void option102(){
        if(!isSellersArrInitialized()){
            return;
        }
        marketManager.printWithIterators();

    }

    // ------------------------------------ option 103 - print sorted by length of string ------------------------------------------------
    public void option103(){
        if(!isSellersArrInitialized()){
            return;
        }

        marketManager.printInOrderByStringLength();
    }


    // ============================================================================================

    // ------------------------------------ option 104 - save Array list state (Memento) ------------------------------------------------
    public void option104(){
        if(!isSellersArrInitialized()){
            return;
        }

        if(marketManager.getSellersList() == null){
            System.out.println("You need to press 102 before you choose this option!");
            return;
        }

        stack.push(marketManager.createMemento());
        System.out.println("current sellers list has saved, this is the list to be saved:");
        System.out.println(marketManager.getSellersList());

    }

    // ------------------------------------ option 105 - restore Array list state (Memento) ------------------------------------------------
    public void option105(){
        if(!isSellersArrInitialized()){
            return;
        }

        if (!stack.isEmpty()) {
            marketManager.setMemento(stack.pop());
            System.out.println("current sellers list has restored, this is the restored list:");
            System.out.println(marketManager.getSellersList());
        }
        else{
            System.out.println("You didn't pressed 104 option yet for save, there is nothing to restore");
        }

    }

    // ---------------------------------- database - part ---------------------------------------
    // ----------------------------------- delete buyer ---------------------------------------
    public void option10(DBapi dpAPI){
        String buyer_string;
        Buyer buyer;
        if(isBuyerArrInitialized()){
            buyer_string = enterExistBuyer();
            buyer = marketManager.getBuyer(buyer_string);
            marketManager.deleteBuyer(dpAPI,buyer);
        }

    }

    // ----------------------------------- loading from database --------------------------------
    public void loadFromDB(DBapi dpAPI){
        marketManager.loadSellers(dpAPI);
        marketManager.loadBuyers(dpAPI);
    }

}




