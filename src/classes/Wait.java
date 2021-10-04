package classes;

import java.util.ArrayList;

public class Wait {
    Integer maxSize;
    ArrayList<Customer> customerList;

    public Wait(Integer size) {
        this.maxSize = size;
        this.customerList = new ArrayList<Customer>(size);
    }

    public ArrayList<Customer> getList() {
        return this.customerList;
    }

    public Customer getFirst() {
        return this.customerList
                .stream()
                .findFirst()
                .orElse(null);
    }

    public void addToList(Customer customer) {
        this.customerList.add(customer);
    }

    public boolean isFull() {
        return this.customerList.size() == this.maxSize;
    }
}
