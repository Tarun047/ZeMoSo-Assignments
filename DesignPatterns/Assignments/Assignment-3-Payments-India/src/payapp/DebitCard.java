package payapp;

class DebitCard extends Card{

    DebitCard(Account newAccount) {
        super(newAccount);
    }

    @Override
    boolean areFundsAvailable() {
        return myAccount.getAccountBalance()-(amount+tax)>=myAccount.getMinimumBalance();
    }

    @Override
    void makeTransaction() {
        //Some Connecting to Bank Logic
        myAccount.setAccountBalance(myAccount.getAccountBalance()-(amount+tax));
        isApproved=true;
    }
}
