package com.parkinglot;

import java.util.List;

public abstract class ParkingBoy {
    protected List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }
    public ParkingTicket park(Car car) {
        return new ParkingTicket();
    }
    public Car fetch(ParkingTicket parkingTicket) {
        return new Car();
    }
}
