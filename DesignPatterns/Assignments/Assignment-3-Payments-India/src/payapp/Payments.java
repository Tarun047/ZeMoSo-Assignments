package payapp;

abstract public class Payments {
    PaymentType type;
    boolean isApproved;
    double amount;
    double tax;
    Account myAccount;

   Payments()
   {

   }

    Payments(Account newAccount)
    {
        myAccount=newAccount;
    }

   abstract boolean validate();
   abstract boolean areFundsAvailable();
   abstract void approveTransaction();
   abstract void showPaymentDetails();

    public void setTransactionAmount(double amount) {
        this.amount = amount;
        this.tax = amount/20;
    }


    public void makePayment()
   {
       boolean validated = validate(),funds = areFundsAvailable();
       System.out.println("Validating payment method and checking for funds ... ");
       if(validated && funds)
       {
           Thread mThread = new Thread(this::approveTransaction);
           try {
               mThread.start();
               System.out.println("New Transaction Initiated\nWaiting for Approval ...");
               mThread.join();
               System.out.println("Transaction "+(isApproved?"Success":"Failure"));
               if (isApproved)
                   showPaymentDetails();

           }
           catch (InterruptedException e){
               e.printStackTrace();
           }
       }
       else if(!validated)
       {
           System.out.println("Invalid Payment choice, The payment option is incorrect!");
       }

       else
       {
           System.out.println("Not enough funds are available on account!");
       }
       System.out.println();
   }

}
