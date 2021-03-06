package com.learning.theatre;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Theatre theatre = new Theatre("Cineplex", 8, 12);

        theatre.reserveSeat("H11");
        theatre.reserveSeat("H11");

        printList(theatre.seats);

        List <Theatre.Seat> reverseSeats = new ArrayList<>(theatre.getSeats());
        Collections.reverse(reverseSeats);
        printList(reverseSeats);


        // Comparator example
        List<Theatre.Seat> priceSeats = new ArrayList<>(theatre.getSeats());
        priceSeats.add(theatre.new Seat("B00", 13.00));
        priceSeats.add(theatre.new Seat("A00", 13.00));
        Collections.sort(priceSeats, Theatre.PRICE_ORDER);

        printList(priceSeats);
    }

    public static void printList(List<Theatre.Seat> list) {
        for (Theatre.Seat s : list) {
            System.out.println(s.getSeatNumber() + " " + s.getPrice() + " ");
        }
        System.out.println("============================");
    }
}

