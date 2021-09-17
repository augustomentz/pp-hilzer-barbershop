package classes;

public class Customer extends Thread {
    private Couch couch;
    private WaitingRoom waitingRoom;
    private boolean out = false;

    public Customer(String nome, Couch couch, WaitingRoom waitingRoom) {
        super(nome);

        this.couch = couch;
        this.waitingRoom = waitingRoom;

        start();
    }

    void cuttingHair(Barber barber) {
        System.out.println(barber.getName() + " are cutting the " + this.getName() + " hair.");
    }

    public void run() {
        System.out.println(this.getName() + " entering at barbershop");

        while (!out) {
            Boolean hasPresentAtWaitingRoom = waitingRoom.checkIfCustomerPresentInTheList(this.getName());
            Boolean hasPresentAtCouch = couch.checkIfCustomerPresentInTheList(this.getName());

            if (!couch.checkIsFull() && !hasPresentAtWaitingRoom && !hasPresentAtCouch) {
                couch.addToList(this);
            } else if (!waitingRoom.checkIsFull() && !hasPresentAtWaitingRoom && !hasPresentAtCouch) {
                waitingRoom.addToList(this);
            }

            try {
                this.sleep(3000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
}