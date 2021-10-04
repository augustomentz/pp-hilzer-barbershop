import classes.*;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.atomic.AtomicInteger;

public class Barbershop {
    public static void main(String[] args) {
        AtomicInteger customer_id = new AtomicInteger(0);
        Wait customersList = new Wait(17);

        CashRegister cashRegister = new CashRegister();

        new Barber("BARBER-1", customersList, cashRegister);
        new Barber("BARBER-2", customersList, cashRegister);
        new Barber("BARBER-3", customersList, cashRegister);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!customersList.isFull()) {
                    Customer customer = new Customer("CUSTOMER-" + (customer_id.getAndIncrement() + 1), customersList);

                    customer.start();
                } else {
                    Logger.log("");
                    Logger.log("================================================");
                    Logger.log("Barbershop is full, have no space to more clients!");
                    Logger.log("================================================");
                    Logger.log("");
                }
            }
        }, 0, 500 + new Random().nextInt(1100));
    }
}

