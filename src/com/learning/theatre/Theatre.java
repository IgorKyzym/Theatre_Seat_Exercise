package com.learning.theatre;

import java.util.*;

public class Theatre {

    private final String name;
    public List<Seat> seats;

    static final Comparator<Seat> PRICE_ORDER;

    static {
        PRICE_ORDER = new Comparator<Seat>() {
            @Override
            public int compare(Seat s1, Seat s2) {
                if (s1.getPrice() < s2.getPrice()) {
                    return 1;
                } else if (s1.getPrice() > s2.getPrice()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        };
    }

    public Theatre(String name, int numberOfRows, int seatsPerRow) {
        this.name = name;
        this.seats = new ArrayList<Seat>();

        int lastRow = 'A' + numberOfRows - 1;

        for (char row = 'A'; row <= lastRow; row++) {
            for (int seatNumber = 1; seatNumber <= seatsPerRow; seatNumber++) {
                double price = 12;
                if (row < 'D' && (seatNumber < 10) && (seatNumber > 3)) {
                    price = 14;
                } else if ((row > 'E') || ((seatNumber <= 3) || (seatNumber >= 10))) {
                    price = 7;
                }
                Seat seat = new Seat(row + String.format("%02d", seatNumber), price);
                seats.add(seat);
            }
        }
    }

    public String getName() {
        return name;
    }

    public boolean reserveSeat(String seatNumber) {
        //Seat requestedSeat = new Seat(seatNumber);
        int foundSeat = Collections.binarySearch(seats, new Seat(seatNumber, 0));
        if (foundSeat >= 0) {
            return seats.get(foundSeat).reserve();
        } else {
            System.out.printf("The is no seat %s.%n", seatNumber);
            return false;
        }
    }

    public Collection<Seat> getSeats() {
        return seats;
    }

    public class Seat implements Comparable<Seat> {

        private final String seatNumber;
        private double price;
        private boolean reserved = false;

        public Seat(String seatNumber, double price) {
            this.seatNumber = seatNumber;
            this.price = price;
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return price;
        }

        // method to reserve a seat
        public boolean reserve() {
            if (!this.reserved) {
                this.reserved = true;
                System.out.printf("Seat %s has been successfully reserved.%n", this.seatNumber);
                return true;
            } else {
                System.out.printf("Sorry, seat %s is already reserved by someone else.%n", this.seatNumber);
                return false;
            }
        }

        // method to cancel seat reservation
        public boolean cancel() {
            if (this.reserved) {
                this.reserved = false;
                System.out.printf("Reservation for seat %s has been successfully cancelled.%n", this.seatNumber);
                return true;
            } else {
                System.out.println("There was no reservation on this seat.");
                return false;
            }
        }

        @Override
        public int compareTo(Seat seat) {
            return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
        }

        @Override
        public String toString() {
            return seatNumber;
        }
    }
}
