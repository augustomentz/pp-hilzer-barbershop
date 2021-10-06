package classes;

public class CashRegister {
    public void acceptPayment(Barber barber, Customer customer) {
        Logger.log("[" + customer.getName() + "]" + " começou a pagar para o " + "[" + barber.getName() + "]");

        try {
            Thread.sleep(1500);

            Logger.log("[" + barber.getName() + "]" + " recebeu o pagamento do " + "[" + customer.getName() + "]");
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}