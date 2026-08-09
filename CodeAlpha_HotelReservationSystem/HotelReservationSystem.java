package com.hotel;

import java.util.ArrayList;
import java.util.Scanner;

public class HotelReservationSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Reservation> reservations = new ArrayList<>();

        // Available hotel rooms
        rooms.add(new Room(101, "Single", 1500));
        rooms.add(new Room(102, "Single", 1500));
        rooms.add(new Room(201, "Double", 2500));
        rooms.add(new Room(202, "Double", 2500));
        rooms.add(new Room(301, "Deluxe", 4000));

        int bookingCounter = 1;

        while (true) {

            System.out.println("\n==============================================");
            System.out.println("          HOTEL RESERVATION SYSTEM");
            System.out.println("==============================================");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book a Room");
            System.out.println("3. View Active Bookings");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Reservation Summary");
            System.out.println("6. Exit");
            System.out.println("==============================================");
            System.out.print("Enter Your Choice : ");

            int choice = sc.nextInt();

            switch (choice) {

            // =====================================================
            // 1. VIEW AVAILABLE ROOMS
            // =====================================================

            case 1:

                System.out.println("\n==================================================");
                System.out.println("                 AVAILABLE ROOMS");
                System.out.println("==================================================");

                System.out.printf("%-12s %-15s %-15s %-12s%n",
                        "Room No.", "Room Type", "Price/Night", "Status");

                System.out.println("--------------------------------------------------");

                boolean availableRoomFound = false;

                for (Room room : rooms) {

                    if (room.available) {

                        availableRoomFound = true;

                        System.out.printf("%-12d %-15s ₹%-14.2f %-12s%n",
                                room.roomNumber,
                                room.roomType,
                                room.pricePerNight,
                                "AVAILABLE");
                    }
                }

                if (!availableRoomFound) {
                    System.out.println("No rooms are currently available.");
                }

                System.out.println("==================================================");

                break;


            // =====================================================
            // 2. BOOK A ROOM
            // =====================================================

            case 2:

                sc.nextLine();

                System.out.println("\n==============================================");
                System.out.println("                 BOOK A ROOM");
                System.out.println("==============================================");

                System.out.print("Enter Guest Name : ");
                String guestName = sc.nextLine();

                System.out.print("Enter Room Number : ");
                int roomNumber = sc.nextInt();

                Room selectedRoom = null;

                for (Room room : rooms) {

                    if (room.roomNumber == roomNumber) {
                        selectedRoom = room;
                        break;
                    }
                }

                if (selectedRoom == null) {

                    System.out.println("\nRoom not found.");

                } else if (!selectedRoom.available) {

                    System.out.println("\nSorry! This room is already booked.");

                } else {

                    System.out.print("Enter Number of Nights : ");
                    int nights = sc.nextInt();

                    if (nights <= 0) {

                        System.out.println("\nNumber of nights must be greater than 0.");

                    } else {

                        double totalAmount =
                                selectedRoom.pricePerNight * nights;

                        String bookingId =
                                String.format("B%03d", bookingCounter);

                        Reservation reservation = new Reservation(
                                bookingId,
                                guestName,
                                selectedRoom.roomNumber,
                                selectedRoom.roomType,
                                nights,
                                totalAmount
                        );

                        reservations.add(reservation);

                        selectedRoom.available = false;

                        bookingCounter++;

                        System.out.println("\n==============================================");
                        System.out.println("              BOOKING CONFIRMED");
                        System.out.println("==============================================");
                        System.out.println("Booking ID   : " + bookingId);
                        System.out.println("Guest Name   : " + guestName);
                        System.out.println("Room Number  : " + selectedRoom.roomNumber);
                        System.out.println("Room Type    : " + selectedRoom.roomType);
                        System.out.println("Nights       : " + nights);
                        System.out.printf("Total Amount : ₹%.2f%n", totalAmount);
                        System.out.println("Status       : BOOKED");
                        System.out.println("==============================================");
                    }
                }

                break;


            // =====================================================
            // 3. VIEW ACTIVE BOOKINGS
            // =====================================================

            case 3:

                boolean activeBookingFound = false;

                System.out.println("\n==========================================================================");
                System.out.println("                         ACTIVE BOOKINGS");
                System.out.println("==========================================================================");

                System.out.printf("%-12s %-18s %-8s %-12s %-8s %-12s%n",
                        "Booking ID", "Guest Name", "Room", "Type",
                        "Nights", "Amount");

                System.out.println("--------------------------------------------------------------------------");

                for (Reservation r : reservations) {

                    if (r.status.equals("BOOKED")) {

                        activeBookingFound = true;

                        System.out.printf("%-12s %-18s %-8d %-12s %-8d ₹%-11.2f%n",
                                r.bookingId,
                                r.guestName,
                                r.roomNumber,
                                r.roomType,
                                r.nights,
                                r.totalAmount);
                    }
                }

                if (!activeBookingFound) {
                    System.out.println("No active bookings found.");
                }

                System.out.println("==========================================================================");

                break;


            // =====================================================
            // 4. CANCEL BOOKING
            // =====================================================

            case 4:

                sc.nextLine();

                System.out.println("\n==============================================");
                System.out.println("               CANCEL BOOKING");
                System.out.println("==============================================");

                System.out.print("Enter Booking ID : ");
                String cancelId = sc.nextLine();

                Reservation reservationToCancel = null;

                for (Reservation r : reservations) {

                    if (r.bookingId.equalsIgnoreCase(cancelId)
                            && r.status.equals("BOOKED")) {

                        reservationToCancel = r;
                        break;
                    }
                }

                if (reservationToCancel == null) {

                    System.out.println("\nActive booking not found.");

                } else {

                    reservationToCancel.status = "CANCELLED";

                    for (Room room : rooms) {

                        if (room.roomNumber == reservationToCancel.roomNumber) {

                            room.available = true;
                            break;
                        }
                    }

                    System.out.println("\n==============================================");
                    System.out.println("           BOOKING CANCELLED");
                    System.out.println("==============================================");
                    System.out.println("Booking ID  : " + reservationToCancel.bookingId);
                    System.out.println("Guest Name  : " + reservationToCancel.guestName);
                    System.out.println("Room Number : " + reservationToCancel.roomNumber);
                    System.out.println("Status      : CANCELLED");
                    System.out.println("Room is now available.");
                    System.out.println("==============================================");
                }

                break;


            // =====================================================
            // 5. RESERVATION SUMMARY
            // =====================================================

            case 5:

                int totalReservations = reservations.size();
                int activeBookings = 0;
                int cancelledBookings = 0;
                int availableRooms = 0;
                int occupiedRooms = 0;

                double totalRevenue = 0;

                for (Reservation r : reservations) {

                    if (r.status.equals("BOOKED")) {

                        activeBookings++;
                        totalRevenue += r.totalAmount;

                    } else if (r.status.equals("CANCELLED")) {

                        cancelledBookings++;
                    }
                }

                for (Room room : rooms) {

                    if (room.available) {
                        availableRooms++;
                    } else {
                        occupiedRooms++;
                    }
                }

                System.out.println("\n==============================================");
                System.out.println("             RESERVATION SUMMARY");
                System.out.println("==============================================");

                System.out.println("Total Reservations : " + totalReservations);
                System.out.println("Active Bookings    : " + activeBookings);
                System.out.println("Cancelled          : " + cancelledBookings);
                System.out.println("Available Rooms    : " + availableRooms);
                System.out.println("Occupied Rooms     : " + occupiedRooms);
                System.out.printf("Total Revenue      : ₹%.2f%n", totalRevenue);

                System.out.println("==============================================");
                System.out.println("              ACTIVE BOOKINGS");
                System.out.println("==============================================");

                if (activeBookings == 0) {

                    System.out.println("No active bookings.");

                } else {

                    System.out.printf("%-12s %-18s %-8s %-12s %-8s %-12s%n",
                            "Booking ID", "Guest Name", "Room", "Type",
                            "Nights", "Amount");

                    System.out.println("--------------------------------------------------------------------------");

                    for (Reservation r : reservations) {

                        if (r.status.equals("BOOKED")) {

                            System.out.printf("%-12s %-18s %-8d %-12s %-8d ₹%-11.2f%n",
                                    r.bookingId,
                                    r.guestName,
                                    r.roomNumber,
                                    r.roomType,
                                    r.nights,
                                    r.totalAmount);
                        }
                    }
                }

                System.out.println("==============================================");

                break;


            // =====================================================
            // 6. EXIT
            // =====================================================

            case 6:

                System.out.println("\nThank you for using Hotel Reservation System!");
                System.out.println("Goodbye!");
                sc.close();
                return;


            // =====================================================
            // INVALID CHOICE
            // =====================================================

            default:

                System.out.println("\nInvalid Choice! Please enter a number from 1 to 6.");
            }
        }
    }
}