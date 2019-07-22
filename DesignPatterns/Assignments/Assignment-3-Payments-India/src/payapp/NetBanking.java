package payapp;

public class NetBanking extends Payments {
    boolean isLoggedIn;
    String userName;
    String password;
    NetBanking()
    {
        super();
        this.type=PaymentType.ONLINE;
    }


    void setLogin(Account mAccount,String userName,String password)
    {
        myAccount=mAccount;
        this.userName=userName;
        this.password=password;
    }

    @Override
    boolean validate() {
        if(userName.equals("gtarun047") && password.equals("1234"))
            isLoggedIn=true;
        return isLoggedIn;
    }

    @Override
    boolean areFundsAvailable() {
        return isLoggedIn && (myAccount.getAccountBalance()-(amount+tax))>=myAccount.getMinimumBalance();
    }

    @Override
    void approveTransaction() {
        isApproved=true;
        myAccount.setAccountBalance(myAccount.getAccountBalance()-(amount+tax));
    }

    @Override
    void showPaymentDetails() {
        System.out.format("NetBanking Transaction details are:\nTotal Transaction Fee:%.2f\nRemaining Funds:%.2f\n",
                amount+tax,myAccount.getAccountBalance());

    }
}
