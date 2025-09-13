/*

    Names:
    Alon Twig - 208298927
    Daniel Katzman - 211545587
 */

package shon_daniel;


/*
    In all classes, just necessary getters and setters appears.
 */


import shon_daniel.data_base.DBapi;

public class Main {


    public static void main(String[] args) {


        MarketFacade marketFacade = MarketFacade.getInstance();
        DBapi dbAPI = new DBapi();

        int userChoice;
        boolean toContinue = true;

        // ---------------------------- load -----------------------------
        marketFacade.loadFromDB(dbAPI);

        while(toContinue) {

            GUI.showMenu();

            userChoice = UserInput.enterInt();

            Command cmd = null;

            switch (userChoice) {
                case -1:
                    marketFacade.option_minus_1();
                    break;

                case 1: //Enter details and get validation after.

                    marketFacade.option1();
                    break;

                case 2: //Enter details and get validation after.

                    marketFacade.option2();
                    break;

                case 3:

                    marketFacade.option3();
                    break;

                case 4:

                    marketFacade.option4();
                    break;

                case 5:
                    marketFacade.option5();
                    break;

                case 6:
                    marketFacade.option6();
                    break;

                case 7:
                    marketFacade.option7();
                    break;

                case 8:
                    marketFacade.option8();
                    break;

                case 9:
                    marketFacade.option9();
                    break;

                case 10:
                    marketFacade.option10(dbAPI);
                    break;


                case 0:
                    System.out.println("Goodbye");
                    UserInput.closeUserInput();
                    toContinue = false;
                    break;

                //----------Part 2 ------------

                case 99:
                    cmd = new Option99(marketFacade);
                    break;

                case 100:
                    cmd = new Option100(marketFacade);
                    break;

                case 101:
                    cmd = new Option101(marketFacade);
                    break;

                case 102:
                    cmd = new Option102(marketFacade);
                    break;

                case 103:
                    cmd = new Option103(marketFacade);
                    break;

                case 104:
                    cmd = new Option104(marketFacade);
                    break;

                case 105:
                    cmd = new Option105(marketFacade);
                    break;

                default:
                    System.out.println("\nInvalid selection. Try again. (0-9) or (99-105)");
                    break;

            }

            if(cmd != null){
                cmd.execute();
            }

            GUI.printLine();

        }

    }



}