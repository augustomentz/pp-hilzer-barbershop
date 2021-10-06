package classes;

import java.util.Random;

public class Customer extends Thread {
    private final Wait customersList;
    private CustomerStatusEnum status = CustomerStatusEnum.STANDING;

    public CustomerStatusEnum getStatus() {
        return status;
    }

    public void setStatus(CustomerStatusEnum status) {
        this.status = status;
    }

    public Customer(String nome, Wait customersList) {
        super(nome);

        this.customersList = customersList;
    }

    void cuttingHair(Barber barber) {
        Logger.log("[" + barber.getName() + "]" + " está cortando o cabelo do " + "[" + this.getName() + "]");
        this.setStatus(CustomerStatusEnum.CUTTING);

        try {
            Thread.sleep(1000 + new Random().nextInt(2000));

            Logger.log("[" + barber.getName() + "]" + " cortou o cabelo do " + "[" + this.getName() + "]");
            this.setStatus(CustomerStatusEnum.FINALIZED);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    void searchForSpaceToSeatdown() {
        synchronized (this.customersList) {
            if (this.customersList.getList().size() < 4) {
                this.setStatus(CustomerStatusEnum.SEATDOWNED);

                Logger.log("[" + this.getName() + "]" + " entrou na barbearia e sentou-se no sofá");
            } else {
                this.setStatus(CustomerStatusEnum.STANDING);

                Logger.log("[" + this.getName() + "]" + " entrou na barbearia e está esperando em pé por um espaço no sofá");
            }

            this.customersList.addToList(this);
        }
    }

    public void run() {
        this.searchForSpaceToSeatdown();

        try {
            Thread.sleep(50);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}