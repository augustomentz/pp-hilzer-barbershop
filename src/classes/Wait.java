package classes;

import java.util.ArrayList;
import java.util.List;

public class Wait {
    Integer maxSize;
    private ArrayList<Customer> customerList = new ArrayList<>();

    public Wait(Integer size) {
        this.maxSize = size;
    }

    public synchronized List<Customer> getList() {
        return this.customerList;
    }

    public synchronized Customer getFirst() {
        return this.customerList
                .stream()
                .findFirst()
                .orElse(null);
    }

    public synchronized Customer getLast() {
        return this.customerList
                .stream()
                .reduce((first, second) -> second)
                .orElse(null);
    }

    public synchronized void addToList(Customer customer) {
        this.customerList.add(customer);
    }

    public synchronized boolean checkIsFull() {
        return this.customerList.size() == this.getMaxSize() ? true : false;
    }

    public synchronized void removeFromList() {
        this.customerList.remove(0);
    }

    public synchronized Integer getMaxSize() {
        return this.maxSize;
    }

    public synchronized boolean checkIfCustomerPresentInTheList(String name) {
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
