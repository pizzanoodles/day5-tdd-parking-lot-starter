package com.parkinglot;

import com.parkinglot.exception.FullParkingLotException;

import java.util.Comparator;
import java.util.List;

public class SuperParkingBoy extends ParkingBoy{

    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }
    public ParkingTicket park(Car car) {
        return parkingLots.stream()
                .max(Comparator.comparing(ParkingLot::getAvailablePositionRate))
                .orElseThrow(FullParkingLotException::new)
                .park(car);
    }
}
