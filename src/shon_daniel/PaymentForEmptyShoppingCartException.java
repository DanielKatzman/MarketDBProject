package shon_daniel;

public class PaymentForEmptyShoppingCartException extends Exception{
    public PaymentForEmptyShoppingCartException() {
        super("The shopping cart is empty, can't pay for that");
    }
}
