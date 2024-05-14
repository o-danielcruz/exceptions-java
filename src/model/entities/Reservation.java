package model.entities;

import model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private Integer roomNumber;
    private Date checkin;
    private Date checkout;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation(Integer roomNumber, Date checkin, Date checkout){
        // nao mais necessario o tratamento com o throw pois a classe Domain agora é extensao de RuntimeException

        if (!checkout.after(checkin)) {
            throw new DomainException ("\nError in reservation: Check-out date must be after check-in date");
        }

        this.roomNumber = roomNumber;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckin() {
        return checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public long duration(){

        long diff = checkout.getTime() - checkin.getTime(); // diferenca em milisegs das duas datas
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS); // converte milissegs em dias
    }

    // o metodos setters para checkin e chekout foram apagados porque o método updates tá fazendo esse papel

    public void updateDates (Date checkin, Date checkout) {

        Date now = new Date();
        if (checkin.before(now) || checkout.before(now)){
            throw new DomainException ("\nError in reservation: reservation dates for update must be future dates");
        }
        if (!checkout.after(checkin)) {
            throw new DomainException ("\nError in reservation: Check-out date must be after check-in date");
        }

        this.checkin = checkin;
        this.checkout = checkout;
    }


    @Override
    public String toString() {
        return "Room "
                + roomNumber
                + ", check-in: "
                + sdf.format(checkin)
                + ", check-out: "
                + sdf.format(checkout)
                + ", "
                + duration()
                + " nights";
    }
}
