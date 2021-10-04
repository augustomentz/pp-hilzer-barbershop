package classes;

public class Barber extends Thread {
    Wait customersList;
    CashRegister cashRegister;
    Customer customer;

    public Barber(String nome, Wait customersList, CashRegister cashRegister) {
        super(nome);

        this.customersList = customersList;
        this.cashRegister = cashRegister;
        start();
    }

    private void callCustomerToCut() {
        synchronized (this.customersList) {
            if (this.customersList.getList().isEmpty()) {
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
        this.customer = this.customersList.getFirst();
        this.customersList.getList().remove(0);

        for (int i = 0; i < 4; i++) {
            try {
                Customer findCustomer = this.customersList.getList().get(i);

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
