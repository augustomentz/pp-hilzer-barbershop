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

        new Barber("Barbeiro #1", customersList, cashRegister);
        new Barber("Barbeiro #2", customersList, cashRegister);
        new Barber("Barbeiro #3", customersList, cashRegister);

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            if (!customersList.isFull()) {
                Customer customer = new Customer("Cliente #" + (customer_id.getAndIncrement() + 1), customersList);

                customer.start();
            } else {
                Logger.log("");
                Logger.log("================================================");
                Logger.log("A barbearia está cheia, não há espaço para mais clientes!");
                Logger.log("================================================");
                Logger.log("");
            }
            }
        }, 0, 500 + new Random().nextInt(1100));
    }
}

