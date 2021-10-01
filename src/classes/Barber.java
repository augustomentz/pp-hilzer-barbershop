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
            Customer customer = null;

            synchronized (couch) {
                if (this.couch.getList().size() > 0) {
                    customer = this.couch.getFirst();
                    this.couch.removeFromList();

                } else {
//                    System.out.println("[" + this.getName() + "]" + " is sleeping waiting for clients");
                }
            }

            if (customer != null) {
                customer.cuttingHair(this);

                synchronized (cashRegister) {
                    cashRegister.acceptPayment(this, customer);
                }

                System.out.println("[" + customer.getName() + "]" + " has leaving the barbershop");
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
