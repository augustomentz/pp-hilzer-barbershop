package classes;

import java.util.ArrayList;
import java.util.List;

public class Wait {
    Integer maxSize;
    private ArrayList<Customer> customerList = new ArrayList<>();

    public Wait(Integer size) {
        this.maxSize = size;
    }

    public List<Customer> getList() {
        return this.customerList;
    }

    public Customer getFirst() {
        return this.customerList
                .stream()
                .findFirst()
                .orElse(null);
    }

    public Customer getLast() {
        return this.customerList
                .stream()
                .reduce((first, second) -> second)
                .orElse(null);
    }

    public synchronized void addToList(Customer customer) {
        this.customerList.add(customer);
    }

    public boolean checkIsFull() {
        return this.customerList.size() == this.getMaxSize() ? true : false;
    }

    public void removeFromList() {
        synchronized (this.customerList) {
            this.customerList.remove(0);
        }
    }

    public Integer getMaxSize() {
        return this.maxSize;
    }

    public boolean checkIfCustomerPresentInTheList(String name) {
        return this.getList()
                .stream()
                .anyMatch(customer -> customer.getName().equals(name));
    }

    public synchronized Customer getAndRemoveFromList() {
        Customer customer = this.getFirst();
        this.removeFromList();

        return customer;
    }
}
