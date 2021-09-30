package classes;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

public class Customer extends Thread {
    private Couch couch;
    private WaitingRoom waitingRoom;
    private boolean hasCut = false;

    public Customer(String nome, Couch couch, WaitingRoom waitingRoom) {
        super(nome);

        this.couch = couch;
        this.waitingRoom = waitingRoom;
    }

    void cuttingHair(Barber barber) {
        System.out.println("[" + barber.getName() + "]" + " are cutting the " + this.getName());
        Integer time = (int)(Math.random() * 500);
        this.hasCut = true;

        try {
            Thread.sleep(time);

            System.out.println("[" + barber.getName() + "]" + " cut the hair of " + "[" + this.getName() + "]" + " in " + time + " ms");
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public synchronized void pay(Barber barber) {
        System.out.println(this.getName() + " payed the " + barber.getName());
    };

    private synchronized Boolean checkCouch() {
        return couch.checkIfCustomerPresentInTheList(this.getName());
    }

    private synchronized Boolean checkWaitingRoom() {
        return waitingRoom.checkIfCustomerPresentInTheList(this.getName());
    }

    public synchronized void run() {
        System.out.println("[" + this.getName() + "]" + " entering at barbershop");

        while (!hasCut) {
            Boolean hasPresentAtCouch = checkCouch();
            Boolean hasPresentAtWaitingRoom = checkWaitingRoom();

            if (!couch.checkIsFull() && !hasPresentAtWaitingRoom && !hasPresentAtCouch) {
                couch.addToList(this);
            } else if (!waitingRoom.checkIsFull() && !hasPresentAtWaitingRoom && !hasPresentAtCouch) {
                waitingRoom.addToList(this);
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}