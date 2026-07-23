package model.entities;

import model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {

    private Integer roomnumber;
    private Date checkin;
    private Date checkout;

    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation(){
    }

    public Reservation(Integer roomnumber, Date checkin, Date checkout) {
        if (!checkout.after(checkin)){
            throw  new DomainException("Error in reservation: Update dates must be future dates.");
        }
        this.roomnumber=roomnumber;
        this.checkin=checkin;
        this.checkout=checkout;
    }

    public Integer getRoomnumber() {
        return roomnumber;
    }

    public Date getCheckin() {
        return checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public void setRoomnumber(Integer roomnumber) {
        this.roomnumber = roomnumber;
    }

    public long duration(){
        long diff = checkout.getTime() - checkin.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void updateDates(Date checkin, Date checkout) {


        Date now = new Date();
        if(checkin.before(now) || checkout.before(now)){
            throw new DomainException("Error in reservation: Reservations for updates must be futures dates. ");
        }
        if (!checkout.after(checkin)){
            throw  new DomainException("Error in reservation: Update dates must be future dates.");
        }
        this.checkin=checkin;
        this.checkout=checkout;

    }

    @Override
    public String toString() {
        return "Room "
                + roomnumber
                + ", check-in: "
                + sdf.format(checkin)
                + ", check-out: "
                + sdf.format(checkout)
                + ", "
                + duration()
                + " nights";
    }
}
