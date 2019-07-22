package payapp;

import java.util.HashSet;
import java.util.Set;

public class Wallets extends Payments{
    String name;
    double balanceCash;
    final Set<String> acceptedWallets = new HashSet<>(){
        {
            add("paytm");
            add("phonepe");
            add("freecharge");
            add("googlepay");
            add("amazonpay");
        }
    };

    Wallets()
    {
        super();
        this.type = PaymentType.ONLINE;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBalanceCash(double balanceCash)
    {
        this.balanceCash = balanceCash;
    }


    @Override
    boolean validate() {
        return acceptedWallets.contains(name.toLowerCase());
    }

    @Override
    boolean areFundsAvailable() {
        return amount+tax<balanceCash;
    }

    @Override
    void approveTransaction() {
        //Connect to wallet provider
        balanceCash-=(amount+tax);
        isApproved=true;
    }

    @Override
    void showPaymentDetails() {
        System.out.println(String.format("Wallet Transaction Details are:\nWallet Name: %s\nAmountCharged: %f",name,amount));
    }
}
