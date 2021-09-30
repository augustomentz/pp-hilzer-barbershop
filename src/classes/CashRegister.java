package classes;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CashRegister {
    private final Lock lock = new ReentrantLock(true);

    public void acceptPayment(Barber barber, Customer customer) {
        try {
            lock.lock();
            System.out.println("Payment done from " + customer.getName() + " to " + barber.getName());
        } finally {
            lock.unlock();
        }
    }
}