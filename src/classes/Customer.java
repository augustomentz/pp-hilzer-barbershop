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

    public void run() {
        System.out.println(Thread.currentThread().getName() + " entering at barbershop");

        while (!out) {
            if (!couch.checkIsFull() && !waitingRoom.checkIfCustomerPresentInTheList(this.getName()) && !couch.checkIfCustomerPresentInTheList(this.getName())) {
                couch.addToList(this);
            } else if (!waitingRoom.checkIsFull() && !waitingRoom.checkIfCustomerPresentInTheList(this.getName()) && !couch.checkIfCustomerPresentInTheList(this.getName())) {
                waitingRoom.addToList(this);
            } else if (waitingRoom.checkIsFull() && !waitingRoom.checkIfCustomerPresentInTheList(this.getName()) && !couch.checkIfCustomerPresentInTheList(this.getName())) {
                out = true;
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() + " is leaving");
    }
}