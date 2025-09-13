package shon_daniel;


import shon_daniel.data_base.DBapi;

import java.util.*;

public class MarketManager implements MarketAPI {

    // --------------------------------------------------- fields ------------------------------------------

    private Seller[] sellers;
    private int sellersCounter = 0; //how many sellers in array
    private Buyer[] buyers;
    private int buyersCounter = 0; //how many buyers in array
    private final int ARR_FACTOR_SIZE = 2;

    // --------------------------------------------------- Comparators --------------------------------------

    private static final Comparator<Seller> SELLER_COMPARATOR = Comparator.nullsLast((s1, s2) -> s2.getProductsCounter() - s1.getProductsCounter());

    private static final Comparator<Buyer> BUYER_COMPARATOR = Comparator.nullsLast(Comparator.comparing(b -> b.getUserAccount().getUserName()));

    //--------------------------------------------------- part 2 data structures --------------------------------------
    private Map<String,Integer> sellersMap = null;
    private List<String> sellers_array_list = null;
    private Set<Seller> sellers_tree_set = new TreeSet<>((o1,o2)->
    {
        String s1 = o1.getUserAccount().getUserName().toUpperCase();
        String s2 = o2.getUserAccount().getUserName().toUpperCase();
        if(s1.equals(s2)){return 0;}

        int DUPLICATION = 1;
        int x = o1.getUserAccount().getUserName().length() - o2.getUserAccount().getUserName().length();
        return x == 0 ? DUPLICATION : x;
    });

    //------------------------------------------- Observer -----------------------------------------------
    private String msg;
    private Set<Observer> set = new HashSet<>();

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    // addListener
    public void attach(Observer o) {
        set.add(o);
    }

    public void pushMessage() {
        myNotify();
    }

    public void detach(Observer o) {
        set.remove(o);
    }

    public void myNotify() {
        for (Observer o : set)
            o.update(this);
    }

    //============================================ Singleton ============================================

    private static MarketManager instance;

    private MarketManager() {
        sellers = new Seller[2]; //default value
        buyers = new Buyer[2];//default value
    }

    public static MarketManager getInstance(){
        if(instance == null){
            instance = new MarketManager();
        }
        return instance;
    }






    //=================================================================================
    //----------------------------- private methods --------------------------------------

    //-------------------------------------  is Array Full -----------------------------------

    private boolean isArrayFull(int arraySize, int currentSize){
        return arraySize == currentSize;
    }


    //-------------------------------------  fix Seller Array -----------------------------------

    private Seller[] fixSellerArray(){

        Seller[] fixedArraySellers = new Seller[sellers.length * ARR_FACTOR_SIZE];

        for(int i = 0; i < sellersCounter; i++){
            fixedArraySellers[i] = sellers[i];
        }

        return fixedArraySellers;
    }


    //-------------------------------------  fix Buyer Array -----------------------------------

    private Buyer[] fixBuyerArray(){

        Buyer[] fixedArrayBuyers = new Buyer [buyers.length * ARR_FACTOR_SIZE];

        for(int i = 0; i < buyersCounter; i++){
            fixedArrayBuyers[i] = buyers[i];
        }

        return fixedArrayBuyers;
    }

    //-------------------------------------  sort sellers -----------------------------------

    private void sortSellers(){
        Arrays.sort(sellers, SELLER_COMPARATOR);
    }

    //-------------------------------------  sort buyers -----------------------------------


    private void sortBuyers(){
        Arrays.sort(buyers, BUYER_COMPARATOR);
    }


    //------------------------------------------  printUserArr -----------------------------------

    private void printUserArr(User[] users, int usersLength){
        for (int i = 0; i < usersLength; i++) {
            users[i].printUserDetails();
            System.out.println("-----------------------------------------------------------");

        }
    }


    //=================================================================================
    //----------------------------- public methods --------------------------------------


    //-------------------------------------  isBuyersEmpty -----------------------------------


    public boolean isBuyersEmpty(){
        return buyersCounter == 0;
    }

    //-------------------------------------  isSellerEmpty -----------------------------------

    public boolean isSellersEmpty(){
        return sellersCounter == 0;
    }


