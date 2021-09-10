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

    public void addToList(Customer customer) {
        this.customerList.add(customer);
    }

    public boolean checkIsFull() {
        return this.customerList.size() == this.getMaxSize() ? true : false;
    }

    public void removeFromList() {
        this.customerList.remove(0);
    }

    public Integer getMaxSize() {
        return this.maxSize;
    }
}
