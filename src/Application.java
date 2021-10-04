import classes.*;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class Application {
    public static void main(String[] args) {
        AtomicInteger customer_id = new AtomicInteger(0);

        Couch couch = new Couch(4);
        CashRegister cashRegister = new CashRegister();

        new Barber("BARBER-1", couch, cashRegister);
        new Barber("BARBER-2", couch, cashRegister);
        new Barber("BARBER-3", couch, cashRegister);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            if (couch.getList().size() < 4) {
                Customer customer = new Customer("CUSTOMER-" + customer_id.getAndIncrement(), couch);

                customer.start();
            } else {
                System.out.println("");
                System.out.println("==================================================");
                System.out.println("Barbershop is full");
                System.out.println("SOFA: " + couch.getList().size() + " " + couch.getList().toString());
                System.out.println("==================================================");
                System.out.println("");
            }
            }
        }, 0, new Random().nextInt(2 - 1) + 1 * 760);
    }
}

