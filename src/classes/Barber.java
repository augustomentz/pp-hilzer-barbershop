package classes;

public class Barber extends Thread {
    Couch couch;
    CashRegister cashRegister;

    public Barber(String nome, Couch couch, CashRegister cashRegister) {
        super(nome);

        this.couch = couch;
        this.cashRegister = cashRegister;
        start();
    }

    public void run() {
        while(true) {

            if (this.couch.getList().size() > 0) {
                Customer customer;

                customer = this.couch.getAndRemoveFromList();
                customer.cuttingHair(this);

                synchronized (cashRegister) {
                    cashRegister.acceptPayment(this, customer);
                }

                System.out.println("[" + customer.getName() + "]" + " has leaving the barbershop");
            } else {
                System.out.println("[" + this.getName() + "]" + " is sleeping waiting for clients");
            }


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
