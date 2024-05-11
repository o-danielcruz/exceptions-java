package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private Integer roomNumber;
    private Date checkin;
    private Date checkout;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation(Integer roomNumber, Date checkin, Date checkout) {
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

    public String updateDates (Date checkin, Date checkout){

        Date now = new Date();
        if (checkin.before(now) || checkout.before(now)){
            return "Error in reservation: model.entities.Reservation dates for update must be future dates";
        }
        if (!checkout.after(checkin)) {
            return "Error in reservation: Check-out date must be after check-in date";
        }

        this.checkin = checkin;
        this.checkout = checkout;
        return null;
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