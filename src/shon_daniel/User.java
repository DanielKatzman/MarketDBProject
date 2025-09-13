package shon_daniel;

public abstract class User {
    private UserAccount userAccount;

    public User(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void printUserDetails(){
        System.out.println(userAccount.toString());
    }
}
