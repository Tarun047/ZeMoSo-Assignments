package payapp;

public class COD extends Payments
{
    private String OTP;
    COD(String OTP)
    {
        this.OTP=OTP;
    }

    @Override
    boolean validate() {
        return true;
    }

    @Override
    boolean areFundsAvailable() {
        return true;
    }

    @Override
    void approveTransaction() {
        if(OTP.equals("1234"))
            isApproved=true;
    }

    @Override
    void showPaymentDetails() {
        System.out.format("The Transaction details\nCash Approved for COD is:%2f",(amount+tax));
    }
}
