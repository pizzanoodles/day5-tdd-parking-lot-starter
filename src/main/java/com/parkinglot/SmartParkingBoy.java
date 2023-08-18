package com.parkinglot;

import com.parkinglot.exception.FullParkingLotException;

import java.util.Comparator;
import java.util.List;

import static java.lang.reflect.Array.get;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public ParkingTicket park(Car car) {
        return parkingLots.stream()
                .max(Comparator.comparing(ParkingLot::getAvailableCapacity))
                .orElseThrow(FullParkingLotException::new)
                .park(car);
    }
}