    //-----------------------------------------  getBuyer -------------------------------------

    public Buyer getBuyer(String name){

        for (int i = 0; i < buyersCounter; i++) {
            if (buyers[i].getUserAccount().getUserName().equals(name)){
                return buyers[i];
            }
        }

        return null;

    }


    //------------------------------------------  getSeller -----------------------------------

    public Seller getSeller(String name){

        for (int i = 0; i < sellersCounter; i++) {
            if (sellers[i].getUserAccount().getUserName().equals(name)){
                return sellers[i];
            }
        }

        return null;

    }





    //**************************************************************************************
    //                       Options to menu (1-7)
    //**************************************************************************************


    //------------------------------------ addSeller - 1 ------------------------------------

    @Override
    public boolean addSeller(){
        /*if (getSeller(sellerName) != null){ //removed for part 2 not used!
            return false;
        }*/

        // if array sellers is full , create a new one (doubled size) and return it with all data
        if (isArrayFull(sellers.length ,sellersCounter)){
            sellers = fixSellerArray();
        }

        User seller = UserFactory.createUser(UserEnum.SELLER);

        sellers[sellersCounter] = (Seller) seller;
        sellersCounter++;


        // ----------------------DATABASE SECTION-------------------------------
        DBapi.addSeller((Seller)seller);
        return true;
    }


    public boolean addSellerDemo(Seller seller){
        /*if (getSeller(sellerName) != null){ //removed for part 2 not used!
            return false;
        }*/

        // if array sellers is full , create a new one (doubled size) and return it with all data
        if (isArrayFull(sellers.length ,sellersCounter)){
            sellers = fixSellerArray();
        }

        sellers[sellersCounter] = seller;
        sellersCounter++;

        return true;
    }

    //------------------------------------ addBuyer - 2 ------------------------------------

    @Override
    public boolean addBuyer() {

//        if (getBuyer(buyerName) != null){ // //removed for part 2 not used!
//            return false;
//        }

        // if array buyers is full , create a new one (doubled size) and return it with all data
        if (isArrayFull(buyers.length ,buyersCounter)){
            buyers = fixBuyerArray();
        }


        User buyer = UserFactory.createUser(UserEnum.BUYER);

        buyers[buyersCounter] = (Buyer) buyer;
        buyersCounter++;


        //---------------- database segment --------------------------
        DBapi.addBuyer((Buyer) buyer);
        return true;
    }

    //------------------------------------ addProductToSeller - 3 ------------------------------------
    @Override
    public boolean addProductToSeller(Seller seller,String productName, double productPrice, int numberOfCategory, double specialPackagingPrice){

        Category category = Category.values()[numberOfCategory];

        return seller.addProductToSeller(new Product(productName,productPrice,category, specialPackagingPrice),seller);

    }

    //------------------------------------ addProductToBuyerShoppingCart - 4 ------------------------------------
    @Override
    public boolean addProductToBuyerShoppingCart(Buyer buyer ,Product product){

        return buyer.getCurrentShoppingCart().addProductToShoppingCart(product);
    }

    //------------------------------------ paymentShoppingCartOfBuyer - 5 ------------------------------------
    @Override
    public boolean paymentShoppingCartOfBuyer(Buyer buyer) throws PaymentForEmptyShoppingCartException {

        ShoppingCart shoppingCart = buyer.getCurrentShoppingCart();


        if( shoppingCart.isShoppingCartEmpty() ){
           throw new PaymentForEmptyShoppingCartException();
        }

        System.out.println( buyer.getUserAccount().getUserName() + " has pay for the cart, and the price was: " + buyer.getCurrentShoppingCart().getSumPrice() + "$");

        buyer.setCurrentShoppingCart(); // update new shopping cart in array

        DBapi.createShoppingCart(shoppingCart,buyer);
        return true;
    }



    //------------------------------------ showAllBuyers - 6 -----------------------------------------

    @Override
    public void showAllBuyers() {

        sortBuyers();

        System.out.println("list of all Buyers: ");
        System.out.println("---------------------");

        printUserArr(buyers, buyersCounter);

        if( buyersCounter == 0 ){
            System.out.println("The market has no buyers");
        }

    }

