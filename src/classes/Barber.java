package classes;

public class Barber extends Thread {
    Couch couch;

    public Barber(String nome, Couch couch) {
        super(nome);

        this.couch = couch;
        start();
    }

    public void run() {
        while(true) {
            System.out.println(Thread.currentThread().getName() + " is sleeping waiting for a customer...");

            if (this.couch.getList().size() > 0) {
                Customer customer = this.couch.getFirst();
                System.out.println(customer.getName() + "... esta sendo cortado pelo " + Thread.currentThread().getName());
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
