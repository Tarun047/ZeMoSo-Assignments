package payapp;

public class Account
{
    private double creditLimit;
    private  double usedCredit;
    private double accountBalance;
    private double minimumBalance;

    public double getAccountBalance() {
        return accountBalance;
    }


    public double getCreditLimit() {
        return creditLimit;
    }

    public double getUsedCredit() {
        return usedCredit;
    }

    public double getMinimumBalance() {
        return minimumBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public void setUsedCredit(double usedCredit) {
        this.usedCredit = usedCredit;
    }

    public void setMinimumBalance(double minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

}
