package com.parkinglot;

import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.Comparator;
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
                .min(Comparator.comparing(ParkingLot::getAvailableCapacity))
                .map(parkingLot -> parkingLot.fetch(parkingTicket))
                .orElseThrow(UnrecognizedTicketException::new);
    }
}
