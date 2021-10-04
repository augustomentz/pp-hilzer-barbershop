import classes.*;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class Application {
    public static void main(String[] args) {
        AtomicInteger customer_id = new AtomicInteger(0);
        Wait list = new Wait(17);

        CashRegister cashRegister = new CashRegister();

        new Barber("BARBER-1", list, cashRegister);
        new Barber("BARBER-2", list, cashRegister);
        new Barber("BARBER-3", list, cashRegister);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            if (!list.isFull()) {
                Customer customer = new Customer("CUSTOMER-" + (customer_id.getAndIncrement() + 1), list);

                customer.start();
            } else {
                Logger.log("");
                Logger.log("==================================================");
                Logger.log("Barbershop is full");
                Logger.log("SOFA: " + list.getList().size() + " " + list.getList().toString());
                Logger.log("==================================================");
                Logger.log("");
            }
            }
        }, 0, new Random().nextInt(2 - 1) + 1 * 1000);
    }
}

