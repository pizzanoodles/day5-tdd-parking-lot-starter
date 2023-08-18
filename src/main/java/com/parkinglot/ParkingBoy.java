package com.parkinglot;

import com.parkinglot.exception.UnrecognizedTicketException;

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
        return parkingLots.stream()
                .filter(ParkingLot::hasParkedCars)
                .findFirst()
                .orElseThrow(UnrecognizedTicketException::new)
                .fetch(parkingTicket);
    }
}
