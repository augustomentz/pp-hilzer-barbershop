package classes;

public class Customer extends Thread {
    private Couch couch;
    private WaitingRoom waitingRoom;
    private boolean hasCut = false;

    public Customer(String nome, Couch couch, WaitingRoom waitingRoom) {
        super(nome);

        this.couch = couch;
        this.waitingRoom = waitingRoom;
    }

    void cuttingHair(Barber barber) {
        System.out.println("[" + barber.getName() + "]" + " are cutting the " + this.getName());
        Integer time = (int)(Math.random() * 500);
        this.hasCut = true;

        try {
            Thread.sleep(time);

            System.out.println("[" + barber.getName() + "]" + " cut the hair of " + "[" + this.getName() + "]" + " in " + time + " ms");
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    private Boolean checkCouch() {
        return couch.checkIfCustomerPresentInTheList(this.getName());
    }

    private Boolean checkWaitingRoom() {
        return waitingRoom.checkIfCustomerPresentInTheList(this.getName());
    }

    public void run() {
        System.out.println("[" + this.getName() + "]" + " entering at barbershop ");

        while (!hasCut) {
            synchronized (waitingRoom) {
                waitingRoom.addToList(this);
            }

            synchronized (couch) {
                if (!couch.checkIsFull()) {
                    this.couch.addToList(this.waitingRoom.getAndRemoveFromList());
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}