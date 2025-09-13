package shon_daniel;

public class UserAccount {

    private String userName;
    private String UserPassword;
    private int userId;
    private static int counter = 0;

    //--------------------------------- ctor --------------------------------------

    public UserAccount(String userName,String UserPassword) {
        this.userName = userName;
        this.UserPassword = UserPassword;
        this.userId = counter++;
    }

    // Load from database because id already exists
    public UserAccount(String userName,String UserPassword,int userId) {
        this.userName = userName;
        this.UserPassword = UserPassword;
        this.userId = userId;
        if(counter < userId){
            counter = ++userId;
        }else{
            this.counter = ++counter;
        }

    }

    //------------------------------ getters --------------------------------------

    public String getUserName() {
        return userName;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public int getUserId() {
        return userId;
    }
    //------------------------------ toString --------------------------------------

    @Override
    public String toString() {
        return "UserAccount{" +
                "userName='" + userName + '\'' +
                "}";
    }

}
