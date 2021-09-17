import classes.*;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class Application {
    public static void main(String[] args) {
        AtomicInteger customer_id = new AtomicInteger(0);

        Couch couch = new Couch(4);
        WaitingRoom waitingRoom = new WaitingRoom(13);

        new Barber("barber-1", couch);
        new Barber("barber-2", couch);
        new Barber("barber-3", couch);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!waitingRoom.checkIsFull()) {
                    new Customer("Customer-" + customer_id.getAndIncrement(), couch, waitingRoom);
                } else {
                    System.out.println("Barbershop is full");
                }
            }
        }, 0, new Random().nextInt(5 - 3) + 3 * 100);
    }
}
