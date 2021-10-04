package classes;

public class Barber extends Thread {
    Wait list;
    CashRegister cashRegister;
    Customer customer;

    public Barber(String nome, Wait list, CashRegister cashRegister) {
        super(nome);

        this.list = list;
        this.cashRegister = cashRegister;
        start();
    }

    private void callCustomerToCut() {
        synchronized (this.list) {
            if (this.list.getList().isEmpty()) {
                Logger.log("[" + this.getName() + "]" + " are sleeping waiting for clients...");
            } else {
                this.customer = this.callCustomerAndFreeSpaces();
            }
        }

        if (this.customer != null) {
            this.customer.cuttingHair(this);

            synchronized (this.cashRegister) {
                this.cashRegister.acceptPayment(this, customer);
            }

            Logger.log("[" + this.customer.getName() + "]" + " leaves the barbershop and the " + "[" + this.getName() + "]" + " is free to get new clients");
        }
    }

    private Customer callCustomerAndFreeSpaces() {
        this.customer = this.list.getFirst();
        this.list.getList().remove(0);

        for (int i = 0; i < 4; i++) {
            try {
                Customer findCustomer = this.list.getList().get(i);

                if (findCustomer.getStatus().equals(CustomerStatusEnum.STANDING)) {
                    findCustomer.setStatus(CustomerStatusEnum.SEATDOWNED);

                    Logger.log("[" + this.getName() + "]" + " call " + "[" + this.customer.getName() + "]" + " to cut and " + "[" + findCustomer.getName() + "]" + " get your space at couch");
                }
            } catch (Exception e){
            }
        }

        return this.customer;
    }

    public void run() {
        while(true) {
            this.callCustomerToCut();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}
