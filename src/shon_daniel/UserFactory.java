package shon_daniel;

public class UserFactory {

    public static User createUser(UserEnum userEnum){

        String userName = UserInput.chooseName();
        String userPassword = UserInput.choosePassword();

        UserAccount userAccount = new UserAccount(userName, userPassword);

        switch (userEnum){

            case BUYER -> {
                String country, city, streetName;
                int buildingNumber;
                country = UserInput.chooseCountry();
                city = UserInput.chooseCity();
                streetName = UserInput.chooseStreetName();
                buildingNumber = UserInput.chooseBuildingNumber();

                return new Buyer(userAccount, new UserAddress(country, city, streetName, buildingNumber));

            }
            case SELLER -> {
                return new Seller(userAccount);
            }
            default -> throw new IllegalArgumentException("Type not supported!");

        }

    }

    public static User createUserDemo(UserEnum userEnum, String userName, String userPassword){

        UserAccount userAccount = new UserAccount(userName, userPassword);

        switch (userEnum){

            case SELLER -> {
                return new Seller(userAccount);
            }
            default -> throw new IllegalArgumentException("Type not supported!");

        }

    }


}
