package com.hotel;

public class Reservation {

    String bookingId;
    String guestName;
    int roomNumber;
    String roomType;
    int nights;
    double totalAmount;
    String status;

    public Reservation(String bookingId, String guestName, int roomNumber,
                       String roomType, int nights, double totalAmount) {

        this.bookingId = bookingId;
        this.guestName = guestName;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.nights = nights;
        this.totalAmount = totalAmount;
        this.status = "BOOKED";
    }
}