    //------------------------------------ showAllSellers - 7 ------------------------------------

    @Override
    public void showAllSellers() {

        sortSellers();

        System.out.println("list of all Sellers: ");
        System.out.println("---------------------");

        printUserArr(sellers, sellersCounter);

        if( sellersCounter == 0 ){
            System.out.println("The market has no sellers");
        }

    }

    //------------------------------------ show All Products By Category - 8 ------------------------------------
    @Override
    public void showAllProductsByCategory(int categoryOrdinal){
        for(int i = 0; i < sellersCounter;i++){
            Seller seller = sellers[i];
            System.out.println("The seller is: " + seller.getUserAccount().getUserName());
            seller.printProductByCategory(Category.values()[categoryOrdinal]);
        }
    }


    //------------------------------------ Create new shopping cart from history - 9 ------------------------------------


    @Override
    public boolean createNewShoppingCartFromHistory(Buyer buyer, int shoppingCartIndexHistory){

        buyer.setCurrentShoppingCartFromHistory(shoppingCartIndexHistory);
        return true;
        
    }
    //------------------------------------ PART 2 ----------------------------------------------------
    //------------------------------------ Helper Functions ----------------------------------------------------

    private void createMapAndInit(){
        sellersMap = new LinkedHashMap<>();

        for (int i = 0; i < sellersCounter; i++) {
            String sellerName = sellers[i].getUserAccount().getUserName().toLowerCase();
            sellersMap.put(sellerName, sellersMap.getOrDefault(sellerName, 0) + 1);
        }
    }

    private void createListAndInit(){
        sellers_array_list = new ArrayList<>();
        Iterator<Map.Entry<String,Integer>> mapIterator = sellersMap.entrySet().iterator();
        ListIterator<String> list_iterator = sellers_array_list.listIterator();

        while(mapIterator.hasNext()){
            String key = mapIterator.next().getKey();
            list_iterator.add(key);
            list_iterator.add(key);
        }
    }

    //------------------------------------ showAllSellers - 99 -----------------------------------------
    public void printSellerNameNotSorted(){
        System.out.println("All sellers name is not sorted:");
        for (int i = 0; i < sellersCounter; i++) {
            System.out.println(sellers[i].getUserAccount().getUserName());
        }
    }

    //------------------------------------ showAllSellersWithoutWithMapAndWithValueOfDuplication - 100 -----------------------------------------
    public void printSellerNameWithoutDuplicates(){
        if(sellersMap == null){
            createMapAndInit();
        }


        for(Map.Entry<String,Integer> entry : sellersMap.entrySet()){
            System.out.printf("%-10.10s..............%d%n", entry.getKey(), entry.getValue());
        }
    }
    //------------------------------------ showNumberOfTimesAStringHadAnDuplicate - 101 -----------------------------------------
    public void printRepeatsOfStringInArr(String target){
        if(sellersMap == null){
            createMapAndInit();
        }

        Integer count = sellersMap.get(target);
        System.out.printf("The number of times %s appears in the original array is %d.\n",target,count == null ? 0 : count);
    }

    //------------------------------------ printWithIterators - 102 -----------------------------------------

    public void printWithIterators(){

        createMapAndInit();
        createListAndInit();

        // -------------- for observer, update listeners
        // Action1 and Action2 both have a filed 'id' that they inherit from Action class , those implement equals() and hashCode() ,
        // so in the set of the Subject, will not have duplicates.
        attach(new Action1());
        attach(new Action2());
        // ----------------------------------------------

        System.out.println("Do you want to see the output of my self-implemented iterators (Y/y or any other key to skip):");

        boolean choice = UserInput.enterYesOrNo();

        if(!choice){
            return;
        }

        Iterator<String> concreteIterator = iterator();

        while(concreteIterator.hasNext()){
            System.out.println(concreteIterator.next());
        }

        setMsg("My Iterator ended!");
        pushMessage();

        System.out.println("\nNow print with List Iterator: ");

        TargetAdapter concreteListIteratorAdapter = new ConcreteListIteratorAdapter(listIterator(0));

        while(concreteListIteratorAdapter.myHasNext()){
            System.out.println(concreteListIteratorAdapter.myNext());
        }

        System.out.println("\nNow print with List Iterator (-------- BACKWARDS --------): ");

        while(concreteListIteratorAdapter.myHasPrevious()){
            System.out.println(concreteListIteratorAdapter.myPrevious());
        }

        setMsg("My ListIterator ended!");
        pushMessage();

    }


