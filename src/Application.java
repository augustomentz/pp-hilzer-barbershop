import classes.*;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class Application {
    public static void main(String[] args) {
        AtomicInteger customer_id = new AtomicInteger(0);

        Barbershop barbershop = new Barbershop(
                new Barber("barber-1"),
                new Barber("barber-2"),
                new Barber("barber-3"),
                new Couch(4),
                new WaitingRoom(13)
        );

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Customer customer = new Customer("Customer-" + customer_id.getAndIncrement());

                customer.start();

                System.out.println(barbershop.isFull());
            }
        }, 0, new Random().nextInt(15 - 10) + 10 * 1000);
    }
}
