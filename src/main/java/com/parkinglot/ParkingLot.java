package com.parkinglot;

import com.parkinglot.exception.FullParkingLotException;
import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingLot {
    private Map<ParkingTicket, Car> ticketCarMap = new HashMap<>();
    private static final int capacity = 10;

    private boolean isFull() {
        return ticketCarMap.size() == capacity;
    }

    public ParkingTicket park(Car car) {
        if (isFull()) {
            throw new FullParkingLotException();
        }
        ParkingTicket parkingTicket = new ParkingTicket();
        ticketCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetch(ParkingTicket parkingTicket) {
        if (ticketCarMap.get(parkingTicket) == null) {
            throw new UnrecognizedTicketException();
        }
        return ticketCarMap.remove(parkingTicket);
    }

    public ParkingTicket getParkingTicketOfCar(Car car) {
        List<Map.Entry<ParkingTicket, Car>> ticketWithMatchingCar = ticketCarMap.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(car))
                .collect(Collectors.toList());
        return ticketWithMatchingCar.get(0).getKey();
    }

    public int getAvailableCapacity() {
        return capacity - ticketCarMap.size();
    }

    public boolean hasAvailableCapacity() {
        return ((capacity - ticketCarMap.size()) != 0);
    }
}
