package classes;

public class Barber extends Thread {
    final Couch couch;
    CashRegister cashRegister;
    Customer customer;

    public Barber(String nome, Couch couch, CashRegister cashRegister) {
        super(nome);

        this.couch = couch;
        this.cashRegister = cashRegister;
        start();
    }

    public void run() {
        while(true) {
            synchronized (this.couch) {
                if (this.couch.getList().isEmpty()) {
                    System.out.println("[" + this.getName() + "]" + " are sleeping waiting for clients...");

                } else {
                    this.customer = this.couch.getFirst();
                    this.couch.getList().removeFirst();
                }
            }

            if (customer != null) {
                this.customer.cuttingHair(this);

                synchronized (this.cashRegister) {
                    this.cashRegister.acceptPayment(this, customer);
                }

                System.out.println("[" + this.customer.getName() + "]" + " leaves the barbershop and the " + "[" + this.getName() + "]" + " is free to get new clients");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
