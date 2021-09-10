package classes;

public class Barbershop {
    Barber barber_1;
    Barber barber_2;
    Barber barber_3;

    Couch couch;
    WaitingRoom waiting_room;

    public Barbershop(Barber barber_1, Barber barber_2, Barber barber_3, Couch couch, WaitingRoom waiting_room) {
        this.barber_1 = barber_1;
        this.barber_2 = barber_2;
        this.barber_3 = barber_3;
        this.couch = couch;
        this.waiting_room = waiting_room;
    }

    public boolean isFull() {
        return this.couch.getList().size() + this.waiting_room.getList().size() == 20;
    }
}
