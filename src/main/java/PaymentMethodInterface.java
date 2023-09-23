public interface PaymentMethodInterface {

    boolean validatePayment(Payment payment);
    void pay() throws PaymentNotSuccessful;
    String getPaymentMethodName();
}
