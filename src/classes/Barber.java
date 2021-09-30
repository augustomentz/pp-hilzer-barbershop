package classes;

public class Barber extends Thread {
    Couch couch;

    public Barber(String nome, Couch couch) {
        super(nome);

        this.couch = couch;
        start();
    }

    public synchronized void run() {
        while(true) {
            if (this.couch.getList().size() > 0) {
                Customer customer = this.couch.getFirst();
                this.couch.removeFromList();
                customer.cuttingHair(this);

                System.out.println("[" + customer.getName() + "]" + " has leaving the barbershop");
            } else {
                System.out.println("[" + this.getName() + "]" + " is sleeping waiting for clients");
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