    //------------------------------------ print in order by length and without duplicates- 103 -----------------------------------------
    public void printInOrderByStringLength(){
        for (int i = 0; i < sellersCounter; i++) {
            sellers_tree_set.add(sellers[i]);
        }

        Iterator<Seller> treeSetIterator = sellers_tree_set.iterator();

        while(treeSetIterator.hasNext()){
            System.out.println(treeSetIterator.next().getUserAccount().getUserName().toUpperCase());
        }
    }



    //-----------------------------------------------------------------------------------------------------------------------------------
    //                                        My Iterators
    //-----------------------------------------------------------------------------------------------------------------------------------


    public Iterator<String> iterator() {
        return new ConcreteIterator();
    }
    public ListIterator<String>	listIterator() {
        return new ConcreteListIterator(0);
    }
    public ListIterator<String>	listIterator(int index) {
        return new ConcreteListIterator(index);
    }

    private class ConcreteIterator implements Iterator<String>{

        int curr = 0;

        @Override
        public boolean hasNext() {
            return curr < sellers_array_list.size();
        }

        @Override
        public String next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }
            String val = sellers_array_list.get(curr);
            ++curr;
            return val;
        }

    }

    private class ConcreteListIterator extends ConcreteIterator  implements ListIterator<String>{

        private ConcreteListIterator(int index){
            curr = index;
        }

        @Override
        public boolean hasPrevious() {
            return curr > 0;
        }

        @Override
        public String previous() {

            if( !hasPrevious() ){
                throw new NoSuchElementException();
            }

            --curr;
            return sellers_array_list.get(curr);

        }

        //-------------------------------Not Used

        @Override
        public int nextIndex() {
//            return 0;
            throw new UnsupportedOperationException();

        }

        @Override
        public int previousIndex() {
//            return 0;
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void set(String s) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void add(String s) {
            throw new UnsupportedOperationException();
        }
    }


    /////////////////////////////////////////////
    //    Memento
    ////////////////////////////////////////////

    public static class Memento {
        private final List<String> sellers_lst;

        private Memento(List<String> sellers_lst) {
            this.sellers_lst = sellers_lst;
        }
    }

    public Memento createMemento() {
        return new Memento(sellers_array_list);
    }

    public void setMemento(Memento m) {
        this.sellers_array_list = m.sellers_lst;
    }

    public List<String> getSellersList(){
        return sellers_array_list;
    }


    // ----------------------- loading DB----------------------------

    public void loadSellers(DBapi dBapi){
        sellers = dBapi.getSellerFromDB().toArray(new Seller[0]);
        if(sellers.length == 0){
            sellers = new Seller[2];
            sellersCounter = 0;
        }else{
            sellersCounter = sellers.length;
        }

    }

    public void loadBuyers(DBapi dBapi){
        buyers = dBapi.getBuyerFromDB().toArray(new Buyer[0]);
        if(buyers.length == 0){
            buyers = new Buyer[2];
            buyersCounter = 0;
        }else{
            buyersCounter = buyers.length;
        }
    }


    public void printAllSellers(){
        for (int i = 0; i < sellersCounter; i++) {
            System.out.println(sellers[i].toString());
        }
    }

    public void printAllBuyers(){
        for (int i = 0; i < buyersCounter; i++) {
            System.out.println(buyers[i].toString());
        }
    }

    // ----------------------- delete buyer----------------------------
    public void deleteBuyer(DBapi dBapi,Buyer buyer){
        dBapi.deleteBuyer(buyer);
        ArrayList<Buyer> arr = new ArrayList<>(Arrays.asList(buyers));
        arr.remove(buyer);
        buyers = arr.toArray(new Buyer[0]);
        buyersCounter -= 1;
    }
}


