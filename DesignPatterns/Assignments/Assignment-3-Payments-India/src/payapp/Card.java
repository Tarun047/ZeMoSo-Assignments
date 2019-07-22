package payapp;

abstract public class Card extends Payments {
    private String cardNumber;
    private int cvv2;
    private int OTP;

    Card(Account newAccount)
    {
        super(newAccount);
        type = PaymentType.ONLINE;

    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setcPIN(int OTP) {
        this.OTP = OTP;
    }

    public void setCvv2(int cvv2) {
        this.cvv2 = cvv2;
    }


    @Override
    boolean validate() {
        //Some dummy card validation logic
        //System.out.println(cardNumber.substring(cardNumber.length()-3)+" "+(cvv2>>1));
        return Integer.parseInt(cardNumber.substring(cardNumber.length()-3))<<1==(cvv2>>1);

    }



    @Override
    void approveTransaction() {
        if(OTP==1234)
            makeTransaction();
    }

    @Override
    void showPaymentDetails() {
        System.out.format("Transaction Details:\nCard Number: %s\n",cardNumber);
        System.out.format("Amount charged: %.2f\n",(isApproved?amount+tax:0));

    }

    abstract void makeTransaction();
    abstract boolean areFundsAvailable();


}
