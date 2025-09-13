package shon_daniel;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UserInput {


    private static Scanner scanner = new Scanner(System.in); // close scanner in the end of the program

    ///////////////////////////////////// input segment /////////////////////////////////////////

    public static void closeUserInput(){
        scanner.close();
    }

    public static String enterStr(){

        String s = scanner.nextLine().trim();

        while(s.isEmpty()){
            System.out.print("Empty word! enter again: ");
            s = scanner.nextLine().trim();
        }

        return s;
    }

    public static int enterInt(){

        int val;

        while(true){

            try {

                val = Integer.parseInt(enterStr());
                return val;
            }
            catch (InputMismatchException | NumberFormatException  e){
                System.out.print("It's not a number(int)! Try again: ");
            }


        }


    }

    public static double enterDouble(){

        double val;

        while(true) {

            try {

                val = Double.parseDouble(enterStr());
                return val;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.print("It's not a number(double)! Try again: ");
            }
        }

    }


    public static String chooseName() {
        System.out.print("Enter name: ");
        return enterStr();
    }

    public static String choosePassword() {
        System.out.print("Enter password: ");
        return enterStr();
    }


    public static String chooseCountry() {
        System.out.print("Enter country: ");
        return enterStr();
    }

    public static String chooseCity() {
        System.out.print("Enter City: ");
        return enterStr();
    }

    public static String chooseStreetName() {
        System.out.print("Enter StreetName: ");
        return enterStr();
    }

    public static int chooseBuildingNumber() {
        System.out.print("Enter BuildingNumber: ");
        int buildingNumber = enterInt();

        while(!isPositiveInt(buildingNumber)){
            System.out.println("Invalid Input! need positive number");
            System.out.print("Enter again: ");
            buildingNumber = enterInt();
        }

        return buildingNumber;
    }

    public static String chooseProductName(){
        System.out.print("Enter product name: ");
        return enterStr();
    }

    public static double chooseProductPrice(){

        System.out.print("Enter product price: ");
        double price = enterDouble();

        while(!isPositiveDouble(price)){
            System.out.println("Invalid Input! need positive number");
            System.out.print("Enter again: ");
            price = enterDouble();
        }

        return price;

    }




    public static int chooseValidCategoryOrdinal() {
        GUI.showAllProductCategories();
        System.out.print("Enter category: ");
        int choice = enterInt();

        while (isCategoryOrdinalNotInRange(choice)){
            System.out.println("Invalid choice try again!");
            System.out.print("Enter again: ");
            choice = enterInt();
        }

        return choice;
    }

    //------------------------------------ enterYesOrNo  ------------------------------------

    public static boolean enterYesOrNo(){

        String choose;

        while( true ){

            System.out.println("Enter y/n: ");

            choose = enterStr();

            if( choose.equalsIgnoreCase("y") ){
                return true;
            }
            if (choose.equalsIgnoreCase("n")){
                return false;
            }
            System.out.println("Wrong input!");
        }

    }

    //------------------------------------ chooseSpecialPackagingPrice  ------------------------------------

    public static double chooseSpecialPackagingPrice(){

        System.out.println("Do the product needs special packaging?");

        if( enterYesOrNo() ){
            System.out.println("Enter Special packaging price: ");

            double price = enterDouble();

            while(!isPositiveDouble(price)){
                System.out.println("You chose to do a special packaging, so the price has to be positive!");
                System.out.print("Enter again: ");
                price = enterDouble();

            }

            System.out.println("Special packaging price " + price + "$ was selected to product");
            return price;
        }

        System.out.println("Special packaging price not needed");

        return 0;

    }




    private static boolean isPositiveDouble(double num){
        return num > 0;
    }

    private static boolean isPositiveInt(int num){
        return num > 0;
    }


    //------------------------------------ chooseValidCategoryOrdinal  ------------------------------------

    private static boolean isCategoryOrdinalNotInRange(int choice){
        return (choice < 0 || choice >= Category.values().length);
    }




}
