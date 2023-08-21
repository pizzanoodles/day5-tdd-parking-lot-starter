package com.parkinglot;

import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.List;

public abstract class ParkingBoy {
    protected List<ParkingLot> parkingLots;
    // TODO: can use private
    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public abstract ParkingTicket park(Car car);

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLots.stream()
                .filter(ParkingLot::hasParkedCars)
                .findFirst()
                .orElseThrow(UnrecognizedTicketException::new)
                .fetch(parkingTicket);
    }
}
