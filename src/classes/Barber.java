package classes;

public class Barber extends Thread {
    String status = "sleeping";
    Customer currentCustomer;

    public Barber(String nome) {
        super(nome);
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
    }

    public Customer getCurrentCustomer() {
        return this.currentCustomer;
    }

    public void setCurrentCustomer(Customer customer) {
        this.currentCustomer = customer;
    }
}
