package classes;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CashRegister {
    public void acceptPayment(Barber barber, Customer customer) {
        System.out.println("[" + customer.getName() + "]" + " start payment to " + "[" + barber.getName() + "]");

        try {
            Thread.sleep(1500);

            System.out.println("[" + barber.getName() + "]" + " received payment from " + "[" + customer.getName() + "]");
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}