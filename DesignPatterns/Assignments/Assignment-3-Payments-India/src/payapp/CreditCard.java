package payapp;

class CreditCard extends Card {

    CreditCard(Account newAccount) {
        super(newAccount);
    }

    @Override
    boolean areFundsAvailable() {
        return amount+tax<(myAccount.getCreditLimit()-myAccount.getUsedCredit());
    }


    @Override
    void makeTransaction() {
        //Some connecting to server logic
        myAccount.setUsedCredit(myAccount.getUsedCredit()+amount+tax);
        //If Success
        isApproved=true;

    }
}
