package classes;

import java.util.LinkedList;

public class Wait {
    Integer maxSize;
    LinkedList<Customer> customerList;

    public Wait(Integer size) {
        this.maxSize = size;
        this.customerList = new LinkedList<Customer>();
    }

    public LinkedList<Customer> getList() {
        return this.customerList;
    }

    public Customer getFirst() {
        return this.customerList
                .stream()
                .findFirst()
                .orElse(null);
    }

    public void addToList(Customer customer) {
        this.customerList.addLast(customer);
    }
}
