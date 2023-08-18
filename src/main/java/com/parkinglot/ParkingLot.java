package com.parkinglot;

import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingLot {
    private Map<ParkingTicket, Car> carTicketMap = new HashMap<>();

    public ParkingTicket park(Car car) {
        if (carTicketMap.size() == 10) return null;
        ParkingTicket parkingTicket = new ParkingTicket();
        carTicketMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (carTicketMap.get(parkingTicket) == null) {
            throw new UnrecognizedTicketException();
        }
        return carTicketMap.remove(parkingTicket);
    }

    public ParkingTicket getParkingTicketOfCar(Car car) {
        List<Map.Entry<ParkingTicket, Car>> ticketWithMatchingCar = carTicketMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(car))
                .collect(Collectors.toList());
        return ticketWithMatchingCar.get(0).getKey();
    }
}
