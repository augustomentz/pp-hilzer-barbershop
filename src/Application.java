import classes.Barber;
import classes.Customer;

public class Application {
    public static void main(String args[]) {
        Customer customer = new Customer();
        customer.setId("1");

        Barber barber = new Barber();
        barber.setId("1");

        System.out.println("Barber >>" + barber.getId());
        System.out.println("Customer >>" + customer.getId());
    }
}
