import payapp.Account;
import payapp.PaymentFactory;
import payapp.PaymentType;
import payapp.Payments;

public class Solution {
    public static void main(String[] args) {
        Account tarunsAccount = new Account();
        tarunsAccount.setAccountBalance(1e5);
        tarunsAccount.setCreditLimit(1e6);
        tarunsAccount.setMinimumBalance(1e3);
        tarunsAccount.setUsedCredit(96e4);

        Payments dCard = PaymentFactory.getCardPayment(PaymentType.DEBITCARD,tarunsAccount,"123456100",400,1234);
        dCard.setTransactionAmount(1e2);
        dCard.makePayment();

        Payments cCard = PaymentFactory.getCardPayment(PaymentType.CREDITCARD,tarunsAccount,"158745150",600,1234);
        cCard.setTransactionAmount(289);
        cCard.makePayment();

        Payments cCard1 = PaymentFactory.getCardPayment(PaymentType.CREDITCARD,tarunsAccount,"158745120",600,1234);
        cCard1.setTransactionAmount(289);
        cCard1.makePayment();

        Payments paytmWallet = PaymentFactory.getWallet(2000,"PayTM");
        paytmWallet.setTransactionAmount(1000);
        paytmWallet.makePayment();

        paytmWallet.setTransactionAmount(1e5);
        paytmWallet.makePayment();

        Payments netBank = PaymentFactory.netBankingTransaction(tarunsAccount,"gtarun047","1234");
        netBank.setTransactionAmount(1e4);
        netBank.makePayment();

        Payments cashOrder = PaymentFactory.codOrder(1e3,"1234");
        cashOrder.makePayment();
    }
}
