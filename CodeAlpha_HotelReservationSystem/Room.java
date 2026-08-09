package com.hotel;

public class Room {

    int roomNumber;
    String roomType;
    double pricePerNight;
    boolean available;

    public Room(int roomNumber, String roomType, double pricePerNight) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.available = true;
    }
}