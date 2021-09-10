package classes;

public class Customer extends Thread {
    String status = "idle";

    public Customer(String nome) {
        super(nome);
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
    }

    public void run() {
    }
}