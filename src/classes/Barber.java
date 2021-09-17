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
            synchronized (couch) {
//                System.out.println(Thread.currentThread().getName() + " is sleeping waiting for a customer...");

                if (couch.getList().size() > 0) {
                    Customer customer = couch.getFirst();
                    couch.removeFromList();
                    customer.cuttingHair(this);
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
}
