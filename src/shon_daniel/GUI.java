package shon_daniel;

public class GUI {

    //------------------------------------ show Menu  ------------------------------------

    public static void printLine(){
        System.out.println("------------------------------------------------------------------");
    }

    public static void showMenu() {
        System.out.println("\n\n******************************************************************");
        System.out.println("Welcome! Select an option: ");
        System.out.println("0 - exit");
        System.out.println("1 - Add seller");
        System.out.println("2 - Add buyer");
        System.out.println("3 - Add product to seller");
        System.out.println("4 - Add product to buyer");
        System.out.println("5 - Payment for buyer");
        System.out.println("6 - Show all buyers");
        System.out.println("7 - Show all sellers");
        System.out.println("8 - Show all products by category");
        System.out.println("9 - Create Cart From History");
        System.out.println("10 - Delete buyer");
//        System.out.println("-------------------Part 2 project--------------------");
//        System.out.println("-1 - Demo");
//        System.out.println("99 - Print Seller names not sorted by name");
//        System.out.println("100 - Print Seller names without duplicates");
//        System.out.println("101 - Print times a string has shown in original arr");
//        System.out.println("102 - Print twice with iterators");
//        System.out.println("103 - Print in order by length of string");
//
//        System.out.println("104 - save Array list state (you need to press 102 before, to create array list)");
//        System.out.println("105 - restore Array list state");
//
//        System.out.println("******************************************************************");
        System.out.print("\nEnter your choice: ");
    }

    public static void showAllProductCategories(){
        System.out.println("Category Products:");
        System.out.println("KIDS - " + Category.KIDS.ordinal());
        System.out.println("Electricity - " + Category.ELECTRICITY.ordinal());
        System.out.println("OFFICE - " + Category.OFFICE.ordinal());
        System.out.println("CLOTHING - " + Category.CLOTHING.ordinal());

    }

}
