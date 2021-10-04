package classes;

import java.util.Random;

public class Customer extends Thread {
    private final Couch couch;
    private boolean hasCut = false;

    public Customer(String nome, Couch couch) {
        super(nome);

        this.couch = couch;
    }

    void cuttingHair(Barber barber) {
        System.out.println("[" + barber.getName() + "]" + " are cutting the " + this.getName());
        Integer time = 1000 + new Random().nextInt(2000);
        this.hasCut = true;

        try {
            Thread.sleep(time);

            System.out.println("[" + barber.getName() + "]" + " cut the hair of " + "[" + this.getName() + "]" + " in " + time + " ms");
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void run() {
        System.out.println("[" + this.getName() + "]" + " entering at barbershop ");

        synchronized (this.couch) {
            this.couch.addToList(this);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}