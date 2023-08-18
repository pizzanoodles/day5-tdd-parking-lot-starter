package com.parkinglot;

import com.parkinglot.exception.FullParkingLotException;
import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.List;

public class StandardParkingBoy extends ParkingBoy {

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {
        return parkingLots.stream()
                .filter(ParkingLot::hasAvailableCapacity)
                .findFirst()
                .orElseThrow(FullParkingLotException::new)
                .park(car);
    }

    public Car fetch(ParkingTicket parkingTicket) {
        return parkingLots.stream()
                .map(parkingLot -> parkingLot.fetch(parkingTicket))
                .findFirst()
                .orElseThrow(UnrecognizedTicketException::new);
    }
}
