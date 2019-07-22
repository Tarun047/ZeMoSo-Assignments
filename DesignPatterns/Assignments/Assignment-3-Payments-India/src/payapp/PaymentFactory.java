package payapp;

public class PaymentFactory {


    public static Payments getCardPayment(PaymentType type,Account myAccount,String cardNumber,int cvv2,int OTP)
    {
        Card myPaymentMethod = null;
        if(type==PaymentType.CREDITCARD)
            myPaymentMethod = new CreditCard(myAccount);
        else if(type == PaymentType.DEBITCARD)
        myPaymentMethod = new DebitCard(myAccount);
        myPaymentMethod.setCardNumber(cardNumber);
        myPaymentMethod.setCvv2(cvv2);
        myPaymentMethod.setcPIN(OTP);
        return myPaymentMethod;
    }

    public static Payments getWallet(double openingBalance,String walletProvider)
    {

        Wallets myWallet = new Wallets();
        myWallet.setName(walletProvider);
        myWallet.setBalanceCash(openingBalance);
        return myWallet;
    }

    public static Payments netBankingTransaction(Account myAccount,String username,String password)
    {
        NetBanking nbSession = new NetBanking();
        nbSession.setLogin(myAccount,username,password);
        return nbSession;
    }

    public static Payments codOrder(double amount, String OTP)
    {

        COD myCod = new COD(OTP);
        myCod.setTransactionAmount(amount);
        return myCod;
    }

}
