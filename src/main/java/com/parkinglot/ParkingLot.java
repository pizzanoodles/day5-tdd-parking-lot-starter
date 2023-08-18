package com.parkinglot;

public class ParkingLot {
    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        car.setParkingTicket(parkingTicket);
        return parkingTicket;
    }
}
